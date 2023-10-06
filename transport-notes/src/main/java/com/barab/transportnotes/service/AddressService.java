package com.barab.transportnotes.service;

import com.barab.transportnotes.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> findAllItems();
    public void saveAddress(AddressDto addressDto);

}
