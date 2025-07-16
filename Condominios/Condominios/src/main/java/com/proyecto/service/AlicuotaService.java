package com.proyecto.service;

import java.util.List;
import java.util.Map;

import com.proyecto.entity.Alicuota;
import com.proyecto.entity.Departamento;

public interface AlicuotaService {
    List<Alicuota> findAllAlicuotas();
    Alicuota saveAlicuota(Alicuota alicuota);
    void deleteAlicuota(Long id);
    Alicuota updateAlicuota(Long id, Alicuota detalle);

    // NUEVO MÉTODO: Para obtener alícuotas canceladas por lista de departamentos
    List<Alicuota> findAlicuotasCanceladasByDepartamentos(List<Departamento> departamentos);

    // Opcional: Si quieres obtener todas las alícuotas de una lista de departamentos (pagadas o no)
    List<Alicuota> findAlicuotasByDepartamentos(List<Departamento> departamentos);

    // NUEVO MÉTODO: Para obtener el conteo de alícuotas por estado para los departamentos de un propietario
    Map<String, Long> getAlicuotasCountByStateForDepartamentos(List<Departamento> departamentos);

    // Opcional: Si quieres el valor total por estado
    Map<String, Double> getAlicuotasSumValueByStateForDepartamentos(List<Departamento> departamentos);
}