package com.app.admin.repository;

import com.app.admin.data.road_track.RouteTrackMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteMarkRepository extends JpaRepository<RouteTrackMark, UUID> {
}
