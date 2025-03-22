package com.viatkin.community_board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.viatkin.communityboard.model.Event;
import com.viatkin.communityboard.repository.EventRepository;
import com.viatkin.communityboard.service.EventService;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

  @Mock
  private EventRepository eventRepository;

  @InjectMocks
  private EventService eventService;

  private Event event1;
  private Event event2;

  @BeforeEach
  void setUp() {
    event1 = new Event();
    event1.setId(1L);
    event1.setTitle("Picnic with Community");
    event1.setDescription("Join us for a picnic in the park!");
    event1.setLocation("Central Park");
    event1.setEventDateTime(LocalDateTime.now().plusDays(7));
    event1.setOrganizer("John Doe");

    event2 = new Event();
    event2.setId(2L);
    event2.setTitle("Book Club Meeting");
    event2.setDescription("Discussing 'The Great Gatsby'");
    event2.setLocation("Library");
    event2.setEventDateTime(LocalDateTime.now().plusDays(14));
    event2.setOrganizer("Jane Smith");
  }

  @Test
  void createEvent_shouldSaveEventAndReturnIt() {
    // Arrange: Define the behavior of the mock repository
    when(eventRepository.save(event1)).thenReturn(event1); // When save is called, return the same event

    // Act: Call the method we're testing
    Event savedEvent = eventService.createEvent(event1);

    // Assert: Check the results
    assertNotNull(savedEvent);
    assertEquals(event1.getTitle(), savedEvent.getTitle());
    assertEquals(event1.getDescription(), savedEvent.getDescription());
    assertEquals(event1.getLocation(), savedEvent.getLocation());
    assertEquals(event1.getEventDateTime(), savedEvent.getEventDateTime());
    assertEquals(event1.getOrganizer(), savedEvent.getOrganizer());

    // Verify: Check that the save method was actually called
    verify(eventRepository, times(1)).save(event1);
  }

  @Test
  void getAllEvents_shouldReturnListOfEvents() {
    // Arrange: Define the behavior of the mock repository
    when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

    // Act: Call the method we're testing
    List<Event> events = eventService.getAllEvents();

    // Assert: Check the results
    assertNotNull(events);
    assertEquals(2, events.size());
    assertEquals("Picnic with Community", events.get(0).getTitle());
    assertEquals("Book Club Meeting", events.get(1).getTitle());

    // Verify: Check that the findAll method was actually called
    verify(eventRepository, times(1)).findAll();
  }

  @Test
  void getEventById_shouldReturnEvent_whenEventExists() {
    // Arrange: Define the behavior of the mock repository
    when(eventRepository.findById(1L)).thenReturn(Optional.of(event1));

    // Act: Call the method we're testing
    Optional<Event> foundEvent = eventService.getEventById(1L);

    // Assert: Check the results
    assertTrue(foundEvent.isPresent());
    assertEquals(event1.getTitle(), foundEvent.get().getTitle());

    // Verify: Check that the findById method was actually called
    verify(eventRepository, times(1)).findById(1L);
  }

  @Test
  void getEventById_shouldReturnEmptyOptional_whenEventDoesNotExist() {
    // Arrange: Define the behavior of the mock repository
    when(eventRepository.findById(3L)).thenReturn(Optional.empty()); // Simulate event not found

    // Act: Call the method we're testing
    Optional<Event> foundEvent = eventService.getEventById(3L);

    // Assert: Check the results
    assertFalse(foundEvent.isPresent());

    // Verify: Check that the findById method was actually called
    verify(eventRepository, times(1)).findById(3L);
  }
}
