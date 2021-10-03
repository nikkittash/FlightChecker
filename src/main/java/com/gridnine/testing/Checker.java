package com.gridnine.testing;

import com.gridnine.testing.models.Flight;

import java.util.List;

public interface Checker {

public List<Flight> findDepartureToTheCurrentTime(List<Flight> flightList);

public List<Flight> findArrivalBeforeDeparture(List<Flight> flightList);

public List<Flight> findLongOnEarth(List<Flight> flightList);
}
