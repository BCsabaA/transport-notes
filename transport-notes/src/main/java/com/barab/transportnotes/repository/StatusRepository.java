package com.barab.transportnotes.repository;

import com.barab.transportnotes.entity.TransportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<TransportStatus, Long> {
}
