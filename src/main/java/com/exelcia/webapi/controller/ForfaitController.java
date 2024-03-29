package com.exelcia.webapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exelcia.webapi.exceptions.ResourceNotFoundException;
import com.exelcia.webapi.model.Forfait;
import com.exelcia.webapi.repository.ForfaitRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value="Gestion des forfaits")
public class ForfaitController {
	
	@Autowired
	ForfaitRepository repo;
	
	//@RequestMapping(value="v2/forfaits", method = RequestMethod.GET, )
	//@GetMapping(value = "/forfaits", params = "1")
	//@GetMapping(value = "/forfaits", headers = "X-API-VERSION=1")
	@GetMapping(value = "/forfaits")
	@ApiOperation(value="Récupère tout les forfaits", response = List.class)
	public List<Forfait> getAllForfait(){
		return repo.findAll();
	}
	
	@ApiOperation(value="Récupère un forfait par ID", response = Forfait.class)
	@ApiResponses(value= {
			@ApiResponse(code=404, message = "Pas de forfait pour cet ID")
	})
	@GetMapping("/forfaits/{id}")
	public Forfait getForfaitById(@PathVariable(value="id")Long forfaitId) {
		
		return repo.findById(forfaitId).orElseThrow(
				() -> new ResourceNotFoundException("Forfait", "id", forfaitId));
	}
	
	@PostMapping("/forfaits")
	public Forfait createForfait(@Valid @RequestBody Forfait forfait) {
		return repo.save(forfait);
	}
	
	@DeleteMapping("/forfaits/{id}")
	public ResponseEntity<Forfait> deleteForfaitById(@PathVariable(value="id")Long forfaitId) {
		Forfait forfait = repo.findById(forfaitId).orElseThrow(
				() -> new ResourceNotFoundException("Forfait", "id", forfaitId));
		repo.delete(forfait);
		
		return ResponseEntity.ok(forfait);
		
	}
	
	

}
