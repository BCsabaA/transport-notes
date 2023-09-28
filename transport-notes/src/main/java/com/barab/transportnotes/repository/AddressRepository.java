package com.barab.transportnotes.repository;

import com.barab.transportnotes.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
