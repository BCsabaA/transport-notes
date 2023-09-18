package com.barab.transportnotes.service;

import com.barab.transportnotes.dto.UserDto;
import com.barab.transportnotes.entity.User;

import java.util.List;

public interface UserService {
    public void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
