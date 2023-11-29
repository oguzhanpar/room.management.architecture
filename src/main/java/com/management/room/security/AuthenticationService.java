package com.management.room.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.room.business.requests.auth.AuthenticationRequest;
import com.management.room.business.requests.auth.RegisterRequest;
import com.management.room.business.responses.auth.AuthenticationResponse;
import com.management.room.dataAccess.abstracts.UserRepository;
import com.management.room.entities.concretes.Role;
import com.management.room.entities.concretes.User;


@Service
public class AuthenticationService {
	
	private  UserRepository userRepository;
	private  PasswordEncoder passwordEncoder;
	private  JwtService jwtService;
	private  AuthenticationManager authenticationManager;
	
	@Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

	public AuthenticationResponse register(RegisterRequest registerRequest) {
		
		var user = User.builder()
				.firstname(registerRequest.getFirstName())
				.lastname(registerRequest.getLastName())
				.email(registerRequest.getEmail())
				.password(passwordEncoder.encode(registerRequest.getPassword()))
				.role(Role.USER)
				.build();
		
		userRepository.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();

	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		
		User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
	}

}
