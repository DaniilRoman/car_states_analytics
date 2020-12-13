package com.app.admin.service;

import com.app.admin.api.model.CarListRequest;
import com.app.admin.api.model.CarParamRequest;
import com.app.admin.api.model.CarRequest;
import com.app.admin.data.car.Car;
import com.app.admin.data.car.CarParameter;
import com.app.admin.data.car.CarParameterKey;
import com.app.admin.data.characteristics.Parameter;
import com.app.admin.repository.CarRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final AccountService accountService;
    private final ParameterService parameterService;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> addCars(CarListRequest request) throws NotFoundException {
        List<Car> cars = new ArrayList<>();
        for (CarRequest carRequest: request.getCars()) {
            Car newCar = new Car();
            newCar.setBrand(carRequest.getBrand());
            newCar.setModel(carRequest.getModel());
            newCar.setOwner(accountService.findById(carRequest.getOwnerId()));
            newCar = carRepository.saveAndFlush(newCar);
            cars.add(newCar);
        }
        return cars;
    }

    public Car getCarById(UUID id) throws NotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car with id: " + id + " not found"));
    }

    public void deleteCarById(UUID id) {
        carRepository.deleteById(id);
    }

    public Car rewriteCarParameters(UUID carID, List<CarParamRequest> carParamRequest) throws NotFoundException {
        Car car = carRepository.getOne(carID);

        car.getParameters().clear();
        for (CarParamRequest param: carParamRequest) {
            Parameter parameter = parameterService.findById(param.getParamId());

            CarParameter carParameter = new CarParameter(new CarParameterKey(carID, param.getParamId()), param.getValue());
            carParameter.setCar(car);
            carParameter.setParameter(parameter);
            car.getParameters().add(carParameter);
        }
        car = carRepository.saveAndFlush(car);
        return car;
    }

    public Car updateCarParameters(UUID carID, List<CarParamRequest> carParamRequest) throws NotFoundException {
        Car car = carRepository.getOne(carID);

        Set<UUID> existingParameterIds = car.getParameters().stream()
                .map(parameter -> parameter.getParameter().getId())
                .collect(Collectors.toSet());
        for (CarParamRequest param: carParamRequest) {
            Parameter parameter = parameterService.findById(param.getParamId());
            if (existingParameterIds.contains(param.getParamId())) {
                for (CarParameter carParameter: car.getParameters()) {
                    if (carParameter.getParameter().getId().equals(param.getParamId())) {
                        carParameter.setVal(param.getValue());
                    }
                }
            }
            else {
                CarParameter carParameter = new CarParameter(new CarParameterKey(carID, param.getParamId()), param.getValue());
                carParameter.setCar(car);
                carParameter.setParameter(parameter);
                car.getParameters().add(carParameter);
            }
        }
        log.info("car: {}", car);
        car = carRepository.saveAndFlush(car);
        return car;
    }

    public List<Car> findCarsByOwnerId(UUID ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }
}
