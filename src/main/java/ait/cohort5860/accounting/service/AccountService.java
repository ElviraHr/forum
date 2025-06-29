package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.UserDto;
import ait.cohort5860.accounting.dto.UserUpdateDto;

public interface AccountService {

    UserDto register(UserDto userDto);
    UserDto login(String login, String password);
    UserDto deleteUser(String login);
    UserDto updateUser(UserUpdateDto userDto);
    UserDto addRole(String login, String role);
    UserDto deleteRole(String login, String role);
    void changePassword();
    UserDto getUser(String userDto);
}
