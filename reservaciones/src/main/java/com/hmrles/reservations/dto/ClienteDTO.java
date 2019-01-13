/**
 * 
 */
package com.hmrles.reservations.dto;

import lombok.Data;

/**
 * Clase que representa la tabla Cliente
 * @author HMrles
 *
 */
@Data
public class ClienteDTO {
	private String nombre;
	private String apellido;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String email;
		
}
