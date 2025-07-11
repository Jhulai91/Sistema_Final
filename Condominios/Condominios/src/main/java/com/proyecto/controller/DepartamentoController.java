package com.proyecto.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.entity.Departamento;


public class DepartamentoController {

	
	 // Mostrar formulario nuevo autor
    @GetMapping("/nuevo/departamento")
    public String mostrarFormulario(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "ingreso_departamento";
    }
}
