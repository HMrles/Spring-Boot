/**
 * 
 */
package com.hmrles.reservations.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hmrles.reservations.modelo.Cliente;
import com.hmrles.reservations.modelo.Reserva;

/**
 * Repositorio para la entidad de {@linkplain Reserva}
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */
public interface ReservaRepository extends JpaRepository<Reserva, String> {

	/**
	 * M&eacute;todo para consultar las reservaciones por cliente
	 * 
	 * @param cliente
	 * @return List<Reserva> 
	 */
	@Query("Select r from Reserva r where r.cliente =:cliente")
	public List<Reserva> findByCliente(Cliente cliente);
	
	/**
	 * M&eacute;todo para consultar las reservaciones por rango de fechas
	 * 
	 * @param fechaInicio
	 * @param fechaSalida
	 * @return List<Reserva> 
	 */
	@Query("Select r from Reserva r where r.fechaIngreso =:fechaInicio  and r.fechaSalida =:fechaSalida")
	public List<Reserva> find(@Param("fechaInicio") Date fechaInicio, @Param("fechaSalida") Date fechaSalida);

	/**
	 * M&eacute;todo para buscar una reserva por su c&oacute;digo
	 * @param codigoReserva
	 * @return Reserva
	 */
	public Reserva findByCodigo(String codigoReserva);
}
