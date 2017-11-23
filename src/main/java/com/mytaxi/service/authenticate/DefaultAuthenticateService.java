package com.mytaxi.service.authenticate;

import com.mytaxi.exception.InvalidCredentialException;
import com.mytaxi.service.driver.DriverService;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to authenticate user.
 * It was used a basic authentication.
 * To log-in you must generate a token by providing some driver credential.
 * You can use some software like Postman for it.
 */
@Service
public class DefaultAuthenticateService implements AuthenticateService
{

    private DriverService driverService;

    @Autowired
    public DefaultAuthenticateService(DriverService driverService)
    {
        this.driverService = driverService;
    }

    /**
     * Method to check if token is valid
     *
     * @param token authentication token
     * @throws InvalidCredentialException if no user with the given credential was found.
     */

    @Override
    public void authenticateToken(String token) throws InvalidCredentialException
    {
        try
        {
            validateAuthenticationPhrase(token);
        }
        catch (Base64DecodingException e)
        {
            throw new InvalidCredentialException("Error on decoding token");
        }
    }


    private Boolean validateAuthenticationPhrase(String token) throws Base64DecodingException, InvalidCredentialException
    {

        byte[] e = Base64.decode(token.substring(6));
        String usernpass = new String(e);
        String user = usernpass.substring(0, usernpass.indexOf(':'));
        String password = usernpass.substring(usernpass.indexOf(':') + 1);
        return driverService.findByUserAndPass(user, password);
    }
}
