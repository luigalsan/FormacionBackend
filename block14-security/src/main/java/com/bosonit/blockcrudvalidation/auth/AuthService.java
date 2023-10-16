package com.bosonit.blockcrudvalidation.auth;


import com.bosonit.blockcrudvalidation.config.ApplicationConfig;
import com.bosonit.blockcrudvalidation.entity.Persona;
import com.bosonit.blockcrudvalidation.entity.Role;
import com.bosonit.blockcrudvalidation.jwt.JwtService;
import com.bosonit.blockcrudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    @Autowired
    PersonaRepository personRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationConfig applicationConfig;



    public AuthResponse login(LoginRequest request){
        UserDetails user = personRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Usuario no encontrado")
        );
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request, boolean isAdmin){
        String encryptedPassword = applicationConfig.passwordEncoder().encode(request.getPassword());
        Role role = Role.USER;
        if (isAdmin) role = Role.ADMIN;
        Persona person = Persona.builder()
                .username(request.getUsername())
                .password(encryptedPassword)
                .name(request.getName())
                .surname(request.getSurname())
                .company_email(request.getCompany_email())
                .personal_email(request.getPersonal_email())
                .city(request.getCity())
                .active(request.isActive())
                .created_date(request.getCreated_date())
                .imagen_url(request.getImagen_url())
                .termination_date(request.getTermination_date())
                .role(role)
                .build();
        personRepository.save(person);

        return AuthResponse.builder()
                .token(jwtService.getToken(person))
                .build();
    }
}
