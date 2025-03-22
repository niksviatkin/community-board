package com.viatkin.communityboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viatkin.communityboard.model.Event;
import com.viatkin.communityboard.repository.EventRepository;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  public Event createEvent(Event event) {
    return eventRepository.save(event);
  }

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public Optional<Event> getEventById(Long id) {
    return eventRepository.findById(id);
  }
}
