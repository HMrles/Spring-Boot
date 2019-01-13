package com.hmrles.reservations.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmrles.reservations.modelo.Cliente;
import com.hmrles.reservations.modelo.Reserva;
import com.hmrles.reservations.repository.ReservaRepository;

/**
 * Clase para definir los servicios de reserva
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 */
@Service
@Transactional(readOnly = true)
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	/**
	 * M&eacute;todo para realizar la operaci&oacute;n de guardar una reserva
	 * 
	 * @param reserva
	 * @return Reserva
	 */
	@Transactional
	public Reserva create(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}

	/**
	 * M&eacute;todo para realizar la operaci&oacute;n de actualizar una reserva
	 * 
	 * @param reserva
	 * @return Reserva
	 */
	@Transactional
	public Reserva update(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}

	/**
	 * M&eacute;todo para realizar la operaci&oacute;n de eliminar una reserva
	 * 
	 * @param reserva
	 */
	@Transactional
	public void delete(Reserva reserva) {
		this.reservaRepository.delete(reserva);
	}

	/**
	 * M&eacute;todo para consultar todos las reservas
	 * 
	 * @return
	 */
	public List<Reserva> findAll() {
		return this.reservaRepository.findAll();
	}

	/**
	 * M&eacute;todo para consultar las reservas por un cliente en espec&iacute;fico
	 * 
	 * @param cliente
	 * @return List<Reserva> 
	 */
	public List<Reserva> findByCliente(Cliente cliente) {
		return this.reservaRepository.findByCliente(cliente);
	}

	/**
	 * M&eacute;todo para consultar las reservas por la fecha de ingreso en base a un rango
	 * de fechas
	 * 
	 * @param fechaInicio
	 * @param fechaSalida
	 * @return List<Reserva>
	 */
	public List<Reserva> find(Date fechaInicio, Date fechaSalida) {
		return this.reservaRepository.find(fechaInicio, fechaSalida);
	}

	/**
	 * M&eacute;todo para consultar una reserva por su c&oacute;digo de reserva
	 * 
	 * @param codigoReserva
	 * @return Reserva
	 */
	public Reserva findByCodigo(String codigoReserva) {
		return this.reservaRepository.findByCodigo(codigoReserva);
	}

}
