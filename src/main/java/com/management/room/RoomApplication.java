package com.management.room;

import java.util.HashMap;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.management.room.core.utilities.exceptions.BusinessException;
import com.management.room.core.utilities.exceptions.ProblemDetails;
import com.management.room.core.utilities.exceptions.ValidationProblemDetails;
import com.management.room.dataAccess.abstracts.RoomStatusRepository;
import com.management.room.entities.concretes.RoomStatus;



@SpringBootApplication
@RestControllerAdvice
public class RoomApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RoomApplication.class, args);
		
	}
	
	
	
	@Bean
	public ModelMapper getModelMapper() {
		
		return new ModelMapper();
		
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessExeception) {
		
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessExeception.getMessage());
		
		return problemDetails;
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setMessage("VALIDATION.EXCEPTION");
		validationProblemDetails.setValidationErrors(new HashMap<String, String>());
		
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			
			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
			
		}
		
		return validationProblemDetails;
		
	}
	
	@Bean
	CommandLineRunner runner(RoomStatusRepository roomStatusRepository) {
		
		return args ->{
			
			
			System.out.println("ROOM STATUS VERİLERİ KONTROL EDİLİYOR");
			
			 Optional<RoomStatus> existingStatus = roomStatusRepository.findById(1L);
			    
			    if (!existingStatus.isPresent()) {
			    
			        RoomStatus newStatus = RoomStatus.builder().name("TEMİZ").build();
			        roomStatusRepository.save(newStatus);
			    }
			    
			 Optional<RoomStatus> existingStatus1 = roomStatusRepository.findById(2L);
			    
			    if (!existingStatus1.isPresent()) {
			    
			        RoomStatus newStatus = RoomStatus.builder().name("KİRLİ").id(2L).build();
			        roomStatusRepository.save(newStatus);
			    }
			    
			  Optional<RoomStatus> existingStatus2 = roomStatusRepository.findById(3L);
			    
			    if (!existingStatus2.isPresent()) {
			    
			        RoomStatus newStatus = RoomStatus.builder().name("ARIZALI").id(3L).build();
			        roomStatusRepository.save(newStatus);
			    }
		};
		
	}


}
