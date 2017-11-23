package com.mytaxi.service;

import com.mytaxi.exception.InvalidCredentialException;
import com.mytaxi.service.authenticate.AuthenticateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticateServiceTest
{

    @Autowired
    private AuthenticateService authenticateService;


    @Before
    public void setUp()
    {
    }


    @Test
    public void failedAuthTest() throws InvalidCredentialException
    {
        try
        {
            //YHJpdmVyMDc6ZHJpdmVyMDdwdw== is a invalid token
            authenticateService.authenticateToken("Basic YHJpdmVyMDc6ZHJpdmVyMDdwdw==");
        }
        catch (InvalidCredentialException e)
        {
            assertEquals(e.getMessage(), "User doesn't not exists or password is invalid");
        }

    }
}
