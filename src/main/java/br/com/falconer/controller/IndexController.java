package br.com.falconer.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.falconer.component.IdentificationComponent;
import br.com.falconer.dto.Identification;

@Controller
public class IndexController {
	
	@Autowired
	private IdentificationComponent identificationComponent;
	
	@GetMapping
	public ModelAndView index(@CookieValue(value = "myappuserid", defaultValue = "") String email) throws UnsupportedEncodingException {
		return new ModelAndView("index", "user", identificationComponent.getUser(email));
	}
	
	@PostMapping
	public ModelAndView identify(@ModelAttribute Identification identification) throws UnsupportedEncodingException {
		return new ModelAndView("index", "user", identificationComponent.setUser(identification));
	}
	
}
