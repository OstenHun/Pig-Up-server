package com.team1.pigup.controller;

import com.team1.pigup.dto.LoginRequest;
import com.team1.pigup.dto.SignupRequest;
import com.team1.pigup.entity.User;
import com.team1.pigup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 완료!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String token = userService.login(request);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        // 1. SecurityContextHolder에서 현재 인증된 사용자 이름 가져오기
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. 유저 정보 조회
        User user = userService.findByUsername(username);

        // 3. 응답 만들기
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

}
