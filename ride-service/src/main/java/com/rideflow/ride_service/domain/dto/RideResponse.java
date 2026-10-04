package com.rideflow.ride_service.domain.dto;

import com.rideflow.ride_service.domain.Ride;

import java.util.UUID;

public record RideResponse (
        UUID id,
        UUID riderId,
        Double pickupLongitude,
        Double pickupLatitude,
        Double dropoffLongitude,
        Double dropoffLatitude
) {

    public static RideResponse fromRide(Ride ride) {
        return new RideResponse(
                ride.getId(), ride.getRiderId(),
                ride.getPickupLongitude(), ride.getPickupLongitude(),
                ride.getDropoffLongitude(), ride.getDropoffLatitude()
        );
    }
}
