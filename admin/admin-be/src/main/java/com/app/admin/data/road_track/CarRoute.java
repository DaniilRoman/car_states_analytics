package com.app.admin.data.road_track;

import com.app.admin.data.car.Car;
import com.app.admin.data.user.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name="car_route")
@NoArgsConstructor
@AllArgsConstructor
public class CarRoute {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Account user;

    private OffsetDateTime created;

    @OneToMany
    @JoinColumn(name="route_id")
    private List<RouteTrackMark> track;
}
