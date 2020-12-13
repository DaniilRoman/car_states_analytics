package com.app.admin.controller;

import com.app.admin.api.RouteApi;
import com.app.admin.api.model.*;
import com.app.admin.data.car.Car;
import com.app.admin.data.road_track.CarRoute;
import com.app.admin.data.road_track.RouteTrackMark;
import com.app.admin.service.RouteService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RouteController implements RouteApi {

    private final CarController carController;
    private final RouteService routeService;

    @Override
    public ResponseEntity<CarRouteResponse> addCarRoute(CarRouteRequest carRouteRequest) {
        try {
            CarRoute carRoute = routeService.addCarRoute(carRouteRequest);
            return ResponseEntity.ok().body(composeCarRouteResponse(carRoute));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCarRouteById(UUID routeId) {
        routeService.deleteCarRouteById(routeId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CarRouteResponse> getCarRouteById(UUID routeId) {
        try {
            CarRoute carRoute = routeService.getCarRouteById(routeId);
            return ResponseEntity.ok().body(composeCarRouteResponse(carRoute));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<CarRouteResponse>> getRoutesByCarId(UUID carId) {
        return ResponseEntity.ok().body(routeService.getCarRoutesByCarId(carId).stream()
                .map(this::composeCarRouteResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<CarRouteMarkResponse> addCarRouteMark(UUID routeId, CarRouteMarkRequest carRouteMarkRequest) {
        try {
            RouteTrackMark trackMark = routeService.addCarRouteMark(routeId, carRouteMarkRequest);
            return ResponseEntity.ok().body(composeCarRouteMarkResponse(trackMark));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCarRouteMarkById(UUID routeMarkId) {
        routeService.deleteCarRouteMarkById(routeMarkId);
        return ResponseEntity.ok().build();
    }

    private CarRouteResponse composeCarRouteResponse(CarRoute carRoute) {
        return new CarRouteResponse()
                .routeId(carRoute.getId())
                .car(carController.composeCarResponse(carRoute.getCar()))
                .date(carRoute.getCreated().toInstant().atOffset(ZoneOffset.UTC))
                .user(carController.composeAccountResponse(carRoute.getUser()))
                .marks(composeCarRouteMarkResponseList(carRoute.getTrack()));
    }

    private CarRouteMarkResponse composeCarRouteMarkResponse(RouteTrackMark routeTrackMark) {
        return new CarRouteMarkResponse()
                .markId(routeTrackMark.getId())
                .oil(routeTrackMark.getOil())
                .speed(routeTrackMark.getSpeed())
                .x(routeTrackMark.getX())
                .y(routeTrackMark.getY())
                .z(routeTrackMark.getZ())
                .timeMark(routeTrackMark.getTime_mark().toInstant().atOffset(ZoneOffset.UTC));
    }

    private List<CarRouteMarkResponse> composeCarRouteMarkResponseList(List<RouteTrackMark> routeTrackMarkList) {
        return routeTrackMarkList.stream()
                .map(this::composeCarRouteMarkResponse)
                .collect(Collectors.toList());
    }

}
