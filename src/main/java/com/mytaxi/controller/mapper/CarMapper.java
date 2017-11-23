package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.domainobject.car.CarDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

public class CarMapper
{

    private CarMapper()
    {
    }


    public static CarDO makeCarDO(CarDTO carDTO)
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carDTO, CarDO.class);
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carDO, CarDTO.class);
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
    {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
