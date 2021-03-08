package services.interfaces;

import dtos.AccessToken;
import dtos.LoginDto;
import entities.User;

public interface IAuthService {
    AccessToken authenticateUser(LoginDto data) throws Exception;

    User getUserByUsername(String issuer);
}
