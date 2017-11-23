package com.mytaxi.dataaccessobject.car;

import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.EngineType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{

    List<CarDO> findByEngineType(EngineType engineType);
}
