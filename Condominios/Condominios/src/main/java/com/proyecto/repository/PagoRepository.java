package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {

}