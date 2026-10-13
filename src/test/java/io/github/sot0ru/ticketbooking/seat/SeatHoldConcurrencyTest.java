package io.github.sot0ru.ticketbooking.seat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * The core test of this project: many requests try to hold the same seat at the same time,
 * and exactly one of them may succeed.
 */
@Disabled("Enable once the hold endpoint exists (week 2)")
class SeatHoldConcurrencyTest {

	@Test
	void onlyOneOfManyConcurrentHoldsSucceeds() {
		// TODO (week 2), suggested approach:
		//  1. Start the app against Testcontainers (see TicketBookingApiApplicationTests) and create one event + one seat.
		//  2. Start N threads (ExecutorService) that wait on a shared CountDownLatch, so they all fire at once.
		//  3. Each thread sends a hold request for the same seat.
		//  4. Assert: exactly 1 success, N - 1 conflicts (409), and the seat is HELD exactly once in the database.
	}

}
