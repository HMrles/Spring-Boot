/**
 * 
 */
package com.hmrles.reservations.dto;

import java.util.Date;

import lombok.Data;

/**
 * Clase que representa la tabla reserva
 * @author HMrles
 *
 */
@Data
public class ReservaDTO {
	private String codigo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private int cantidadPersonas;
	private String descripcion;
	private ClienteDTO cliente;
}
