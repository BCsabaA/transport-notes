package com.barab.transportnotes.service.impl;

import com.barab.transportnotes.dto.UserDto;
import com.barab.transportnotes.entity.Role;
import com.barab.transportnotes.entity.User;
import com.barab.transportnotes.repository.RoleRepository;
import com.barab.transportnotes.repository.UserRepository;
import com.barab.transportnotes.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // TODO: encrypt the password with Spring Security
        user.setPassword(userDto.getPassword());
        user.setCompany(userDto.getCompany());

        Role role = roleRepository.findByName("ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUseDto).toList();
    }

    private UserDto mapToUseDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setCompany(user.getCompany());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ADMIN");
        return roleRepository.save(role);
    }
}
