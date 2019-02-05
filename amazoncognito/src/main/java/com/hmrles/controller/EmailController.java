package com.hmrles.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hmrles.model.User;
import com.hmrles.services.Authentication;
import com.hmrles.services.Validaciones;
import com.hmrles.util.AppConstants;

/**
 * <h4>EmailController</h4>
 * <p>
 * Controlador para el manejo de peticiones con respecto a Email
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
@Controller
public class EmailController {

	@Autowired
	Authentication autenticacionService;

	@Autowired
	Validaciones validacionesService;

	@PostMapping("/change_email_form")
	public String changeEmailRequestForm(@RequestParam("email_addr") final String email, RedirectAttributes redirect,
			HttpServletRequest request) throws Exception {
		
		String nextPage = "redirect:change_email";
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);

		if (user != null) {
			if (validacionesService.isEmailValid(email)) {

				// Obtenemos el email de la BD de Cognito
				User userCognito = autenticacionService.getUserInfo(user.getUserName());

				if (!userCognito.getEmailAddr().equals(email)) {
					try {
						autenticacionService.changeEmail(user.getUserName(), email);
						User userNew = autenticacionService.getUserInfo(user.getUserName());
						request.getSession().setAttribute(AppConstants.USER_SESSION_ATTR, userNew);
						nextPage = "redirect:application";
					} catch (Exception e) {
						redirect.addFlashAttribute("email_addr_error", e.getLocalizedMessage());
					}
				} else {
					redirect.addFlashAttribute("email_addr_error",
							"La nueva dirección de correo electrónico es la misma que la dirección en la base de datos");
				}
			} else {
				redirect.addFlashAttribute("email_addr_error", "Requerido. Ingrese un Email valido");
			}
		} else {

			nextPage = "redirect:/";
		}
		return nextPage;
	}

}
