package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entity.Propietario;


public interface PropietarioService {
	List<Propietario> findAllPropieatrio();
    Propietario savePropietario(Propietario propietario);
    void deletePropietario(long id);
    Propietario updatePropietario(long id, Propietario detalle);
    
    Optional<Propietario> obtenerPropietarioPorEmailUsuario(String emailUsuario);
}
