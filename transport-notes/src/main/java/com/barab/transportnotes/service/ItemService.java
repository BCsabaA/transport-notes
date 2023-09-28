package com.barab.transportnotes.service;

import com.barab.transportnotes.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllItems();

}
