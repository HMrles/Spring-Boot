/**
 * 
 */
package com.hmrles.reservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hmrles.reservations.modelo.Cliente;

/**
 * Repositorio para la entidad de {@linkplain Cliente}
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, String> {

	/**
	 * M&eacute;todo para buscar los clientes por su apellido
	 * 
	 * @param apellido
	 * @return List<Cliente> 
	 */
	public List<Cliente> findByApellido(String apellido);

	/**
	 * M&eacute;todo que busca un cliente por su identificaci&oacute;n
	 * 
	 * @param identificacion
	 * @return Cliente
	 */
	public Cliente findByIdentificacion(String identificacion);

	/**
	 * M&eacute;todo para buscar un cliente por su cuenta de email
	 * 
	 * @param email
	 * @return Cliente
	 */
	@Query("Select c from Cliente c where c.email like %:email")
	public Cliente findByEmailAccount(@Param("email") String email);

}
