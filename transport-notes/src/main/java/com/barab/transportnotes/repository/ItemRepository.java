package com.barab.transportnotes.repository;

import com.barab.transportnotes.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
