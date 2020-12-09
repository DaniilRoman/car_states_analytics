package com.app.admin.service;

import com.app.admin.api.model.CarListRequest;
import com.app.admin.api.model.CarRequest;
import com.app.admin.data.Car;
import com.app.admin.repository.CarRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final AccountService accountService;

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

    public List<Car> findCarsByOwnerId(UUID ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }
}
