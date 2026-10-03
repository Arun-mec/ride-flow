package com.rideflow.driver_service.api;

import com.rideflow.driver_service.domain.Driver;
import com.rideflow.driver_service.domain.dto.DriverRequest;
import com.rideflow.driver_service.domain.dto.DriverResponse;
import com.rideflow.driver_service.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverResponse> register(@Valid @RequestBody DriverRequest driverRequest) {
        Driver newDriver = driverService.registerDriver(driverRequest);
        return new ResponseEntity<>(
                DriverResponse.fromDriver(newDriver),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getById(@PathVariable UUID id) {
        Driver newDriver = driverService.getDriverById(id);
        return new ResponseEntity<>(
                DriverResponse.fromDriver(newDriver),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public List<DriverResponse> getAllDrivers() {
        return driverService.getAllDrivers().stream().map(DriverResponse::fromDriver).toList();
    }
}
