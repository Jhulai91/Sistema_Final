package com.proyecto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.entity.Propietario;
import com.proyecto.entity.Usuario;
import com.proyecto.repository.PropietarioRepository;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.service.PropietarioService;


@Service
@Transactional
public class PropietarioServiceImpl implements PropietarioService {
	
	@Autowired
    private PropietarioRepository propietarioRepository;
	
    @Autowired 
    private UsuarioRepository usuarioRepository;
    
	@Override
	public List<Propietario> findAllPropieatrio() {
		return propietarioRepository.findAll();
	}

	@Override
	public Propietario savePropietario(Propietario propietario) {
		// TODO Auto-generated method stub
		return propietarioRepository.save(propietario);
	}

	@Override
	public void deletePropietario(long id) {
		// TODO Auto-generated method stub
		propietarioRepository.deleteById(id);
		
	}

	@Override
	public Propietario updatePropietario(long id, Propietario detalle) {
		// TODO Auto-generated method stub
		  Propietario propietario = propietarioRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Propietario no encoentrado :: " + id));
	        
	        return propietarioRepository.save(propietario);
	}

	@Override
	public Optional<Propietario> obtenerPropietarioPorEmailUsuario(String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario);

        if (usuario != null) {

            return Optional.ofNullable(usuario.getPropietario());
        }
        return Optional.empty(); 
	}
}
