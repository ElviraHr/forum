package ait.cohort5860.accounting.controller;

import ait.cohort5860.accounting.dto.UserDto;
import ait.cohort5860.accounting.dto.UserUpdateDto;
import ait.cohort5860.accounting.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

private final AccountService accountService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody UserDto userDto) {
        return accountService.register(userDto);
    }

    @PostMapping("/login")
    public UserDto login(String login, String password) {
        return accountService.login(login, password);
    }

    @DeleteMapping("/user/{login}")
    public UserDto deleteUser(@PathVariable String login) {
        return accountService.deleteUser(login);
    }

    @PatchMapping("/user/{user}")
    public UserDto updateUser(@PathVariable UserUpdateDto userUpdateDto) {
        return accountService.updateUser(userUpdateDto);
    }

    @PatchMapping("/user/{login}/role/{role}")
    public UserDto addRole(String login, String role) {
        return accountService.addRole(login, role);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public UserDto deleteRole(String login, String role) {
        return accountService.deleteRole(login, role);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword() {
        accountService.changePassword();
    }

    @GetMapping("/user/{login}")
    public UserDto getUser(@PathVariable String login) {
        return accountService.getUser(login);
    }
}
