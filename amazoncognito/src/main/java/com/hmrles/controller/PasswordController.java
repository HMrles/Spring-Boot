package com.hmrles.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.google.common.base.Strings;
import com.hmrles.model.Password;
import com.hmrles.model.PasswordReset;
import com.hmrles.services.Authentication;
import com.hmrles.util.AppConstants;

/**
 * <h4>PasswordController</h4>
 * <p>
 * Controlador para manejar las peticiones del reseteo de Password </p>
 *
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
@Controller
public class PasswordController {
	
    @Autowired
    Authentication autenticacionService;

	/**
	 * Metodo para restabler una contraseña
	 * @param oldPassword    password existente
	 * @param newPassword    nuevo password
	 * @param verifyPassword nuevo password
	 * @param redirect
	 * @return
	 */
	@PostMapping("reset_password_form")
	public String changePasswordForm(@RequestParam("user_name") final String userName,
			@RequestParam("reset_code") final String resetCode, @RequestParam("new_password") final String newPassword,
			@RequestParam("verify_password") final String verifyPassword, RedirectAttributes redirect) {
		boolean hasErrors = false;
		String userNameArg = null;
		String resetCodeArg = null;
		String newPasswordArg = null;
		String verifyPasswordArg = null;

		String nextPage = "redirect:reset_password";
		
		if (Strings.isNullOrEmpty(userName)) {
			redirect.addFlashAttribute("user_name_error", "Ingrese Usuario");
			hasErrors = true;
		} else {
			userNameArg = userName.trim();
			redirect.addFlashAttribute("user_name_val", userNameArg);
		}
		if (!hasErrors) {
			if (Strings.isNullOrEmpty(resetCode)) {
				redirect.addFlashAttribute("reset_code_error", "Ingrese Codigo para restablecer");
				hasErrors = true;
			} else {
				resetCodeArg = resetCode.trim();
			}
		}
		if (!hasErrors) {
			if (Strings.isNullOrEmpty(newPassword)) {
				redirect.addFlashAttribute("new_password_error", "Ingrese la nueva contraseña");
				hasErrors = true;
			} else {
				newPasswordArg = newPassword.trim();
				if (newPasswordArg.length() < AppConstants.PASSWORD_MIN_LENGTH) {
					redirect.addFlashAttribute("new_password_error",
							"La nueva contraseña debe de tener por lo menos " + AppConstants.PASSWORD_MIN_LENGTH + " caracteres");
					hasErrors = true;
				}
			}
		}
		if (!hasErrors) {
			if (Strings.isNullOrEmpty(verifyPassword)) {
				redirect.addFlashAttribute("verify_password_error", "Ingrese su nueva contraseña nuevamente");
				hasErrors = true;
			} else {
				verifyPasswordArg = verifyPassword.trim();
				if (!newPasswordArg.equals(verifyPasswordArg)) {
					redirect.addFlashAttribute("verify_password_error", "Las contraseñas no coinciden");
					hasErrors = true;
				}
			}
		}

		if (!hasErrors) {
			try {
				PasswordReset resetRequest = new PasswordReset(userNameArg, resetCodeArg, newPasswordArg);
				autenticacionService.resetPassword(resetRequest);
				redirect.addFlashAttribute("user_name_val", userName);
				nextPage = "redirect:/";
			} catch (InvalidPasswordException e) {
				redirect.addFlashAttribute("reset_password_error", "Contraseña incorrecta: " + e.getErrorMessage());
			} catch (Exception e) {
				redirect.addFlashAttribute("reset_password_error",
						"Error al restablcer la contraseña " + e.getLocalizedMessage());
			}
		}
		return nextPage;
	}
	
	
	/**
	 * 
     * Metodo para cambiar una contraseña
	 * @param oldPassword    password existente
	 * @param newPassword    nuevo password
	 * @param verifyPassword nuevo password
	 * @param redirect
	 * @return
	 */
	@PostMapping("change_password_form")
	public String changePasswordForm(@RequestParam("user_name") final String userName,
			@RequestParam("old_password") final String oldPassword,
			@RequestParam("new_password") final String newPassword,
			@RequestParam("verify_password") final String verifyPassword, RedirectAttributes redirect,
			HttpServletRequest request) {
		boolean hasErrors = false;
		String userNameArg = null;
		String oldPasswordArg = null;
		String newPasswordArg = null;
		String verifyPasswordArg = null;
		
		String nextPage = "redirect:change_password";
		if (Strings.isNullOrEmpty(userName)) {
			redirect.addFlashAttribute("user_name_error", "Ingrese su usuario");
			hasErrors = true;
		} else {
			userNameArg = userName.trim();
			redirect.addFlashAttribute("user_name_val", userNameArg);
		}
		if (!hasErrors) {
			if (Strings.isNullOrEmpty(oldPassword)) {
				redirect.addFlashAttribute("old_password_error", "Ingrese su contraseña actual");
				hasErrors = true;
			} else {
				oldPasswordArg = oldPassword.trim();
			}
		}
		if (!hasErrors) {
			if (Strings.isNullOrEmpty(newPassword)) {
				redirect.addFlashAttribute("new_password_error", "Ingrese una nueva contraseña");
				hasErrors = true;
			} else {
				newPasswordArg = newPassword.trim();
				if (newPasswordArg.length() < AppConstants.PASSWORD_MIN_LENGTH) {
					redirect.addFlashAttribute("new_password_error",
							"La contraseña debe tener por lo menos " + AppConstants.PASSWORD_MIN_LENGTH + " caracteres");
					hasErrors = true;
				}
			}
		}
		if (!hasErrors) {
			if (Strings.isNullOrEmpty(verifyPassword)) {
				redirect.addFlashAttribute("verify_password_error", "Por favor ingrese su nueva contrasena nuevamente");
				hasErrors = true;
			} else {
				verifyPasswordArg = verifyPassword.trim();
				if (!newPasswordArg.equals(verifyPasswordArg)) {
					redirect.addFlashAttribute("verify_password_error", "Las contraseñas no coinciden");
					hasErrors = true;
				}
			}
		}

		if (!hasErrors) {
			try {
				Password passwordRequest = new Password(userNameArg, oldPasswordArg, newPasswordArg);
				if (request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR) == null) {
					
					autenticacionService.changeTemporaryPassword(passwordRequest);
					redirect.addFlashAttribute("user_name_val", userNameArg);
					nextPage = "redirect:/";
				} else {
					
					autenticacionService.changePassword(passwordRequest);
					nextPage = "redirect:/application";
				}
			} catch (InvalidPasswordException e) {
				redirect.addFlashAttribute("change_password_error", "Error, password incorrecto " + e.getErrorMessage());
			} catch (Exception e) {
				redirect.addFlashAttribute("change_password_error",
						"Error al cambiar password " + e.getLocalizedMessage());
			}
		}
		return nextPage;
	}
	

	/**
	 * Metodo para recuperar una contraseña olvidada. 
	 * Dado el username, se envia un email con un password (codigo).
	 * 
	 * @param user
	 * @param redirect
	 * @return
	 */
	@PostMapping("/forgot_password_form")
	public String resetPasswordForm(@RequestParam("user_name") String user, RedirectAttributes redirect,
			HttpServletRequest request) {
		boolean hasErrors = false;
		String userNameArg = null;
		String nextPage = "redirect:forgot_password";
		
		if (Strings.isNullOrEmpty(user)) {
			redirect.addFlashAttribute("user_name_error", "Por favor ingrese un usuario");
			hasErrors = true;
		} else {
			userNameArg = user.trim();
		}
		if (!hasErrors) {
			try {
				autenticacionService.forgotPassword(userNameArg);
				redirect.addFlashAttribute("message", "Se ha enviado un código de reinicio a su dirección de correo electrónico.");
				redirect.addFlashAttribute("user_name_val", userNameArg);
				redirect.addFlashAttribute("change_type", "forgotten_password");
				
				//Salir de session
				request.getSession().setAttribute(AppConstants.USER_SESSION_ATTR, null);
				nextPage = "redirect:reset_password"; // Ir al formulario para restablecer password
			} catch (UserNotFoundException e) {
				redirect.addFlashAttribute("user_name_error", "Upss!, no se encontro el usuario " + userNameArg);
			} catch (InvalidParameterException e) {
				redirect.addFlashAttribute("user_name_error",
						"No se puede restablecer el password para el usuario, ya que no hay email y/o cel verificado");
			} catch (Exception e) {
				redirect.addFlashAttribute("user_name_error",
						"Error username: " + e.getClass().getName() + " " + e.getLocalizedMessage());
			}
		}
		return nextPage;
	}
}
