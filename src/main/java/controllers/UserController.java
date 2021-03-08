package controllers;

import entities.Book;
import entities.User;
import filters.customAnnotations.JWTTokenNeeded;
import services.interfaces.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@JWTTokenNeeded
@Path("users")
public class UserController {
    @Inject
    private IUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users;
        try {
            users = userService.getAll();
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .ok(users)
                .build();
    }

    @GET
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadBooks(User user){
        List<Book> books;
        try{
            books = userService.getReadBooks(user);
        }
        catch (ServerErrorException ex){
            return Response.status(500).entity(ex.getMessage()).build();
        }

        return Response.ok(books).build();
    }

    @POST
    @Path("/read/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(User user, @PathParam("id") int bookId){
        boolean isRead;
        try{
            isRead = userService.readBook(user, bookId);
        }
        catch (ServerErrorException ex){
            return Response.status(500).entity(ex.getMessage()).build();
        }

        if(isRead)
            return Response.ok("Read the book").build();

        return Response.ok("Do not read the book").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        boolean created;
        try {
            created = userService.create(user);
        } catch (ServerErrorException ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("User cannot be created!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("User created successfully!")
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        User user;
        try {
            user = userService.get(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (user == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("User does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @GET
    @Path("/word/{word}")
    public Response something(@PathParam("word") String word) {
        return Response.ok("You entered : " + word).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        boolean removed;

        try {
            removed = userService.delete(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (removed) {
            return Response.ok("A user was removed successfully!").build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("A user with such id was not found!")
                    .build();
        }
    }
}
