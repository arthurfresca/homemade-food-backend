package com.mytaxi.controller.car;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidCredentialException;
import com.mytaxi.service.authenticate.AuthenticateService;
import com.mytaxi.service.car.CarService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    private final CarService carService;
    private final AuthenticateService authenticateService;


    @Autowired
    public CarController(final CarService carService, final AuthenticateService authenticateService)
    {
        this.carService = carService;
        this.authenticateService = authenticateService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCarById(
        @RequestHeader(value = "Authorization") String token,
        @Valid @PathVariable long carId) throws EntityNotFoundException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(
        @RequestHeader(value = "Authorization") String token,
        @Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCarById(
        @RequestHeader(value = "Authorization") String token,
        @Valid @PathVariable long carId) throws EntityNotFoundException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        carService.delete(carId);
    }


    @PutMapping("/{carId}")
    public void updateCarRating(
        @RequestHeader(value = "Authorization") String token,
        @Valid @PathVariable long carId, @RequestParam Integer rating)
        throws EntityNotFoundException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        carService.updateRating(carId, rating);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarDTO> findCarsByAttributes(
        @RequestHeader(value = "Authorization") String token,
        @ModelAttribute CarDTO carDTO) throws InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        return CarMapper.makeCarDTOList(carService.filteredCars(carDTO));
    }

}
