package io.github.sot0ru.ticketbooking.event;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.Instant;
import java.util.List;


@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event create(String name, String venue, Instant startsAt, Instant endsAt) {
        Event event = new Event(name, venue, startsAt, endsAt);
        return eventRepository.save(event);
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }
}
