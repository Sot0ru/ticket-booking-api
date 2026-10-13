package io.github.sot0ru.ticketbooking.seat;

import io.github.sot0ru.ticketbooking.event.Event;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.Instant;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "row_label", "seat_number"}))
public class Seat {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Event event;

	private String rowLabel;

	private int seatNumber;

	@Enumerated(EnumType.STRING)
	private SeatStatus status = SeatStatus.AVAILABLE;

	/** Set while the seat is HELD; a scheduled job releases the seat once this point has passed. */
	private Instant heldUntil;

	// TODO (week 1): add a version field for optimistic locking.
	//  Question to answer in your README: what happens without it when two requests hold the same seat at once?

	protected Seat() {
		// required by JPA
	}

	public Seat(Event event, String rowLabel, int seatNumber) {
		this.event = event;
		this.rowLabel = rowLabel;
		this.seatNumber = seatNumber;
	}

	// TODO (week 1): add state transitions, e.g. hold(Instant until), book(), release().
	//  Each one should reject invalid transitions (e.g. booking a seat that is not HELD).

	public Long getId() {
		return id;
	}

	public Event getEvent() {
		return event;
	}

	public String getRowLabel() {
		return rowLabel;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public Instant getHeldUntil() {
		return heldUntil;
	}

}
