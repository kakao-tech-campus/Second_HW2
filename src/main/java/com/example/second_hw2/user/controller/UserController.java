package com.example.second_hw2.user.controller;

import com.example.second_hw2.common.consts.Const;
import com.example.second_hw2.user.dto.request.UserSaveRequestDto;
import com.example.second_hw2.user.dto.request.UserUpdateRequestDto;
import com.example.second_hw2.user.dto.response.UserResponseDto;
import com.example.second_hw2.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserSaveRequestDto dto) {
        return ResponseEntity.ok(userService.save(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PutMapping("/users/me")
    public ResponseEntity<UserResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @RequestBody UserUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(userService.update(userId, dto));
    }

    @DeleteMapping("/users/me")
    public void delete(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        userService.deleteById(userId);
        session.invalidate();
    }
}
