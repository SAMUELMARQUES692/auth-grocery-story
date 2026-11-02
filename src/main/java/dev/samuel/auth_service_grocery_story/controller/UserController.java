package dev.samuel.auth_service_grocery_story.controller;

import dev.samuel.auth_service_grocery_story.request.UserRequest;
import dev.samuel.auth_service_grocery_story.response.UserResponse;
import dev.samuel.auth_service_grocery_story.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        UserResponse response = userService.createUser(request);
        URI location = URI.create("api/users/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

}
