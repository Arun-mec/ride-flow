package com.rideflow.driver_service.service.impl;

import com.rideflow.driver_service.domain.Driver;
import com.rideflow.driver_service.domain.dto.DriverRequest;
import com.rideflow.driver_service.domain.dto.DriverResponse;
import com.rideflow.driver_service.exception.DriverNotFoundException;
import com.rideflow.driver_service.exception.DuplicateDriverException;
import com.rideflow.driver_service.repository.DriverRepository;
import com.rideflow.driver_service.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver registerDriver(DriverRequest driverRequest) {
        if (driverRepository.existsByEmail(driverRequest.email())) {
            throw new DuplicateDriverException("email already registered: "+driverRequest.email());
        }

        if (driverRepository.existsByPhoneNumber(driverRequest.phoneNumber())) {
            throw new DuplicateDriverException("phone number already registered: "+driverRequest.phoneNumber());
        }

        if (driverRepository.existsByVehicleNumber(driverRequest.vehicleNumber())) {
            throw new DuplicateDriverException("vehicle number already registered: "+driverRequest.vehicleNumber());
        }

        Driver driver = new Driver(driverRequest.username(), driverRequest.email(), driverRequest.phoneNumber(), driverRequest.vehicleNumber());
        return driverRepository.save(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver getDriverById(UUID id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

}
