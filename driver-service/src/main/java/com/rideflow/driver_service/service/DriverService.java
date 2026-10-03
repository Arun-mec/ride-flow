package com.rideflow.driver_service.service;

import com.rideflow.driver_service.domain.Driver;
import com.rideflow.driver_service.domain.dto.DriverRequest;

import java.util.List;
import java.util.UUID;

public interface DriverService {

    Driver registerDriver(DriverRequest driverRequest);

    Driver getDriverById(UUID id);

    List<Driver> getAllDrivers();
}
