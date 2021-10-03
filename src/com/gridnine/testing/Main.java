package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {
        FlightChecker flightChecker = new FlightChecker();
        flightChecker.start(FlightBuilder.createFlights());
    }
}
