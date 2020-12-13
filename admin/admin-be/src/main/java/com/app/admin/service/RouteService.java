package com.app.admin.service;

import com.app.admin.api.model.CarRouteMarkRequest;
import com.app.admin.api.model.CarRouteRequest;
import com.app.admin.data.road_track.CarRoute;
import com.app.admin.data.road_track.RouteTrackMark;
import com.app.admin.repository.CarRouteRepository;
import com.app.admin.repository.RouteMarkRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteService {

    private final CarRouteRepository carRouteRepository;
    private final RouteMarkRepository routeMarkRepository;

    private final CarService carService;
    private final AccountService accountService;

    public CarRoute addCarRoute(CarRouteRequest carRouteRequest) throws NotFoundException {
        CarRoute carRoute = new CarRoute();
        carRoute.setCreated(carRouteRequest.getDate());
        carRoute.setCar(carService.getCarById(carRouteRequest.getCarId()));
        carRoute.setUser(accountService.findById(carRouteRequest.getUserId()));
        carRoute = carRouteRepository.saveAndFlush(carRoute);
        return carRoute;
    }

    public void deleteCarRouteById(UUID routeId) {
        carRouteRepository.deleteById(routeId);
    }

    public CarRoute getCarRouteById(UUID routeId) throws NotFoundException {
        return carRouteRepository.findById(routeId).orElseThrow(() -> new NotFoundException("Route with id: " + routeId + " not found"));
    }

    public List<CarRoute> getCarRoutesByCarId(UUID carId) {
        return carRouteRepository.findByCarId(carId);
    }


    public void deleteCarRouteMarkById(UUID routeMarkId) {
        routeMarkRepository.deleteById(routeMarkId);
    }

    public RouteTrackMark addCarRouteMark(UUID routeId, CarRouteMarkRequest carRouteMarkRequest) throws NotFoundException {
        RouteTrackMark routeTrackMark = new RouteTrackMark();
        CarRoute carRoute = getCarRouteById(routeId);
        routeTrackMark.setCarRoute(carRoute);

        routeTrackMark.setX(carRouteMarkRequest.getX());
        routeTrackMark.setY(carRouteMarkRequest.getY());
        routeTrackMark.setZ(carRouteMarkRequest.getZ());
        routeTrackMark.setSpeed(carRouteMarkRequest.getSpeed());
        routeTrackMark.setOil(carRouteMarkRequest.getOil());
        routeTrackMark.setTime_mark(carRouteMarkRequest.getTimeMark());
        routeTrackMark = routeMarkRepository.saveAndFlush(routeTrackMark);
        return routeTrackMark;
    }
}
