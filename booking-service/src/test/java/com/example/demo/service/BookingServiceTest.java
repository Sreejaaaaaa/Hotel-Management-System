package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.feign.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private GuestRepository guestRepo;

    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private RoomClient roomClient;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private BillingClient billingClient;

    @InjectMocks
    private BookingService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private BookingRequest createRequest() {
        Guest guest = new Guest();
        guest.setId(1);
        guest.setName("Test");

        Booking booking = new Booking();
        booking.setRoomId(1);

        BookingRequest req = new BookingRequest();
        req.setGuest(guest);
        req.setBooking(booking);

        return req;
    }

    @Test
    void testCreateBookingSuccess() throws Exception {

        BookingRequest req = createRequest();

        Guest savedGuest = new Guest();
        savedGuest.setId(1);

        when(guestRepo.save(any())).thenReturn(savedGuest);

        Room room = new Room();
        room.setId(1);
        room.setAvailable(true);
        room.setPrice(100);

        when(roomClient.getRoomById(1)).thenReturn(room);

        when(bookingRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        CompletableFuture<Map<String, Object>> result =
                service.createBooking(req);

        assertNotNull(result.get());
        verify(paymentClient).makePayment(any());
        verify(billingClient).generateBill(any());
    }

    @Test
    void testCreateBookingRoomNotAvailable() {

        BookingRequest req = createRequest();

        when(guestRepo.save(any())).thenReturn(new Guest());

        Room room = new Room();
        room.setAvailable(false);

        when(roomClient.getRoomById(anyInt())).thenReturn(room);

        assertThrows(RuntimeException.class,
                () -> service.createBooking(req));
    }
}