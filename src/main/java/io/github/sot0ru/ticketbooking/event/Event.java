package io.github.sot0ru.ticketbooking.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String venue;

	private Instant startsAt;

	protected Event() {
		// required by JPA
	}

	public Event(String name, String venue, Instant startsAt) {
		this.name = name;
		this.venue = venue;
		this.startsAt = startsAt;
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

}
