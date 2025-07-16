package com.proyecto.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.entity.Alicuota;
import com.proyecto.entity.Departamento;
import com.proyecto.repository.AlicuotaRepository; 
import com.proyecto.service.AlicuotaService;

@Service
@Transactional
public class AlicuotaServiceImpl implements AlicuotaService {

    @Autowired
    private AlicuotaRepository alicuotaRepository;

    @Override
    public List<Alicuota> findAllAlicuotas() {
        return alicuotaRepository.findAll();
    }

    @Override
    public Alicuota saveAlicuota(Alicuota alicuota) {
        return alicuotaRepository.save(alicuota);
    }

    @Override
    public void deleteAlicuota(Long id) {
        alicuotaRepository.deleteById(id);
    }

    @Override
    public Alicuota updateAlicuota(Long id, Alicuota detalle) {
        Optional<Alicuota> alicuotaOptional = alicuotaRepository.findById(id);

        if (alicuotaOptional.isPresent()) {
            Alicuota alicuotaExistente = alicuotaOptional.get();

            alicuotaExistente.setMes(detalle.getMes());
            alicuotaExistente.setAnio(detalle.getAnio());
            alicuotaExistente.setValorCalculado(detalle.getValorCalculado());
            return alicuotaRepository.save(alicuotaExistente);
        } else {
            throw new RuntimeException("Alícuota no encontrada con ID :: " + id);
        }
    }

    @Override
    public List<Alicuota> findAlicuotasCanceladasByDepartamentos(List<Departamento> departamentos) {
        return alicuotaRepository.findByDepartamentoInAndPagoEstado(departamentos, "PAGADO");
    }

    @Override
    public List<Alicuota> findAlicuotasByDepartamentos(List<Departamento> departamentos) {
        return alicuotaRepository.findByDepartamentoIn(departamentos);
    }
    
    @Override
    public Map<String, Long> getAlicuotasCountByStateForDepartamentos(List<Departamento> departamentos) {
        List<Object[]> results = alicuotaRepository.countAlicuotasByStateForDepartamentos(departamentos);
        Map<String, Long> countMap = new HashMap<>();
        // Inicializar con 0 para asegurar que todos los estados estén presentes
        countMap.put("PAGADO", 0L);
        countMap.put("PENDIENTE", 0L);
        countMap.put("VENCIDO", 0L);

        for (Object[] result : results) {
            String estado = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(estado, count);
        }
        return countMap;
    }

    @Override
    public Map<String, Double> getAlicuotasSumValueByStateForDepartamentos(List<Departamento> departamentos) {
        List<Object[]> results = alicuotaRepository.sumAlicuotasValueByStateForDepartamentos(departamentos);
        Map<String, Double> sumMap = new HashMap<>();
        // Inicializar con 0.0 para asegurar que todos los estados estén presentes
        sumMap.put("PAGADO", 0.0);
        sumMap.put("PENDIENTE", 0.0);
        sumMap.put("VENCIDO", 0.0);

        for (Object[] result : results) {
            String estado = (String) result[0];
            Double sum = (Double) result[1];
            sumMap.put(estado, sum);
        }
        return sumMap;
    }
}