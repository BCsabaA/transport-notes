package com.barab.transportnotes.service.impl;

import com.barab.transportnotes.dto.ItemDto;
import com.barab.transportnotes.dto.UserDto;
import com.barab.transportnotes.entity.Item;
import com.barab.transportnotes.entity.User;
import com.barab.transportnotes.repository.ItemRepository;
import com.barab.transportnotes.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;


    @Override
    public List<ItemDto> findAllItems() {
        List<Item> items = itemRepository.findAll();

        return items.stream().map(this::mapToItemDto).collect(Collectors.toList());
    }

    private ItemDto mapToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setComment(item.getComment());
        return itemDto;
    }

    private UserDto mapToUseDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setId(user.getId());
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());
        return userDto;
    }
}
