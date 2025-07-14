package com.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.entity.Alicuota;
import com.proyecto.entity.Departamento;

public interface AlicuotaRepository extends JpaRepository<Alicuota, Long> {
    // Método para encontrar alícuotas por una lista de departamentos
    List<Alicuota> findByDepartamentoIn(List<Departamento> departamentos);

    // Método para encontrar alícuotas por una lista de departamentos y que tengan un pago asociado
    // y el estado de ese pago sea "PAGADO"
    List<Alicuota> findByDepartamentoInAndPagoEstado(List<Departamento> departamentos, String estadoPago);
}