package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PropietarioController {

	 @GetMapping("/propietario/home")
	    public String propietarioHome() {
	        return "propietario-home";
	    }
}
