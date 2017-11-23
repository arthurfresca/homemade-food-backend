package com.mytaxi.service.driver;

import com.mytaxi.datatransferobject.driver.DriverDTO;
import com.mytaxi.domainobject.driver.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidCredentialException;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    void updateCarDriving(long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    List<DriverDO> find(DriverDTO driverDTO);

    Boolean findByUserAndPass(String user, String pass) throws InvalidCredentialException;

}
