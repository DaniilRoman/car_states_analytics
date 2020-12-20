package com.app.admin.repository;

import com.app.admin.data.road_track.CarRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRouteRepository extends JpaRepository<CarRoute, UUID> {
    List<CarRoute> findByCarId(UUID carId);
}
