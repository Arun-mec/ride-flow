package com.rideflow.ride_service.matching;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CabDriverMatchingService implements DriverMatchingService {

    private static final UUID SAMPLE_DRIVER_ID = UUID.fromString("650e8400-e29b-41d4-a716-446655440002");

    @Override
    public Optional<UUID> findDriverFor(Double pickupLatitude, Double pickupLongitude) {
        // Driver matching business logic
        return Optional.of(SAMPLE_DRIVER_ID);
    }
}
