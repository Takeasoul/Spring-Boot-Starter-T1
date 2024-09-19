package org.example.repositories;

import org.example.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, UUID> {

    Optional<ErrorLog> findByErrorMessage(String errorMessage);


}