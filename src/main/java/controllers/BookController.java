package controllers;

import entities.Book;
import filters.customAnnotations.JWTTokenNeeded;
import services.interfaces.IBookService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("books")
public class BookController {
    @Inject
    private IBookService bookService;

    @JWTTokenNeeded
    @GET
    public Response getAllBooks() {
        List<Book> books;
        try {
            books = bookService.getAll();
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .ok(books)
                .build();
    }


    @JWTTokenNeeded
    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") int id) {
        Book book;
        try {
            book = bookService.getBookById(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (book == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Book does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(book)
                .build();
    }
}
