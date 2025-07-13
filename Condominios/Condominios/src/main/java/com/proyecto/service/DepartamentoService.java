package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.Departamento;
import com.proyecto.entity.Propietario;

public interface DepartamentoService {

	List<Departamento> findAllDepartamento();
    Departamento saveDepartamento(Departamento departamento);
    void deleteDepartamento(long id);
    Departamento updateDepartamento(long id, Departamento detalle);
    
    List<Departamento> findByPropietario(Propietario propietario);
}
