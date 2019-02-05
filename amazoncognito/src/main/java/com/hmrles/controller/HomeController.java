package com.hmrles.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.hmrles.model.Login;
import com.hmrles.model.User;
import com.hmrles.services.Authentication;
import com.hmrles.services.Validaciones;
import com.hmrles.util.AppConstants;

/**
 * <h4>HomeController</h4>
 * <p>
 * Controlador para el manejo de peticiones de los componentes del Front
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
@Controller
public class HomeController {

	@Autowired
	Authentication autenticacionService;
	
	@Autowired
	Validaciones validacionesService;

	/**
	 * Metodo para manejar la pagina principal 
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView index(ModelMap model, HttpServletRequest request) {
		String nextPage = "index";
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (user != null) {
			//El usuario ya ha iniciado session, por lo que se redirige a la pagina aplication.jsp
			nextPage = "application";
			model.addAttribute(AppConstants.USER_SESSION_ATTR, user);
		}
		return new ModelAndView(nextPage, model);
	}

	
	/**
	 * Metodo para obtener la pagina de user
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/create_user")
	public String create_user(ModelMap model) {
		return "/create_user";
	}

	
	@GetMapping("/application")
	public ModelAndView application(ModelMap model, RedirectAttributes redirect, HttpServletRequest request) {
		String nextPage = "application";
		
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (user != null) {
			model.addAttribute("user_info", user);
		} else {
			// El usuario no ha iniciado sesión, se redirige a pagina de inicio.
			nextPage = "index";
		}
		return new ModelAndView(nextPage, model);
	}

	
	@GetMapping("/delete_account")
	public String deleteAccount(Model model, HttpServletRequest request) {
		String nextPage = "delete_account";
		
		//Nos aseguramos que el usuario haya iniciado session
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (user == null) {
			nextPage = "index";
		}
		return nextPage;
	}

	
	@GetMapping("/change_email")
	public ModelAndView changeEmailRequest(ModelMap model, HttpServletRequest request) {
		String nextPage = "change_email";
		
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (user != null) {
			String currentEmailAddr = user.getEmailAddr();
			model.addAttribute("current_email", currentEmailAddr);
		} else {
			nextPage = "index";
		}
		return new ModelAndView(nextPage, model);
	}

	
	/**
	 * Metodo para cerrar la session
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/logout_form")
	public String logoutPage(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (user != null) {
			autenticacionService.userLogout(user.getUserName());
			request.getSession().setAttribute(AppConstants.USER_SESSION_ATTR, null);
		}
		return "index";
	}


	/**
	 * Metodo para obtener la pagina de changePassword
	 * 
	 * @return
	 */

	@GetMapping("change_password")
	public ModelAndView changePassword(ModelMap modelMap, HttpServletRequest request) {
		if (request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR) != null) {
			//Si el usuario ha inciado session(Conoce su contraseña)
			//Cambiar contraseña sin enviar email
			User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
			if (user != null) {
				modelMap.addAttribute("user_name_val", user.getUserName());
			}
		}
		return new ModelAndView("change_password", modelMap);
	}

	
	@GetMapping("/change_profile")
	public ModelAndView changeProfile(ModelMap model, RedirectAttributes redirect, HttpServletRequest request) {
		String nextPage = "change_profile";
		
		User user = (User) request.getSession().getAttribute(AppConstants.USER_SESSION_ATTR);
		if (user != null) {
			model.addAttribute("user_info", user);
		} else {
			nextPage = "index";
		}
		return new ModelAndView(nextPage, model);
	}

	
	@GetMapping("/forgot_password")
	public String passwordResetPage(Model model) {
		return "forgot_password";
	}

	/**
	 * Metodo para obtener la pagina resetPassword
	 * 
	 * @return
	 */
	@GetMapping("reset_password")
	public String changePassword(Model model) {
		return "reset_password";
	}

	@GetMapping("username_lookup")
	public String userNameLookup(Model model) {
		String nextPage = "username_lookup";		
		return nextPage;
	}

	/**
	 * Metodo para obtener el formulario de login
	 * 
	 * @param userName
	 * @param password
	 * @param redirect
	 * @return
	 */
	@PostMapping("/login_form")
	public String login(@RequestParam("user_name") String userName, @RequestParam("password") String password,
			RedirectAttributes redirect, HttpServletRequest request) {
		
		boolean hasErrors = false;
		String nextPage = "redirect:/";
		
		if(validacionesService.isUserValid(userName)) {
			redirect.addFlashAttribute("user_name_val", userName.trim());
		}else {
			redirect.addFlashAttribute("user_name_error", "Requerido");
			hasErrors = true;
		}

		if (!hasErrors) {
			if(validacionesService.isPasswordValid(password.trim())) {
				
			}else {
				redirect.addFlashAttribute("password_error", "Requerido. Ingresa un password");
				hasErrors = true;
			}			
		}
		
		
		if (!hasErrors) {
			/*
			* El nombre de usuario y la contraseña han sido ingresados. Ahora intente el proceso de inicio de sesión.
			* Hay tres resultados: 
			* 1. El inicio de sesión se realiza correctamente y el control pasa a Página de solicitud. 
			* 2. Se requiere un cambio de contraseña (por ejemplo, para un nuevo usuario) y  El control va a la página de cambio de contraseña. 
			* 3. El inicio de sesión falla y volvemos a la página índice con el formulario de inicio de sesión.
			 */
			try {
				Login loginInfo = autenticacionService.userLogin(userName, password);
				if (loginInfo != null) {
					if (loginInfo.getNewPasswordRequired()) {
						nextPage = "redirect:change_password";
						redirect.addFlashAttribute("user_name_val", userName);
						redirect.addFlashAttribute("change_type", "change_password");
					} else {
						nextPage = "redirect:application";
						User userInfo = new User(loginInfo.getUserName(), loginInfo.getEmailAddr(),	loginInfo.getLocation());
						//Se estable el atributo de ssion
						request.getSession().setAttribute(AppConstants.USER_SESSION_ATTR, userInfo);
					}
				}
			} catch (NotAuthorizedException e) {
				redirect.addFlashAttribute("login_error", "Usuario o password incorrectos");
			} catch (Exception e) {
				redirect.addFlashAttribute("login_error", "Error en login: " + e.getClass().getName() + " " + e.getLocalizedMessage());
			}
		}
		return nextPage;
	}

}
