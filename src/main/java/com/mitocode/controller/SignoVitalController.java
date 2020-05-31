package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.SignoVital;
import com.mitocode.service.SignoVitalService;

@RestController
@RequestMapping("/signos")
public class SignoVitalController {
	
	@Autowired
	private SignoVitalService service;
	
	@GetMapping
	public ResponseEntity<List<SignoVital>> listar() throws Exception {
		List<SignoVital> lista = service.listar();
		return new ResponseEntity<List<SignoVital>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SignoVital> listarPorId(@PathVariable("id") Integer id) throws Exception{
		SignoVital p = service.listarPorId(id);
		if(p == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<SignoVital>(p, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<SignoVital> registrar(@RequestBody SignoVital request) throws Exception {
		SignoVital response = service.registrar(request);
		return new ResponseEntity<SignoVital>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<SignoVital> modificar(@RequestBody SignoVital request) throws Exception {
		SignoVital response = service.modificar(request);
		return new ResponseEntity<SignoVital>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) throws Exception{
		SignoVital obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<SignoVital>> listarPageable(Pageable pageable) throws Exception{
		Page<SignoVital> pacientes = service.listarPageable(pageable);
		return new ResponseEntity<Page<SignoVital>>(pacientes, HttpStatus.OK);
	}

}
