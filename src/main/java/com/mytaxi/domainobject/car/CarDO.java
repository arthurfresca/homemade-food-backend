package com.mytaxi.domainobject.car;

import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.Manufacturer;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"license_plate"})
)
public class CarDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_created", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Basic(optional = false)
    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Basic(optional = false)
    @Column(name = "car_name", nullable = false)
    private String carName;

    @Basic(optional = false)
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    private EngineType engineType;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false)
    private Manufacturer manufacturer;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column(name = "convertible")
    private Boolean convertible;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
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


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
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
