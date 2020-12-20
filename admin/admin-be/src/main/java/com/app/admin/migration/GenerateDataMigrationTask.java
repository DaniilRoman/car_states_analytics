package com.app.admin.migration;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;


public class GenerateDataMigrationTask implements CustomTaskChange {
    @Override
    public void execute(Database database) throws CustomChangeException {
        Connection connection = ((JdbcConnection) database.getConnection()).getUnderlyingConnection();
        DataSource dataSource = new SingleConnectionDataSource(connection, false);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder finalInsert = new StringBuilder();

        finalInsert.append(getFullAccountString("admin", true));
        finalInsert.append(getFullAccountString("user", false));


        for (int i = 0; i < 10; i++) {
            finalInsert.append(getFullAccountString(null, false));
        }

        Arrays.stream(finalInsert.toString().split(";")).forEach(jdbcTemplate::update);
    }

    private String getFullAccountString(String username, boolean isAdmin) {
        String accountId = UUID.randomUUID().toString();
        String carId = UUID.randomUUID().toString();

        String accountString = getAccountString(accountId, username, isAdmin);
        String carString = getCarString(carId, accountId);
        String routeString = getRouteString(carId, accountId);

        return accountString + carString + routeString;
    }

    private String getRouteString(String carId, String userId) {
        String id = UUID.randomUUID().toString();
        String routeName = getRandomEl(new String[]{ "Moscow - NN", "Kazan - Spb", "Omsk - Samara", "Saratov - Volgograd" });

        String routeStr = String.format("INSERT INTO car_route VALUES ('%s','%s','%s','%s');",
                id, carId, userId, "2020-12-20", routeName);
        String routeMarks = getRouteMarks(id);

        return routeStr + routeMarks;
    }

    private String getRouteMarks(String routeId) {
        StringBuilder marks = new StringBuilder();
        for (int i=0; i<getRandom(20, 40); i++) {
            marks.append(getRouteMark(routeId));
        }
        return marks.toString();
    }

    private String getRouteMark(String routeId) {
        String id = UUID.randomUUID().toString();
        Float speed = (float) getRandom(60, 160);
        Float oil = (float) getRandom(6, 12);

        return String.format("INSERT INTO route_mark VALUES ('%s','%s','%s','%s','%s','%s',%f,%f);",
                id, routeId, new Timestamp(1608480124000L), "x", "y", "z", speed, oil);
    }

    private String getAccountString(String id, String username, boolean isAdmin) {
        id = id == null ? UUID.randomUUID().toString() : id;
        username = username == null ? "user"+getRandom() : username;
        String password = "$2a$10$a5P4q5DBpNNgzWQSADLoKuVYXUL93UsDjX.ntDVrfeTXs9VIUyQ5."; // encoded: 12345


        String accStr = String.format("INSERT INTO account VALUES ('%s','%s','%s','%s','%s');",
                id, username, password, "2020-12-20", "2020-12-20");

        String roleId = isAdmin ? "9f763f53-a3e7-430d-86da-f7283b617b3b" : "c9ccb552-f864-4dea-b7ab-d3d6f9bbe8d3";
        String accRoleStr = String.format("INSERT INTO account_role VALUES ('%s','%s');",
                id, roleId);
        return accStr + accRoleStr;
    }

    private String getCarString(String carId, String ownerId) {
        String brand = getRandomEl(new String[]{ "Tayota", "BMW", "Opel", "WV", "Audi", "Lexus", "Mercedes" });
        String model = getRandomEl(new String[]{ "m1", "m2", "m3", "m4", "m5", "m6", "m7" });

        String carCtr = String.format("INSERT INTO cars VALUES ('%s','%s','%s','%s');",
                carId, brand, model, ownerId);

        String paramsStr = getCarParamsString(carId);
        return carCtr + paramsStr;
    }

    private String getCarParamsString(String carId) {
        String maxSpeedId = "8643c9e3-cd44-458c-984e-e3815b6115d5";
        String oilVolumeId = "726a9679-02a4-4896-8548-28bac097fd15";
        String yearId = "717c9e8f-1e95-462c-85fe-58aa7d538608";
        String colorId = "9187534b-d6ab-4e35-9e02-44db8f825a63";

        String maxSpeed = getRandomEl(new String[]{ "120", "250", "180", "150", "160", "200", "220" });
        String oilVolume = getRandomEl(new String[]{ "80", "90", "75", "95", "99" });
        String year = getRandomEl(new String[]{ "1999", "2010", "2001", "2018", "2008", "2006", "2012" });
        String color = getRandomEl(new String[]{ "white", "red", "blue", "orange", "black", "green", "yellow" });

        return String.format("INSERT INTO car_parameters VALUES ('%s','%s','%s');" +
                "INSERT INTO car_parameters VALUES ('%s','%s','%s');" +
                "INSERT INTO car_parameters VALUES ('%s','%s','%s');" +
                "INSERT INTO car_parameters VALUES ('%s','%s','%s');",
                carId, maxSpeedId, maxSpeed,
                carId, oilVolumeId, oilVolume,
                carId, yearId, year,
                carId, colorId, color);
    }

    private <T> T getRandomEl(T[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    private int getRandom() {
        return new Random().nextInt(1000);
    }

    public int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

}
