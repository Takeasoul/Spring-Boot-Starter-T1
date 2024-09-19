package org.example.repositories;

import org.example.entity.EventMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventMemberRepository extends JpaRepository<EventMember, UUID>, JpaSpecificationExecutor<EventMember> {

    Page<EventMember> findAllByEventId(UUID eventId, Pageable pageable);
}