package com.rideflow.user_service.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record UserRequest (
        @NotBlank(message = "username is required")
        String username,

        @NotBlank(message = "email is required")
        @Email(message = "email must be valid")
        String email,

        @NotBlank(message = "phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "phone number must be 7-15 digits")
        String phoneNumber
) {
}
