package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.car.CarRepository;
import com.mytaxi.datatransferobject.car.CarDTO;
import com.mytaxi.domainobject.car.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository carRepository;

    @Autowired
    private SessionFactory sessionFactory;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    /**
     * Selects a car by id.
     *
     * @param carId car id
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new car.
     *
     * @param carDO car to be saved
     * @return carDO
     * @throws ConstraintsViolationException if a car already exists with the given username, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId car id
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
    }


    /**
     * Update the car rating
     *
     * @param carId car id
     * @param rating car rating
     * @throws EntityNotFoundException return this Exception in case Entity not exist on DB
     */
    @Override
    @Transactional
    public void updateRating(long carId, Integer rating) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setRating(rating);
    }


    /**
     * Find all cars by engine type
     *
     * @param engineType engine type
     */
    @Override
    public List<CarDO> find(EngineType engineType)
    {
        return carRepository.findByEngineType(engineType);
    }


    /**
     * Filter Car providing car information
     *
     * @param carDTO car attributes to filter cars
     */
    @Override
    public List<CarDO> filteredCars(CarDTO carDTO)
    {
        List<CarDO> results;
        try (Session session = sessionFactory.openSession())
        {
            Criteria criteria = session.createCriteria(CarDO.class);
            addCriteriaRestriction(carDTO, criteria);

            results = criteria.list();
        }
        return results;
    }


    private void addCriteriaRestriction(CarDTO carDTO, Criteria criteria)
    {
        criteria.add(Restrictions.eq("deleted", false));
        if (carDTO.getCarName() != null)
        {
            criteria.add(Restrictions.eq("carName", carDTO.getCarName()));
        }
        if (carDTO.getConvertible() != null)
        {
            criteria.add(Restrictions.eq("convertible", carDTO.getConvertible()));
        }
        if (carDTO.getEngineType() != null)
        {
            criteria.add(Restrictions.eq("engineType", carDTO.getEngineType()));
        }
        if (carDTO.getId() != null)
        {
            criteria.add(Restrictions.eq("id", carDTO.getId()));
        }
        if (carDTO.getManufactureYear() != null)
        {
            criteria.add(Restrictions.eq("manufactureYear", carDTO.getManufactureYear()));
        }
        if (carDTO.getRating() != null)
        {
            criteria.add(Restrictions.gt("rating", carDTO.getRating()));
        }
        if (carDTO.getLicensePlate() != null)
        {
            criteria.add(Restrictions.eq("licensePlate", carDTO.getLicensePlate()));
        }
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carRepository.findOne(carId);
        if (carDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }
        return carDO;
    }

}
