package com.viatkin.communityboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viatkin.communityboard.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
