package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Departamento;
import com.proyecto.entity.Propietario;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
	List<Departamento> findByPropietario(Propietario propietario);
}
