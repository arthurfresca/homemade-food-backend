package com.mytaxi.service.car;

import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateRating(long carId, Integer rating) throws EntityNotFoundException;

    List<CarDO> find(EngineType engineType);

    List<CarDO> filteredCars(CarDTO carDTO);

}
