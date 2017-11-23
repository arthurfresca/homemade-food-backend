package com.mytaxi.mock;

import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.Manufacturer;

public class CarMockFactory
{
    public static CarDO createCarDO()
    {

        CarDO carDO = new CarDO();
        carDO.setId(1L);
        carDO.setCarName("Test");
        carDO.setLicensePlate("ABC-123");
        carDO.setDeleted(false);
        carDO.setEngineType(EngineType.ELETRIC);
        return carDO;
    }


    public static CarDTO getCarDto()
    {

        CarDTO carDTO = new CarDTO();
        carDTO.setEngineType(EngineType.ELETRIC);
        carDTO.setCarName("c180");
        carDTO.setConvertible(true);
        carDTO.setLicensePlate("ABC-1234");
        carDTO.setManufacturer(Manufacturer.MERCEDES_BENZ);
        carDTO.setRating(1);
        carDTO.setSeatCount(4);
        return carDTO;
    }


    public static CarDTO createCarDto()
    {

        CarDTO carDTO = new CarDTO();
        carDTO.setEngineType(EngineType.ELETRIC);
        carDTO.setCarName("c180");
        carDTO.setConvertible(true);
        carDTO.setLicensePlate("ADC-1234");
        carDTO.setManufacturer(Manufacturer.MERCEDES_BENZ);
        carDTO.setRating(1);
        carDTO.setSeatCount(4);
        return carDTO;
    }

}
