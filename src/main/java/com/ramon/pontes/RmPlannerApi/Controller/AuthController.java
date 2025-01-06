package com.ramon.pontes.RmPlannerApi.Controller;

import com.ramon.pontes.RmPlannerApi.dto.request.LoginRequest;
import com.ramon.pontes.RmPlannerApi.dto.request.RegisterRequest;
import com.ramon.pontes.RmPlannerApi.dto.response.LoginResponse;
import com.ramon.pontes.RmPlannerApi.infra.security.TokenService;
import com.ramon.pontes.RmPlannerApi.model.User.User;
import com.ramon.pontes.RmPlannerApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@RequestBody LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> authRegister(@RequestBody RegisterRequest data) {
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.email(), data.account_type());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
