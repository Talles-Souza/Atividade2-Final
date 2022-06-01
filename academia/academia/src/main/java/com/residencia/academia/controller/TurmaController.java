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

import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.TurmaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	@Operation(summary = "Listar todas as turmas", description = "Listagem de turmas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<List<Turma>> findAllTurma() {
		List<Turma> turmas = turmaService.findAllTurma();
		if (null == turmas)
			throw new NoSuchElementFoundException("Nenhuma turma encontrada");
		else
			return new ResponseEntity<>(turmaService.findAllTurma(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	@Operation(summary = "Retornar uma turma", description = "Turma")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<Turma> findTurmaById(@PathVariable  Integer id) {
		Turma turma = turmaService.findTurmaById(id);
		if (null == turma)
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o id " + id);
		else
			return new ResponseEntity<>(turmaService.findTurmaById(id), HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	@Operation(summary = "Retornar uma turma", description = "Turma")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<TurmaDTO> findTurmaDTOById(@PathVariable Integer id) {
		TurmaDTO turmaDTO = turmaService.findTurmaDTOById(id);
		if (null == turmaDTO)
			throw new NoSuchElementFoundException("Não foi encontrada  a Turma com o id " + id);
		else
			return new ResponseEntity<>(turmaDTO, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Inserir os dados de turma", description = "Turma adicionada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<Turma> saveTurma(@RequestBody @Valid Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	@Operation(summary = "Inserir os dados de turma", description = "Turma adicionada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<TurmaDTO> saveTurmaDTO(@RequestBody TurmaDTO turmaDto) {
		TurmaDTO turmaDTO = turmaService.saveTurmaDTO(turmaDto);
		return new ResponseEntity<>(turmaDTO, HttpStatus.CREATED);
	}
	
	@PutMapping
	@Operation(summary = "Atualizar os dados de turma", description = "Turma atualizada")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<Turma> updateTurma(@RequestBody @Valid Turma turma) {
		Turma turma1 = turmaService.findTurmaById(turma.getIdTurma());
		if (null == turma1)
			throw new NoSuchElementFoundException("Não foi possivel atualizar a Turma ");
		else
			return new ResponseEntity<>(turmaService.updateTurma(turma), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover uma turma", description = "Turma removida")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<String> deleteTurma(@PathVariable Integer id) {
		Turma turma = turmaService.findTurmaById(id);
		if (null == turma)
			throw new NoSuchElementFoundException("Não foi possivel deletar a Turma com o id " + id);
		else
			turmaService.deleteTurma(id);
		return new ResponseEntity<>("", HttpStatus.OK);

	}

	/*
	 * @DeleteMapping("/{id}") public ResponseEntity<String>
	 * deleteTurmaComConferencia(@PathVariable Integer id) { Boolean verificacao =
	 * turmaService.deleteTurmaComConferencia(id); if(verificacao) return new
	 * ResponseEntity<>("", HttpStatus.OK); else throw new
	 * NoSuchElementFoundException("Não foi possível excluir a Turma, " +
	 * "pois não foi " + "encontrada uma turma com o id " + id);
	 * 
	 * }
	 */

}
