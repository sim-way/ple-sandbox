package com.example.ple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.ple.domain.bus.CommandBus;
import com.example.ple.domain.event.EventStore;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.example.ple.domain.event.DomainEvent;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

  private final CommandBus commandBus;
  private final EventStore eventStore;

  @Autowired
  public SubscriptionController(CommandBus commandBus, EventStore eventStore) {
    this.commandBus = commandBus;
    this.eventStore = eventStore;
  }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        List<DomainEvent> allEvents = eventStore.loadAllEvents();
        return allEvents.stream()
            .collect(java.util.stream.Collectors.groupingBy(DomainEvent::getAggregateId))
            .entrySet().stream()
            .map(entry -> projectState(entry.getKey(), entry.getValue()))
            .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/{subscriptionrootId}")
    public ResponseEntity<?> getById(@PathVariable String subscriptionrootId) {
        List<DomainEvent> events = eventStore.loadAll(subscriptionrootId);
        if (events.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "AggregateNotFound");
            error.put("aggregateType", "Subscription");
            error.put("subscriptionrootId", subscriptionrootId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else {
            return ResponseEntity.ok(projectState(subscriptionrootId, events));
        }
    }

  /**
   * Get raw Event Stream for a specific aggregate instance.
   */
  @GetMapping("/{id}/events")
  public ResponseEntity<List<DomainEvent>> getEvents(@PathVariable String id) {
    List<DomainEvent> events = eventStore.loadAll(id);
    if (events == null || events.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(events);
  }

  /**
   * Get all Events across all aggregate instances.
   */
  @GetMapping("/events/all")
  public ResponseEntity<List<DomainEvent>> getAllEvents() {
    return ResponseEntity.ok(eventStore.loadAllEvents());
  }

  /**
   * Project event stream into current state (Read Model).
   * 
   * Folds the event history for a given aggregate into a single Map representing
   * the current state by applying deterministic state reducers based on command
   * operations (SET, ADD, SUBTRACT) rather than blind payload merging.
   * 
   * @param id The aggregate ID
   * @param events The event stream for this aggregate
   * @return A Map<String, Object> representing the current state
   */
  private Map<String, Object> projectState(String id, List<DomainEvent> events) {
    Map<String, Object> state = new HashMap<>();
    state.put("subscriptionrootId", id);
    
    state.put("id", null);
    state.put("status", null);
    state.put("planType", null);

    for (DomainEvent event : events) {
      // Check if this is a GenericEvent with a payload (using reflection-safe approach)
      try {
        // Try to get the payload if it exists
        var payloadMethod = event.getClass().getDeclaredMethod("getPayload");
        payloadMethod.setAccessible(true);
        Map<String, Object> payload = (Map<String, Object>) payloadMethod.invoke(event);
        if (payload != null) {
          // Get command name from event
          var cmdNameMethod = event.getClass().getDeclaredMethod("getCommandName");
          cmdNameMethod.setAccessible(true);
          String commandName = (String) cmdNameMethod.invoke(event);
          
                    if (payload.containsKey("id")) { state.put("id", payload.get("id")); }
          if (payload.containsKey("status")) { state.put("status", payload.get("status")); }
          if (payload.containsKey("planType")) { state.put("planType", payload.get("planType")); }

        }
      } catch (Exception e) {
        // Event doesn't have a payload, skip it
      }
    }
    
    return state;
  }
}
