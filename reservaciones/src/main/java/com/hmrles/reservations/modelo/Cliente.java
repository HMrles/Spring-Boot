/**
 * 
 */
package com.hmrles.reservations.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * Clase que mapea la entidad <b>CLIENTE</b> 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */
@Data
@Entity
@Table(name = "cliente")
@NamedQuery(name="Cliente.findByIdentificacion", query="Select c from Cliente c where c.identificacion = ?1")
public class Cliente {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String idCliente;
	private String nombre;
	private String apellido;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String email;
	
	@OneToMany(mappedBy="cliente")
	private Set<Reserva> reservas;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
}
