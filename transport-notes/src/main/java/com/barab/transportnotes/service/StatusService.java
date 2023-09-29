package com.barab.transportnotes.service;

import com.barab.transportnotes.dto.StatusDto;

import java.util.List;

public interface StatusService {
    List<StatusDto> findAllItems();

    List<String> findAllNames();
}
