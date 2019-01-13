package com.hmrles.reservations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador para el manejo de peticiones a la pagina principal (login)
 * 
 * @author HMrles (hmorales@gmx.com)
 * @version 1.0
 * @since Enero 2019
 *
 */

@Controller
@RequestMapping("app")
public class LoginController {
	
	@GetMapping(value = { "/login", "/" })
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "Usuario no autorizado, debe autentificarse.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("login");
		return mav;
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
}