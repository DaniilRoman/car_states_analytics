package com.app.admin.data.road_track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity(name="route_mark")
@NoArgsConstructor
@AllArgsConstructor
public class RouteTrackMark {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="route_id")
    private CarRoute carRoute;

    private OffsetDateTime time_mark;

    private String x;

    private String y;

    private String z;

    private Float speed;

    private Float oil;
}
