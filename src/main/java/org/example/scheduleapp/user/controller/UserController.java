package org.example.scheduleapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.common.consts.Const;
import org.example.scheduleapp.user.dto.UserResponseDto;
import org.example.scheduleapp.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me") // 내 정보 조회
    public ResponseEntity<UserResponseDto> getMe(@SessionAttribute(name = Const.LOGIN_USER) Long id) {

        userService.getMe(id);
    }

}
