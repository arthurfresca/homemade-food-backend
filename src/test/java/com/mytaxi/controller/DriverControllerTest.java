package com.mytaxi.controller;

import com.mytaxi.controller.driver.DriverController;
import com.mytaxi.datatransferobject.driver.DriverDTO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidCredentialException;
import com.mytaxi.mock.DriverMockFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DriverControllerTest
{

    @Autowired
    private DriverController driverController;


    @Before
    public void setUp()
    {
    }


    @Test
    public void getDriverByIdTest() throws InvalidCredentialException, EntityNotFoundException
    {
        //driver07 authentication token.
        DriverDTO output = driverController.getDriverById("Basic ZHJpdmVyMDc6ZHJpdmVyMDdwdw==", 1L);
        DriverDTO mock = DriverMockFactory.createDriverDtoUser();
        assertEquals(output.getId(), mock.getId());
        assertEquals(output.getUsername(), mock.getUsername());
        assertEquals(output.getPassword(), mock.getPassword());
        assertEquals(output.getOnlineStatus(), mock.getOnlineStatus());
    }


    @Test
    public void entityNotFoundTest() throws InvalidCredentialException, EntityNotFoundException
    {
        try
        {
            DriverDTO output = driverController.getDriverById("Basic ZHJpdmVyMDc6ZHJpdmVyMDdwdw==", 1000L);
        }
        catch (EntityNotFoundException e)
        {
            assertEquals(e.getMessage(), "Could not find entity with id: 1000");
        }
    }


    @Test
    public void createDriverTest() throws InvalidCredentialException, ConstraintsViolationException
    {
        //driver07 authentication token.
        DriverDTO mock = DriverMockFactory.createDriverDto();
        DriverDTO output = driverController.createDriver("Basic ZHJpdmVyMDc6ZHJpdmVyMDdwdw==", mock);
        assertEquals(output.getUsername(), mock.getUsername());
        assertEquals(output.getPassword(), mock.getPassword());
        assertEquals(output.getOnlineStatus(), mock.getOnlineStatus());
    }
}
