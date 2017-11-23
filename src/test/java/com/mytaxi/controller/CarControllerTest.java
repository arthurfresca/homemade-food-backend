package com.mytaxi.controller;

import com.mytaxi.controller.car.CarController;
import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidCredentialException;
import com.mytaxi.mock.CarMockFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest
{

    @Autowired
    private CarController carController;


    @Before
    public void setUp()
    {
    }


    @Test
    public void getDriverByIdTest() throws InvalidCredentialException, EntityNotFoundException
    {
        //driver07 authentication token.
        CarDTO output = carController.getCarById("Basic ZHJpdmVyMDc6ZHJpdmVyMDdwdw==", 1L);
        CarDTO mock = CarMockFactory.getCarDto();
        assertEquals(output.getCarName(), mock.getCarName());
        assertEquals(output.getEngineType(), mock.getEngineType());
        assertEquals(output.getLicensePlate(), mock.getLicensePlate());
    }


    @Test
    public void entityNotFoundTest() throws InvalidCredentialException, EntityNotFoundException
    {
        try
        {
            CarDTO output = carController.getCarById("Basic ZHJpdmVyMDc6ZHJpdmVyMDdwdw==", 1000L);
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
        CarDTO mock = CarMockFactory.createCarDto();
        CarDTO output = carController.createCar("Basic ZHJpdmVyMDc6ZHJpdmVyMDdwdw==", mock);
        assertEquals(output.getCarName(), mock.getCarName());
        assertEquals(output.getEngineType(), mock.getEngineType());
        assertEquals(output.getManufactureYear(), mock.getManufactureYear());
        assertEquals(output.getLicensePlate(), mock.getLicensePlate());
    }

}
