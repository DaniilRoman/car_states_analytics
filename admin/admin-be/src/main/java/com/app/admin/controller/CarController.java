package com.app.admin.controller;

import com.app.admin.api.CarApi;
import com.app.admin.api.model.AccountResponse;
import com.app.admin.api.model.CarListRequest;
import com.app.admin.api.model.CarResponse;
import com.app.admin.data.Car;
import com.app.admin.data.user.Account;
import com.app.admin.service.CarService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CarController implements CarApi {
    private final CarService carService;

    @Override
    public ResponseEntity<List<CarResponse>> getCars() {
        return ResponseEntity.ok().body(carService.findAll().stream()
                .map(it -> new CarResponse()
                        .id(it.getId())
                        .brand(it.getBrand())
                        .model(it.getModel())
                        .owner(composeAccountResponse(it.getOwner())))
                .collect(Collectors.toList()));

    }

    @Override
    public ResponseEntity<List<CarResponse>> addCars(CarListRequest carListRequest) {
        try {
            List<CarResponse> carListResponse = carService.addCars(carListRequest).stream()
                    .map(car -> new CarResponse()
                            .id(car.getId())
                            .owner(composeAccountResponse(car.getOwner()))
                            .model(car.getModel())
                            .brand(car.getBrand()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(carListResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCarById(UUID carId) {
        carService.deleteCarById(carId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CarResponse> getCarById(UUID carId) {
        try {
            Car car = carService.getCarById(carId);
            return ResponseEntity.ok().body(new CarResponse()
                    .id(car.getId())
                    .brand(car.getBrand())
                    .model(car.getModel())
                    .owner(composeAccountResponse(car.getOwner())));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<CarResponse>> getCarsByOwnerId(UUID accountId) {
        return ResponseEntity.ok().body(carService.findCarsByOwnerId(accountId).stream()
                .map(it -> new CarResponse()
                        .id(it.getId())
                        .brand(it.getBrand())
                        .model(it.getModel())
                        .owner(composeAccountResponse(it.getOwner())))
                .collect(Collectors.toList()));
    }

    private AccountResponse composeAccountResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setUsername(account.getUsername());
        return accountResponse;
    }
}
