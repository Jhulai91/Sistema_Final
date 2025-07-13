package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Propietario;
import com.proyecto.entity.Usuario;

public interface PropietarioRepository extends JpaRepository<Propietario, Long>{

}
