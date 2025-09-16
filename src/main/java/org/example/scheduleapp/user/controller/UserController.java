package org.example.scheduleapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.common.consts.Const;
import org.example.scheduleapp.user.dto.UpdateUserRequestDto;
import org.example.scheduleapp.user.dto.UserResponseDto;
import org.example.scheduleapp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me") // 내 정보 조회
    public ResponseEntity<UserResponseDto> getMe(@SessionAttribute(name = Const.LOGIN_USER) Long id) {

        UserResponseDto userResponseDto = userService.getMe(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping ("/me")// 내 정보 수정
    public ResponseEntity<UserResponseDto> updateUser(
            @SessionAttribute(name = Const.LOGIN_USER) Long id,
            @RequestBody UpdateUserRequestDto request) {

        UserResponseDto userResponseDto = userService.updateUser(id, request.getPassword());
    }

}
