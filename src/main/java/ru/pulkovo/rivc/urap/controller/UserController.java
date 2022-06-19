package ru.pulkovo.rivc.urap.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import ru.pulkovo.rivc.urap.dto.user.*;
import ru.pulkovo.rivc.urap.service.UserService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public UserInfoDto getUser(@PathVariable("id") UUID id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}/{newStatus}")
    public UserStatisticDto updateStatus(@PathVariable("id") UUID id,
                                         @PathVariable("newStatus") UserStatusDto newStatus) {
        return userService.updateStatus(id, newStatus);
    }

    @GetMapping("/statistic")
    public ServerStatisticDto getStatistic(@RequestParam(value = "status", required = false) UserStatusDto status,
                                           @RequestParam(value = "adult", required = false) Boolean adult) {
        return userService.getStatistic(status, adult);
    }
}