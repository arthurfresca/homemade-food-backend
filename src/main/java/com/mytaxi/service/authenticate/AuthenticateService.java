package com.mytaxi.service.authenticate;

import com.mytaxi.exception.InvalidCredentialException;

public interface AuthenticateService
{

    void authenticateToken(String token) throws InvalidCredentialException;

}
