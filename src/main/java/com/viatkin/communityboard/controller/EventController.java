package com.viatkin.communityboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viatkin.communityboard.model.Event;
import com.viatkin.communityboard.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @PostMapping
  public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    Event savedEvent = eventService.createEvent(event);
    return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<List<Event>> getAllEvents() {
    List<Event> events = eventService.getAllEvents();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Event> getEventById(@PathVariable Long id) {
    Optional<Event> event = eventService.getEventById(id);
    return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
