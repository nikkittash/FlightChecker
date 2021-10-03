package com.gridnine.testing;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        FlightChecker flightChecker = new FlightChecker();
        flightChecker.start(FlightBuilder.createFlights());
    }
}
