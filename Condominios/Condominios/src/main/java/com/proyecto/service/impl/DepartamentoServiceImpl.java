package com.proyecto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.entity.Departamento;
import com.proyecto.entity.Propietario;
import com.proyecto.repository.DepartamentoRepository;
import com.proyecto.repository.PropietarioRepository;
import com.proyecto.service.DepartamentoService;
@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
    private DepartamentoRepository departamentoRepository;
	
	@Override
	public List<Departamento> findAllDepartamento() {
		// TODO Auto-generated method stub
		return departamentoRepository.findAll();
	}

	@Override
	public Departamento saveDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		return departamentoRepository.save(departamento);
	}

	@Override
	public void deleteDepartamento(long id) {
		// TODO Auto-generated method stub
		departamentoRepository.deleteById(id);
	}

	@Override
	public Departamento updateDepartamento(long id, Departamento detalle) {
		// TODO Auto-generated method stub
		 Departamento departamento = departamentoRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Departamento no encoentrado :: " + id));
	        
	        return departamentoRepository.save(departamento);
	}

    @Override
    public List<Departamento> findByPropietario(Propietario propietario) {
        return departamentoRepository.findByPropietario(propietario);
    }

}
