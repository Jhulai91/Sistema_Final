package com.proyecto.service;

import java.util.List;

import com.proyecto.entity.Departamento;
import com.proyecto.entity.Propietario;

public interface DepartamentoService {

	List<Departamento> findAllDepartamento();
    Departamento saveDepartamento(Departamento departamento);
    void deleteDepartamento(int id);
    Departamento updateDepartamento(int id, Departamento detalle);
}
