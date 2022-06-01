package com.residencia.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

	@Autowired 
	private AtividadeService atividadeService;
	
	@GetMapping
	@Operation(summary="Listar todas as atividades", description  = "Listagem de atividades")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	public ResponseEntity<List<Atividade>> findAllAtividade() {
		List<Atividade> atividade = atividadeService.findAllAtividade();
		if (null == atividade)
			throw new NoSuchElementFoundException("Nenhuma atividade encontrada");
		else
		    return new ResponseEntity<>(atividadeService.findAllAtividade(), HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	@Operation(summary="Retornar uma atividade", description  = "Atividade")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("Não foi encontrada Atividade com o id " + id);
		else
			return new ResponseEntity<>(atividadeService.findAtividadeById(id), HttpStatus.OK);
	} 
	
	@PostMapping
	@Operation(summary="Inserir os dados de atividade", description  = "Atividade adicionada")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	public ResponseEntity<Atividade> saveAtividade(@RequestBody @Valid Atividade atividade) {
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary="Atualizar os dados de atividade", description  = "Atividade atualizada")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Atualiza com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	public ResponseEntity<Atividade> updateAtividade(@RequestBody @Valid Atividade atividade) {
		return new ResponseEntity<>(atividadeService.updateAtividade(atividade), HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	@Operation(summary="Remover uma atividade", description  = "Atividade removida")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	public ResponseEntity<String> deleteAtividade(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("Não foi possivel deletar a Atividade com o id " + id);
		else
			atividadeService.deleteAtividade(id);
			return new ResponseEntity<>("", HttpStatus.OK);
	
	}
 
	
}
