package com.meldia.restapi.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meldia.restapi.payload.VideogameRequest;
import com.meldia.restapi.payload.VideogameResponse;
import com.meldia.restapi.service.VideogameServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/videogamestore")
public class VideogameController {
	
	@Autowired
	public VideogameServiceImpl service;
	
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<?> allProducts() {
		VideogameResponse response = service.allProducts();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(response);
		
	}
	
	@PostMapping(value = "/add", produces = "application/json")
	public ResponseEntity<VideogameResponse> addProduct(@RequestBody VideogameRequest rq) {		
		VideogameResponse response = service.addProduct(rq);		
		if(response.getResponseCode().equals("1")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 			 .body(response);
		}		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
							 .body(response);
		
	}
	
	@PostMapping(value = "/update", produces = "application/json")
	public ResponseEntity<VideogameResponse> updateProduct(@RequestBody VideogameRequest rq) {		
		VideogameResponse response = service.updateProduct(rq);		
		if(response.getResponseCode().equals("1")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 			 .body(response);
		}		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
							 .body(response);
		
	}
	
	@PostMapping(value = "/inactivate", produces = "application/json")
	public ResponseEntity<VideogameResponse> inactivateProduct(@RequestBody VideogameRequest rq) {		
		VideogameResponse response = service.inactivateProduct(rq);		
		if(response.getResponseCode().equals("1")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 			 .body(response);
		}		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
							 .body(response);
		
	}

}
