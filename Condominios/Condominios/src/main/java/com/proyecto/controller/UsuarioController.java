package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.entity.Propietario;
import com.proyecto.entity.Rol;
import com.proyecto.entity.Usuario;
import com.proyecto.repository.PropietarioRepository;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.service.UsuarioService;
import com.uisrael.gestion_biblioteca.entity.Autor;
import com.uisrael.gestion_biblioteca.entity.Libro;

@Controller
public class UsuarioController {
	
	  @Autowired
	    private UsuarioRepository usuarioRepo;

	  @Autowired
	    private UsuarioService usuarioService;
	    @Autowired
	    private PropietarioRepository propietarioRepo;
	    @Autowired
	    private PasswordEncoder passwordEncoder; 


	 @GetMapping("/login")
	    public String login() {
	        return "login"; // esto busca templates/login.html
	    }
	 
	

	 @GetMapping("/usuario/registro")
	    public String mostrarFormulario(Model model) {
	        model.addAttribute("usuario", new Usuario());
	        return "registro";
	    }

	    @PostMapping("usuario/registro")
	    public String registrar(
	    	    @ModelAttribute Usuario usuario,
	    	    @RequestParam("cedula") String cedula,
	    	    @RequestParam("telefono") String telefono,
	    	    @RequestParam("rol") String rol,
	    	    Model model
	    	) {
	    	    if (usuarioRepo.findByEmail(usuario.getEmail()) != null) {
	    	        model.addAttribute("error", "El correo ya está registrado.");
	    	        return "registro";
	    	    }
	    	    
	    	    String hashedPassword = passwordEncoder.encode(usuario.getPassword());
	    	    usuario.setPassword(hashedPassword);
	    	    // Asignar el rol recibido
	    	    usuario.setRol(Rol.valueOf(rol));

	    	    // Guardar usuario
	    	    Usuario nuevoUsuario = usuarioRepo.save(usuario);

	    	    // Crear Propietario vinculado
	    	    Propietario propietario = new Propietario();
	    	    propietario.setCedula(cedula);
	    	    propietario.setTelefono(telefono);
	    	    propietario.setUsuario(nuevoUsuario);

	    	    propietarioRepo.save(propietario);

	    	    return "redirect:/login?registroExitoso";
	    	}
	    
	    @GetMapping("/editar/usuario/{id}")
	    public String editarUsuario(@PathVariable int id, Model model) {
	        Usuario usuario = usuarioService.findAllUsuario()
	                .stream()
	                .filter(l -> l.getId() == id)
	                .findFirst()
	                .orElse(null);

	        if (usuario != null) {
	            model.addAttribute("usuario", usuario);
	            return "actualiza_propietario";
	        } else {
	            return "redirect:/admin/home";
	        }
	    }

}
