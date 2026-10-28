package io.github.sot0ru.ticketbooking.event;

import java.time.Instant;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;

public record CreateEventRequest(
        @NotBlank(message = "Event name is required") @Size(max = 100) String name,
        @NotBlank(message = "Event venue is required") @Size(max = 100) String venue,
        @NotNull(message = "Event start time is required") @Future(message = "Event start time must be in the future") Instant startsAt,
        @NotNull(message = "Event end time is required") @Future(message = "Event end time must be in the future") Instant endsAt) {

    @AssertTrue(message = "Event end time must be after start time")
    boolean isEndAfterStart() {
        if (startsAt == null || endsAt == null) {
            return true;
        }
        return endsAt.isAfter(startsAt);
    }
}
