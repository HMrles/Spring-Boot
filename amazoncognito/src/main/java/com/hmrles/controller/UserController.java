package com.hmrles.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;
import com.hmrles.model.User;
import com.hmrles.services.Authentication;
import com.hmrles.services.Validaciones;
import com.hmrles.util.AppConstants;

/**
 * <h4>UserController</h4>
 * <p>
 * Controlador para el manejo de peticiones del componente de User
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
@Controller
public class UserController {

	@Autowired
	Authentication autenticacionService;

	@Autowired
	Validaciones validacionesService;

	/**
	 * <p>
	 * Metodo para crear un nuevo usuario en AWS - Cognito
	 * </p>
	 * 
	 * @param userName
	 * @param emailAddr
	 * @param location
	 * @param redirect
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/create_user_form")
	public String new_user(@RequestParam("user_name") final String userName,
			@RequestParam("email") final String emailAddr, @RequestParam("location") final String location,
			RedirectAttributes redirect) throws Exception {

		String newPage = "redirect:create_user";

		boolean badArgument = false;

		if (validacionesService.isUserValid(userName)) {

			if (!autenticacionService.hasUser(userName.trim())) {
				// provide a default value for the user name so the user doesn't have to type it
				// again when correcting errors.
				redirect.addFlashAttribute("userNameVal", userName);
			} else {
				redirect.addFlashAttribute("userNameError", "El usurario \"" + userName + "\" ya existe");
				badArgument = true;
			}
		} else {
			redirect.addFlashAttribute("userNameError",
					"Requerido. EL usuario debe tener minimo " + AppConstants.USER_NAME_MIN_LENGTH + " caracteres");
		}

		if (!badArgument) { 
			if (validacionesService.isEmailValid(emailAddr.trim())) {
				User info = autenticacionService.findUserByEmail(emailAddr);

				if (info != null) { 
					redirect.addFlashAttribute("emailError", "Este email ya está registrado");
					badArgument = true;
				}
			} else {
				redirect.addFlashAttribute("emailError", "Requerido. El email debe ser valido.");
				badArgument = true;
			}
		}

		if (!badArgument) {

			if (validacionesService.isLocationValid(location)) {
			} else {
				redirect.addFlashAttribute("locationError", "Seleccione una region.");
				badArgument = true;
			}
		}


		if (!badArgument) {
			try {
				User userInfo = new User(userName, emailAddr, location);
				autenticacionService.createUser(userInfo);
				redirect.addFlashAttribute("login_message",
						"Tu usuario es " + userName + ". Revisa tu email para  tu password temporal");
				redirect.addFlashAttribute("user_name_val", userName);
				newPage = "redirect:/";
			} catch (Exception e) {
				redirect.addFlashAttribute("createUserError",
						"Error al registrar nuevo usuario: " + e.getLocalizedMessage());
			}
		}
		return newPage;
	}

	/**
	 * Metodo para eliminar un usuario
	 * 
	 * @param password
	 * @return
	 */
	@PostMapping("/delete_account_form")
	public String deleteAccountForm(@RequestParam("password") final String password, RedirectAttributes redirect,
			HttpServletRequest request) {
		String nextPage = "redirect:delete_account";
		User info = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (info != null) {

			if (validacionesService.isPasswordValid(password)) {
				try {
					String userName = info.getUserName();
					autenticacionService.deleteUser(userName, password);
					request.getSession().setAttribute(AppConstants.USER_SESSION_ATTR, null);
					nextPage = "redirect:/";
				} catch (Exception e) {
					redirect.addFlashAttribute("password_error", e.getLocalizedMessage());
				}
			} else {
				redirect.addFlashAttribute("password_error", "Por favor, ingresa tu password");
			}
		} else {
			nextPage = "redirect:/";
		}
		return nextPage;
	}
	
	/**
	 * Metodo para buscar un usuario por email
	 * 
	 * @param emailAddr
	 * @return
	 */
	@PostMapping("username_lookup_form")
	public String userNameLookupForm(@RequestParam("email_address") String emailAddr, RedirectAttributes redirect) {
		String nextPage = "redirect:username_lookup";
		if (Strings.isNullOrEmpty(emailAddr)) {
			redirect.addFlashAttribute("email_error", "Ingrese email");
		} else {
			if (validacionesService.isEmailValid(emailAddr)) {
				try {
					User info = autenticacionService.findUserByEmail(emailAddr);
					if (info != null) {
						String userName = info.getUserName();
						if (userName != null) {
							redirect.addFlashAttribute("user_name_val", userName);
							redirect.addFlashAttribute("login_message", "El usuario es " + userName);
							nextPage = "redirect:/";
						} else {
							redirect.addFlashAttribute("email_error",
									"La búsqueda de nombre de usuario no tuvo éxito (se devolvió nulo)");
						}
					} else {
						redirect.addFlashAttribute("email_error", "Ningún usuario ha sido encontrado con esa dirección de correo electrónico");
					}
				} catch (Exception e) {
					redirect.addFlashAttribute("email_error", e.getLocalizedMessage());
				}
			} else {
				redirect.addFlashAttribute("email_error", "Hay un problema con el formato de la dirección de correo electrónico");
			}
		}
		return nextPage;
	}
	

	/**
	 * Metodo para actualizar el perfil del usuario
	 * 
	 * @param location
	 * @param redirect
	 * @param request
	 * @return
	 */
	@PostMapping("/change_profile_form")
	public String changeProfileForm(@RequestParam("location") final String location, RedirectAttributes redirect,
			HttpServletRequest request) {
		String nextPage = "redirect:change_profile";
		User info = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (info != null) {
			if (Strings.isNullOrEmpty(location)) {
				redirect.addFlashAttribute("location_error", "Ingrese su region");
			} else {
				try {
					if ((!info.getLocation().equals(location))) {
						User newInfo = new User(info.getUserName(), info.getEmailAddr(), location);
						autenticacionService.updateUser(newInfo);
						newInfo = autenticacionService.getUserInfo(info.getUserName());
						request.getSession().setAttribute(AppConstants.USER_SESSION_ATTR, newInfo);
					}
					nextPage = "redirect:application";
				} catch (Exception e) {
					redirect.addFlashAttribute("location_error", e.getLocalizedMessage());
				}
			}
		} else {
			// Regrese a la pagina de index, si el usuario no ha iniciado session
			nextPage = "redirect:/";
		}
		return nextPage;
	}
}
