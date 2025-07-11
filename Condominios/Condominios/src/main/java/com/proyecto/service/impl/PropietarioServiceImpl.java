package com.proyecto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.entity.Propietario;
import com.proyecto.repository.PropietarioRepository;
import com.proyecto.service.PropietarioService;
import com.uisrael.gestion_biblioteca.entity.Autor;


@Service
@Transactional
public class PropietarioServiceImpl implements PropietarioService {

	
	@Autowired
    private PropietarioRepository propietarioRepository;
	@Override
	public List<Propietario> findAllPropieatrio() {
		// TODO Auto-generated method stub
		return propietarioRepository.findAll();
	}

	@Override
	public Propietario savePropietario(Propietario propietario) {
		// TODO Auto-generated method stub
		return propietarioRepository.save(propietario);
	}

	@Override
	public void deletePropietario(int id) {
		// TODO Auto-generated method stub
		propietarioRepository.deleteById(id);
		
	}

	@Override
	public Propietario updatePropietario(int id, Propietario detalle) {
		// TODO Auto-generated method stub
		  Propietario propietario = propietarioRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Propietario no encoentrado :: " + id));
	        
	        return propietarioRepository.save(propietario);
	}


	

}
