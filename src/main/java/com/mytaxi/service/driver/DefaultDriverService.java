package com.mytaxi.service.driver;

import com.mytaxi.dataaccessobject.car.CarRepository;
import com.mytaxi.dataaccessobject.driver.DriverRepository;
import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.datatransferobject.driver.DriverDTO;
import com.mytaxi.domainobject.driver.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidCredentialException;
import java.util.List;
import javax.validation.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private SessionFactory sessionFactory;


    public DefaultDriverService(final DriverRepository driverRepository, CarRepository carRepository, SessionFactory sessionFactory)
    {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.sessionFactory = sessionFactory;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId driver id
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO driver to be created
     * @return driverDO
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId driver id
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId driver id
     * @param longitude longitude
     * @param latitude latitude
     * @throws EntityNotFoundException throws this exception when there is no entity found
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Select/deselect a car driver are driving with
     *
     * @param driverId driver id
     * @param carId    set it as null in case you want to deselect the car
     * @throws EntityNotFoundException, CarAlreadyInUseException
     */
    @Override
    @Transactional
    public void updateCarDriving(long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        if (OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()))
        {
            throw new ValidationException("Driver must be online to drive a car.");
        }
        if (carId != null && carId > 0)
        {
            List<DriverDO> drivingCar = driverRepository.findCarInUse(carId);
            if (!drivingCar.isEmpty() && drivingCar.get(0).getId() != driverId)
            {
                throw new CarAlreadyInUseException("This car is Already in Use.");
            }
            driverDO.setCarDriving(carRepository.findOne(carId));
            return;
        }
        driverDO.setCarDriving(null);
    }


    /**
     * Filter Driver providing driver and car information
     *
     * @param driverDTO driver attributes to be filtered
     */
    @Override
    public List<DriverDO> find(DriverDTO driverDTO)
    {
        List<DriverDO> results;

        try (Session session = sessionFactory.openSession())
        {
            Criteria criteria = session.createCriteria(DriverDO.class);
            addCriteriaRestriction(driverDTO, criteria);

            results = criteria.list();
        }
        return results;
    }


    @Override
    public Boolean findByUserAndPass(String user, String pass) throws InvalidCredentialException
    {
        if (driverRepository.findExisting(user, pass).isEmpty())
        {
            throw new InvalidCredentialException("User doesn't not exists or password is invalid");
        }
        return true;
    }


    private void addCriteriaRestriction(DriverDTO driverDTO, Criteria criteria)
    {
        criteria.add(Restrictions.eq("deleted", false));
        if (driverDTO.getOnlineStatus() != null)
        {
            criteria.add(Restrictions.eq("onlineStatus", driverDTO.getOnlineStatus()));
        }
        if (driverDTO.getUsername() != null)
        {
            criteria.add(Restrictions.eq("username", driverDTO.getUsername()));
        }
        if (driverDTO.getCarDriving() != null)
        {
            addCarCriteriaRestriction(criteria, driverDTO.getCarDriving());
        }
    }


    private void addCarCriteriaRestriction(Criteria criteria, CarDTO carDTO)
    {
        criteria.createAlias("carDriving", "carDriving", JoinType.LEFT_OUTER_JOIN);
        if (carDTO.getCarName() != null)
        {
            criteria.add(Restrictions.eq("carDriving.carName", carDTO.getCarName()));
        }
        if (carDTO.getConvertible() != null)
        {
            criteria.add(Restrictions.eq("carDriving.convertible", carDTO.getConvertible()));
        }
        if (carDTO.getEngineType() != null)
        {
            criteria.add(Restrictions.eq("carDriving.engineType", carDTO.getEngineType()));
        }
        if (carDTO.getManufactureYear() != null)
        {
            criteria.add(Restrictions.eq("carDriving.manufactureYear", carDTO.getManufactureYear()));
        }
        if (carDTO.getRating() != null)
        {
            criteria.add(Restrictions.gt("carDriving.rating", carDTO.getRating()));
        }
        if (carDTO.getLicensePlate() != null)
        {
            criteria.add(Restrictions.eq("carDriving.licensePlate", carDTO.getLicensePlate()));
        }
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + driverId);
        }
        return driverDO;
    }
}
