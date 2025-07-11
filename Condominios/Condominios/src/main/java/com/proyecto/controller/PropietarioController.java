package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uisrael.gestion_biblioteca.entity.Autor;
@Controller
public class PropietarioController {

	 @GetMapping("/propietario/home")
	    public String propietarioHome() {
	        return "propietario-home";
	    }
	 
	 // Mostrar formulario nuevo autor
	    @GetMapping("/nuevo")
	    public String mostrarFormulario(Model model) {
	        model.addAttribute("autor", new Autor());
	        return "ingreso_propietario";
	    }
}
