package com.hmrles.services;

import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;
import com.hmrles.model.Login;
import com.hmrles.model.Password;
import com.hmrles.model.PasswordReset;
import com.hmrles.model.User;

/**
 * <h4>AuthenticationInterface</h4>
 * <p>
 * Interfaz para la autenticacion. Esta interfaz está diseñada para abstraer los
 * metodos necesarios para la autenticacion con AWS Cognito
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public interface Authentication {

	/**
	 * <p>
	 * Crea un nuevo Usuario
	 * </p>
	 * 
	 */
	void createUser(User user) throws Exception;

	/**
	 * <p>
	 * Elimina un usuario. Se require la contraseña
	 * </p>
	 * 
	 * @param userName
	 * @param password
	 */
	void deleteUser(String userName, String password) throws Exception;

	/**
	 * Encontrar un usuario por email
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	User findUserByEmail(String email) throws Exception;

	/**
	 * <p>
	 * Actualizar usuario
	 * </p>
	 * 
	 * @param newInfo
	 * @throws AWSCognitoIdentityProviderException
	 */
	void updateUser(User newInfo) throws Exception;

	/**
	 * <p>
	 * Login para iniciar session
	 * </p>
	 * 
	 * @param userName
	 * @param password
	 * @return un Login object 
	 */
	Login userLogin(String userName, String password) throws Exception;

	/**
	 * <p>
	 * Cerrar session
	 * </p>
	 * 
	 * @param userName
	 */
	void userLogout(String userName) throws Exception;

	/**
	 * <p>
	 * Cambiar password temporal por un permanente
	 * </p>
	 */
	public void changeTemporaryPassword(final Password passwordRequest) throws Exception;

	/**
	 * Soporte para restablecer la contraseña del usuario en caso de que se haya olvidado.
	 * 
	 * @param userName
	 * @param resetCode
	 * @throws Exception
	 */
	void forgotPassword(String userName) throws Exception;

	/**
	 * <p>
	 * Obtener la información asociada al usuario.
	 * </p>
	 * 
	 * @param userName the name of the user
	 * @return un User object
	 */
	User getUserInfo(String userName) throws Exception; 

	/**
	 * <p>
	 * Determina si un usuario con nombre de usuario existe en la base de datos de inicio de sesión.
	 * </p>
	 * 
	 * @param userName
	 * @return true if the user exists, false otherwise.
	 */
	boolean hasUser(String userName);

	/**
	 * <p>
	 * Restablecer la contraseña de un usuario utilizando un código de autenticación.
	 * </p>
	 * 
	 * @param resetRequest
	 * @throws Exception
	 */
	void resetPassword(PasswordReset resetRequest) throws Exception;

	/**
	 * <p>
     * Cambia la contraseña de un usuario registrado. A diferencia de la contraseña olvidada
     * Esto no requiere un código de autenticación.
	 * </p>
	 * 
	 * @param passwordRequest
	 * @throws Exception
	 */
	void changePassword(Password passwordRequest) throws Exception;

	/**
	 * <p>
	 * Cambiar la dirección de correo electrónico de un usuario registrado.
	 * </p>
	 * 
	 * @param userName
	 * @param newEmailAddr
	 * @throws Exception
	 */
	void changeEmail(String userName, String newEmailAddr) throws Exception;

}
