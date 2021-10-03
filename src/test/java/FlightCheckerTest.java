import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.FlightChecker;
import com.gridnine.testing.models.Flight;
import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightCheckerTest extends TestCase {
    List<Flight> flightList;
    FlightChecker flightChecker;
    List<Flight> flightsToCurTime;
    List<Flight> flightsWithArBeforeDep;
    List<Flight> flightsWithLongTime;

    @Override
    protected void setUp() throws Exception {
        flightList = FlightBuilder.createFlights();
        flightChecker = new FlightChecker();

        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);


        flightsToCurTime = Arrays.asList(FlightBuilder.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow));

        flightsWithArBeforeDep = Arrays.asList(FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)));

        flightsWithLongTime = Arrays.asList(FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    public void testFindDepartureToTheCurrentTime() {
        List<String> actual = flightChecker.findDepartureToTheCurrentTime(flightList).stream().map(f -> f.toString()).collect(Collectors.toList());
        List<String> expected = new ArrayList<>(flightsToCurTime.stream().map(f -> f.toString()).collect(Collectors.toList()));

        assertEquals(expected,actual);
    }

    public void testFindArrivalBeforeDeparture() {
        List<String> actual = flightChecker.findArrivalBeforeDeparture(flightList).stream().map(f -> f.toString()).collect(Collectors.toList());
        List<String> expected = new ArrayList<>(flightsWithArBeforeDep.stream().map(f -> f.toString()).collect(Collectors.toList()));

        assertEquals(expected, actual);
    }

    public void testFindLongOnEarth() {
        List<String> actual = flightChecker.findLongOnEarth(flightList).stream().map(f -> f.toString()).collect(Collectors.toList());
        List<String> expected = new ArrayList<>(flightsWithLongTime.stream().map(f -> f.toString()).collect(Collectors.toList()));

        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
