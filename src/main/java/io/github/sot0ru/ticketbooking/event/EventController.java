package io.github.sot0ru.ticketbooking.event;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;
import java.util.List;



@RestController 
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping
    public List<EventResponse> findAll() {
        return eventService.findAll().stream()
                .map(EventResponse::from)
                .toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(@PathVariable Long id){
        return eventService.findById(id)
                .map(EventResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody CreateEventRequest request){
        Event event = eventService.create(request.name(), request.venue(), request.startsAt(), request.endsAt());
        return ResponseEntity.created(URI.create("/events/" + event.getId()))

                             .body(EventResponse.from(event)); 
    }
}
