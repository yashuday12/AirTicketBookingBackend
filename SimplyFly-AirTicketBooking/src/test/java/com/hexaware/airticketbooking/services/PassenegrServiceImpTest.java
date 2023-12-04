package com.hexaware.airticketbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.airticketbooking.dto.PassengerDTO;
import com.hexaware.airticketbooking.entities.Passenger;
import com.hexaware.airticketbooking.exceptions.PassengerNotFoundException;
import com.hexaware.airticketbooking.repository.IPassengerRepository;
@SpringBootTest
class PassenegrServiceImpTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	

	@Mock
    private IPassengerRepository passengerRepository;

    @InjectMocks
    private PassenegrServiceImp passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRemovePassenger() {
        int passengerId = 1;
        passengerService.removePassenger(passengerId);
        verify(passengerRepository, times(1)).deleteById(passengerId);
    }

    @Test
    void testGetByPassengerId() throws PassengerNotFoundException {
        int passengerId = 1;
        Passenger passenger = createPassenger(passengerId);
        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(passenger));
        PassengerDTO result = passengerService.getByPassengerId(passengerId);
        assertNotNull(result);
        assertEquals(passengerId, result.getPassengerId());
    }

    @Test
    void testGetAllPassengers() {
        Passenger passenger1 = createPassenger(1);
        Passenger passenger2 = createPassenger(2);
        List<Passenger> passengers = List.of(passenger1, passenger2);
        when(passengerRepository.findAll()).thenReturn(passengers);
        List<PassengerDTO> result = passengerService.getAllPassengers();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetPassengerByTicketId() {
        int ticketId = 1;
        Passenger passenger1 = createPassenger(1);
        Passenger passenger2 = createPassenger(2);
        List<Passenger> passengers = List.of(passenger1, passenger2);
        when(passengerRepository.getPassengerByTicketId(ticketId)).thenReturn(passengers);
        List<PassengerDTO> result = passengerService.getPassengerByTicketId(ticketId);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFetchBookedSeats() {
        LocalDate travelDate = LocalDate.of(2023, 1, 1);
        int flightId = 1;
        List<String> bookedSeats = List.of("A1", "B2");
        when(passengerRepository.fetchBookedSeats(travelDate, flightId)).thenReturn(bookedSeats);
        List<String> result = passengerService.fetchBookedSeats(travelDate, flightId);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    private Passenger createPassenger(int id) {
        Passenger passenger = new Passenger();
        passenger.setPassenegerId(id);
        return passenger;
    }
}


