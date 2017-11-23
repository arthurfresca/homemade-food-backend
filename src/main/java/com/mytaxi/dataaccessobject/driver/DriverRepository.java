package com.mytaxi.dataaccessobject.driver;

import com.mytaxi.domainobject.driver.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    @Query("SELECT d FROM DriverDO d WHERE d.username = :username and  d.password = :password")
    List<DriverDO> findExisting(@Param("username") String username, @Param("password") String password);

    @Query("SELECT d FROM DriverDO d WHERE d.carDriving.id = :carId")
    List<DriverDO> findCarInUse(@Param("carId") Long carId);
}
