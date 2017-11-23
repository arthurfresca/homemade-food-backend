package com.mytaxi.controller.driver;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.driver.DriverDTO;
import com.mytaxi.domainobject.driver.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidCredentialException;
import com.mytaxi.service.authenticate.AuthenticateService;
import com.mytaxi.service.driver.DriverService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;
    private final AuthenticateService authenticateService;


    @Autowired
    public DriverController(final DriverService driverService, final AuthenticateService authenticateService)
    {
        this.authenticateService = authenticateService;
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriverById(
        @RequestHeader(value = "Authorization") String token,
        @Valid @PathVariable long driverId) throws EntityNotFoundException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(
        @RequestHeader(value = "Authorization") String token,
        @Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriverById(
        @RequestHeader(value = "Authorization") String token,
        @Valid @PathVariable long driverId) throws EntityNotFoundException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateDriverLocation(
        @RequestHeader(value = "Authorization") String token,
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @PutMapping
    public void updateCarDriving(
        @RequestHeader(value = "Authorization") String token,
        @Valid @RequestParam long driverId, @RequestParam(required = false) Long carId)
        throws EntityNotFoundException, CarAlreadyInUseException, InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        driverService.updateCarDriving(driverId, carId);
    }


    @GetMapping
    public List<DriverDTO> findDriversByAttributes(
        @RequestHeader(value = "Authorization") String token,
        @ModelAttribute DriverDTO driverDTO) throws InvalidCredentialException
    {
        authenticateService.authenticateToken(token);
        return DriverMapper.makeDriverDTOList(driverService.find(driverDTO));
    }

}
