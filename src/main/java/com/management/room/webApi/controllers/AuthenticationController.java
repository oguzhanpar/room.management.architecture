package com.management.room.webApi.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.room.business.requests.auth.AuthenticationRequest;
import com.management.room.business.requests.auth.RegisterRequest;
import com.management.room.business.responses.auth.AuthenticationResponse;
import com.management.room.security.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
		
		return ResponseEntity.ok(authenticationService.register(registerRequest));
		
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
		return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));

	}

}
