package io.github.sot0ru.ticketbooking.event;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String venue;

	private Instant startsAt;

	private Instant endsAt;

	protected Event() {
		// required by JPA
	}

	public Event(String name, String venue, Instant startsAt, Instant endsAt) {
		this.name = name;
		this.venue = venue;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getVenue() {
		return venue;
	}

	public Instant getStartsAt() {
		return startsAt;
	}

	public Instant getEndsAt(){
		return endsAt;
	}
}
