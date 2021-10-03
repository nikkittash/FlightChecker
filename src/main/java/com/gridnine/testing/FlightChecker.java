package com.gridnine.testing;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightChecker implements Checker {

    @Override
    public List<Flight> findDepartureToTheCurrentTime(List<Flight> flightList) {

        List<Flight> flights = flightList.stream().filter(flight -> flight.getSegments().
                        stream().anyMatch(s -> s.getDepartureDate().isBefore(LocalDateTime.now()))).
                collect(Collectors.toList());
        return flights;
    }

    @Override
    public List<Flight> findArrivalBeforeDeparture(List<Flight> flightList) {

        List<Flight> flights = flightList.stream().filter(flight -> flight.getSegments().
                        stream().anyMatch(s -> s.getArrivalDate().isBefore(s.getDepartureDate()))).
                collect(Collectors.toList());
        return flights;
    }

    @Override
    public List<Flight> findLongOnEarth(List<Flight> flightList) {

        List<Flight> flights = new ArrayList<>();

        for (Flight flight : flightList) {
            if(flight.getSegments().size() > 1) {
                long timeOnTheGround = 0;
                List<Segment> segments = flight.getSegments();
                for (int i = 0; i < segments.size() - 1; i++) {
                    timeOnTheGround += ChronoUnit.HOURS.between
                            (segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                    if (timeOnTheGround > 2) {
                        flights.add(flight);
                    }
                }
            }
        }
        return flights;
    }

    public void writeResult(List<Flight> flightList) {
        flightList.stream().forEach(flight -> System.out.println(flight.toString()));
    }


    public void start(List<Flight> flightList) {
        System.out.println("\nFlights with departure up to the current time:");
        writeResult(findDepartureToTheCurrentTime(flightList));

        System.out.println("\nFlights with arrival before departure:");
        writeResult(findArrivalBeforeDeparture(flightList));

        System.out.println("\nFlights with time spent on the ground for more than 2 hours:");
        writeResult(findLongOnEarth(flightList));
    }
}
