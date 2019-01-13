/**
 * 
 */
package com.hmrles.reservations.controller;

import java.util.List;

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

import com.hmrles.reservations.dto.ReservaDTO;
import com.hmrles.reservations.modelo.Cliente;
import com.hmrles.reservations.modelo.Reserva;
import com.hmrles.reservations.services.ClienteService;
import com.hmrles.reservations.services.ReservaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que representa el servicio web de Reserva
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */
@RestController
@RequestMapping("/api/reserva")
@Api(tags = "reserva")
public class ReservaController {

	/**
	 * Referencia al servicio de reservaService
	 */
	@Autowired
	private ReservaService reservaService;
	
	/**
	 * Referencia al servicio de clienteService
	 */
	@Autowired
	private ClienteService clienteService;



	@PostMapping
	@ApiOperation(value = "Crear Reserva", notes = "Servicio para crear un nueva reserva")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reserva creada correctamente"),
			@ApiResponse(code = 400, message = "Solicitud Inválida") })
	public ResponseEntity<Reserva> createReserva(@RequestBody ReservaDTO reservaDTO) {
		Cliente cliente = clienteService.findByIdentificacion(reservaDTO.getCliente().getIdentificacion());
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setCodigo(reservaDTO.getCodigo());
		reserva.setFechaIngreso(reservaDTO.getFechaIngreso());
		reserva.setFechaSalida(reservaDTO.getFechaSalida());
		reserva.setCantidadPersonas(reservaDTO.getCantidadPersonas());
		reserva.setDescripcion(reservaDTO.getDescripcion());

		return new ResponseEntity<>(this.reservaService.create(reserva), HttpStatus.CREATED);
	}

	@PutMapping("/{codigoReserva}")
	@ApiOperation(value = "Actualizar Reserva", notes = "Servicio para actualizar una reserva")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reserva actualizada correctamente"),
			@ApiResponse(code = 404, message = "Reserva no encontrada") })
	public ResponseEntity<Reserva> updateReserva(@PathVariable("codigoReserva") String codigoReserva,
			ReservaDTO reservaVO) {

		Reserva reserva = this.reservaService.findByCodigo(codigoReserva);
		if (reserva == null) {
			return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
		} else {
			Cliente cliente = clienteService.findByIdentificacion(reservaVO.getCliente().getIdentificacion());
			reserva.setCliente(cliente);
			reserva.setFechaIngreso(reservaVO.getFechaIngreso());
			reserva.setFechaSalida(reservaVO.getFechaSalida());
			reserva.setCantidadPersonas(reservaVO.getCantidadPersonas());
			reserva.setDescripcion(reservaVO.getDescripcion());
		}
		return new ResponseEntity<>(this.reservaService.update(reserva), HttpStatus.OK);
	}

	@DeleteMapping("/{codigoReserva}")
	@ApiOperation(value = "Eliminar Reserva", notes = "Servicio para eliminar una reserva")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reserva eliminada correctamente"),
			@ApiResponse(code = 404, message = "Reserva no encontrada") })
	public void removeCliente(@PathVariable("codigoReserva") String codigoReserva) {
		Reserva reserva = this.reservaService.findByCodigo(codigoReserva);
		if (reserva != null) {
			this.reservaService.delete(reserva);
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Reservas", notes = "Servicio para listar todas las reservas")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reservas encontrados"),
			@ApiResponse(code = 404, message = "Reservas no encontrados") })
	public ResponseEntity<List<Reserva>> findAll() {
		return ResponseEntity.ok(this.reservaService.findAll());
	}
}
