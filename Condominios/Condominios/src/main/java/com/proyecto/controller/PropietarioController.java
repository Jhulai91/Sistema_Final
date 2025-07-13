package com.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.proyecto.entity.Departamento;
import com.proyecto.entity.Propietario;
import com.proyecto.entity.Usuario;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.service.DepartamentoService;
import com.proyecto.service.PropietarioService;
import com.uisrael.gestion_biblioteca.entity.Autor;

@Controller
public class PropietarioController {

	@Autowired
	private final UsuarioRepository usuarioRepo;
	private final PropietarioService propietarioService;
	private final DepartamentoService departamentoService;
	
	public PropietarioController(
            UsuarioRepository usuarioRepo,
            PropietarioService propietarioService,
            DepartamentoService departamentoService) { // Añadir DepartamentoService al constructor
		this.usuarioRepo = usuarioRepo;
        this.propietarioService = propietarioService;
        this.departamentoService = departamentoService; // Asignar el servicio
	}

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

	@ModelAttribute
	public void agregarUsuarioAutenticado(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			Usuario usuario = usuarioRepo.findByEmail(userDetails.getUsername());
			model.addAttribute("usuarioAutenticado", usuario);
		}
	}
	
    @GetMapping("/propietario/informacion") 
    public String propietarioInformacion(Model model) {
        // 1. Obtener el email del usuario autenticado desde Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuarioAutenticado = authentication.getName(); // Esto debería ser el email del usuario

        // 2. Usar el PropietarioService para obtener la información del propietario
        Optional<Propietario> propietarioOptional = propietarioService.obtenerPropietarioPorEmailUsuario(emailUsuarioAutenticado);

        if (propietarioOptional.isPresent()) {
            Propietario propietario = propietarioOptional.get();
            model.addAttribute("propietario", propietario);
            // Ya el @ModelAttribute("usuarioAutenticado") añade el objeto Usuario al modelo.
            // Pero para ser explícitos o si no usas @ModelAttribute para este caso, puedes añadirlo aquí:
            model.addAttribute("usuario", propietario.getUsuario()); // Añadimos el objeto Usuario asociado al Propietario

            List<Departamento> departamentos = departamentoService.findByPropietario(propietario);
            model.addAttribute("departamentos", departamentos); // Añadir la lista de departamentos al modelo
   
            return "propietario-informacion"; // Mapea a src/main/resources/templates/propietario-informacion.html
        } else {
            // Manejar el caso donde no se encuentra el propietario
            model.addAttribute("mensajeError", "No se encontró la información de propietario para el usuario actual.");
            return "error"; // Puedes redirigir a una página de error genérica o mostrar un mensaje en la misma página.
        }
    }
}
