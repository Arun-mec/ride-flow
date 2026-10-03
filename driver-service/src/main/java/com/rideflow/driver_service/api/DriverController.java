package com.rideflow.driver_service.api;

import com.rideflow.driver_service.domain.Driver;
import com.rideflow.driver_service.domain.DriverStatus;
import com.rideflow.driver_service.domain.dto.DriverRequest;
import com.rideflow.driver_service.domain.dto.DriverResponse;
import com.rideflow.driver_service.domain.dto.UpdateLocationRequest;
import com.rideflow.driver_service.domain.dto.UpdateStatusRequest;
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
    public ResponseEntity<DriverResponse> registerDriver(@Valid @RequestBody DriverRequest driverRequest) {
        Driver newDriver = driverService.register(driverRequest);
        return new ResponseEntity<>(
                DriverResponse.fromDriver(newDriver),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable UUID id) {
        Driver newDriver = driverService.getById(id);
        return new ResponseEntity<>(
                DriverResponse.fromDriver(newDriver),
                HttpStatus.FOUND
        );
    }

    @GetMapping
    public List<DriverResponse> getAllDrivers(@RequestParam(required = false)DriverStatus status) {
        return status==null ?
                driverService.getAll().stream().map(DriverResponse::fromDriver).toList():
                driverService.getByStatus(status).stream().map(DriverResponse::fromDriver).toList();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DriverResponse> updateDriverStatus(@PathVariable UUID id,
                                                             @Valid @RequestBody UpdateStatusRequest updateStatusRequest) {
        Driver updateDriver = driverService.updateStatus(id, updateStatusRequest);
        return new ResponseEntity<>(
                DriverResponse.fromDriver(updateDriver),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<DriverResponse> updateDriverLocation(@PathVariable UUID id,
                                                               @Valid @RequestBody UpdateLocationRequest updateLocationRequest) {
        Driver updateDriver = driverService.updateLocation(id, updateLocationRequest);
        return new ResponseEntity<>(
                DriverResponse.fromDriver(updateDriver),
                HttpStatus.OK
        );
    }

}
