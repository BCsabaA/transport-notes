package com.barab.transportnotes.service.impl;

import com.barab.transportnotes.dto.AddressDto;
import com.barab.transportnotes.entity.Address;
import com.barab.transportnotes.repository.AddressRepository;
import com.barab.transportnotes.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Override
    public List<AddressDto> findAllItems() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(this::mapToAddressDto).collect(Collectors.toList());
    }

    @Override
    public void saveAddress(AddressDto addressDto) {
        Address address = mapToAddress(addressDto);
        System.out.println("Address to save: " + address.toString());
        Address savedUser = addressRepository.save(address);
        System.out.println("Saves user: " + savedUser);
    }

    private Address mapToAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setName(addressDto.getName());
        address.setAddress(addressDto.getAddress());
        address.setComment(addressDto.getComment());
        return address;
    }

    private AddressDto mapToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setName(address.getName());
        addressDto.setAddress(address.getAddress());
        addressDto.setComment(address.getComment());

        return addressDto;
    }
}
