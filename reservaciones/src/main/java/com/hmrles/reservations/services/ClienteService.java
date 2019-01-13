/**
 * 
 */
package com.hmrles.reservations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmrles.reservations.modelo.Cliente;
import com.hmrles.reservations.repository.ClienteRepository;

/**
 * Clase para definir los servicios de cliente
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */
@Service
@Transactional(readOnly = true)
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * M&eacute;todo para realizar la operaci&oacute;n de guardar un cliente
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	@Transactional
	public Cliente create(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	/**
	 * M&eacute;todo para realizar la operaci&oacute;n de actualizar un cliente
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	@Transactional
	public Cliente update(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	/**
	 * M&eacute;todo para realizar la operaci&oacute;n de eliminar un cliente
	 * 
	 * @param cliente
	 */
	@Transactional
	public void delete(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}

	/**
	 * M&eacute;todo para consultar un cliente por su identificaci&oacute;n
	 * 
	 * @param identificacion
	 * @return
	 */
	public Cliente findByIdentificacion(String identificacion) {
		return this.clienteRepository.findByIdentificacion(identificacion);
	}

	/**
	 * M&eacute;todo para consultar todos los clientes
	 * 
	 * @return List<Cliente>
	 */
	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	/**
	 * M&eacute;todo para buscar los clientes por su apellido
	 * 
	 * @param apellido
	 * @return
	 */
	public List<Cliente> findByApellidoCli(String apellido) {
		return this.clienteRepository.findByApellido(apellido);
	}

	/**
	 * M&eacute;todo para buscar un cliente por su cuenta de email
	 * 
	 * @param email
	 * @return Cliente
	 */
	public Cliente findByEmailAccount(String email) {
		return this.findByEmailAccount(email);
	}

}
