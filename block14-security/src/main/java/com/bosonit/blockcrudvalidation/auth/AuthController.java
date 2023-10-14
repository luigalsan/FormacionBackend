package com.bosonit.blockcrudvalidation.auth;


import com.bosonit.blockcrudvalidation.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    PersonaRepository personaRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest, @RequestParam boolean isAdmin){
        return ResponseEntity.ok(authService.register(registerRequest, isAdmin));
    }

    @GetMapping("/hello")
    public String hello(){
        return "accediendo";
    }
}
