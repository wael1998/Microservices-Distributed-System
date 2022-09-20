package com.microcode.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}


