package com.mytaxi.domainobject.driver;

import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "driver",
    uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
public class DriverDO
{

    @Basic(optional = false)
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_created", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Basic(optional = false)
    @Column(name = "username", nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Basic(optional = false)
    @Column(name = "password", nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Basic(optional = false)
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @Column(name = "coordinate")
    @Embedded
    private GeoCoordinate coordinate;

    @Column(name = "date_coordinate_updated")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "online_status", nullable = false)
    private OnlineStatus onlineStatus;

    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @OneToOne
    private CarDO carDriving;


    private DriverDO()
    {
    }


    public DriverDO(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.deleted = false;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = OnlineStatus.OFFLINE;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    public void setOnlineStatus(OnlineStatus onlineStatus)
    {
        this.onlineStatus = onlineStatus;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    public void setCoordinate(GeoCoordinate coordinate)
    {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }


    public CarDO getCarDriving()
    {
        return carDriving;
    }


    public void setCarDriving(CarDO carDriving)
    {
        this.carDriving = carDriving;
    }

}
