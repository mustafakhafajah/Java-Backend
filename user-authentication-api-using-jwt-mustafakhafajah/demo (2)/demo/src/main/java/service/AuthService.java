package service;

import com.example.demo.*;
import com.example.demo.*;
import com.example.demo.*;
import com.example.demo.*;
import com.example.demo.*;
import dto.LoginRequest;
import dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import model.RefreshToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import repository.RefreshTokenRepository;
import repository.UserRepository;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public TokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String username = request.getEmail();
        String accessToken = jwtService.generateAccessToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        RefreshToken rt = new RefreshToken();
        rt.setToken(refreshToken);
        rt.setUser(userRepository.findByEmail(username).orElseThrow()); // تأكد من وجود المستخدم
        rt.setExpiryDate(Instant.now().plusSeconds(604800)); // 7 أيام
        refreshTokenRepository.save(rt);

        return new TokenResponse(accessToken, refreshToken);
    }

    public void logout(String refreshToken) {
        refreshTokenRepository.findByToken(refreshToken).ifPresent(refreshTokenRepository::delete);
    }

    public TokenResponse refreshToken(String refreshToken) {
        Optional<RefreshToken> storedToken = refreshTokenRepository.findByToken(refreshToken);

        if (storedToken.isPresent() && storedToken.get().getExpiryDate().isAfter(Instant.now())) {
            String username = storedToken.get().getUser().getEmail();

            refreshTokenRepository.delete(storedToken.get());

            String newAccessToken = jwtService.generateAccessToken(username);
            String newRefreshToken = jwtService.generateRefreshToken(username);

            RefreshToken newRt = new RefreshToken();
            newRt.setToken(newRefreshToken);
            newRt.setUser(storedToken.get().getUser());
            newRt.setExpiryDate(Instant.now().plusSeconds(604800));
            refreshTokenRepository.save(newRt);

            return new TokenResponse(newAccessToken, newRefreshToken);
        } else {
            throw new RuntimeException("Invalid or expired refresh token");
        }
    }
}