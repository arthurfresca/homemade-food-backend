package com.mytaxi.mock;

import com.mytaxi.datatransferobject.driver.DriverDTO;
import com.mytaxi.domainobject.driver.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

public class DriverMockFactory
{

    public static DriverDTO createDriverDto()
    {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setUsername("driver11");
        driverDTO.setPassword("driver1pw");
        driverDTO.setOnlineStatus(OnlineStatus.OFFLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(0, 0);
        driverDTO.setCoordinate(geoCoordinate);
        return driverDTO;
    }


    public static DriverDTO createDriverDtoUser()
    {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setUsername("driver01");
        driverDTO.setPassword("driver01pw");
        driverDTO.setId(1L);
        driverDTO.setOnlineStatus(OnlineStatus.OFFLINE);
        return driverDTO;
    }


    public static DriverDO createDriverDO()
    {
        DriverDO driverDO = new DriverDO("driver01", "driver01pw");
        driverDO.setId(1L);
        GeoCoordinate geoCoordinate = new GeoCoordinate(0, 0);
        driverDO.setCoordinate(geoCoordinate);
        driverDO.setOnlineStatus(OnlineStatus.ONLINE);
        return driverDO;
    }


}
