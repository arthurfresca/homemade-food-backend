package com.mytaxi.datatransferobject.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.Manufacturer;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "LicensePlate can not be null!")
    private String licensePlate;

    @NotNull(message = "CarName can not be null!")
    private String carName;

    @NotNull(message = "EngineType can not be null!")
    private EngineType engineType;

    @NotNull(message = "Manufacturer can not be null!")
    private Manufacturer manufacturer;

    private Integer seatCount;
    private Boolean convertible;
    private Integer rating;
    private Integer manufactureYear;


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public String getCarName()
    {
        return carName;
    }


    public void setCarName(String carName)
    {
        this.carName = carName;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public Manufacturer getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(Manufacturer manufacturer)
    {
        this.manufacturer = manufacturer;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public Integer getRating()
    {
        return rating;
    }


    public void setRating(Integer rating)
    {
        this.rating = rating;
    }


    public Integer getManufactureYear()
    {
        return manufactureYear;
    }


    public void setManufactureYear(Integer manufactureYear)
    {
        this.manufactureYear = manufactureYear;
    }
}
