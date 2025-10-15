package controller;

import com.example.demo.*;
import repository.UserRepository;
import dto.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> getUserInfo() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(userEmail);

        if (user.isPresent()) {
            UserDetailsResponse response = new UserDetailsResponse(user.get().getUsername(), user.get().getEmail());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }
}