package com.proyecto.service.impl;

import java.util.List;
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
        // Implementación del método usando el repositorio
        // Usamos el estado "PAGADO" para filtrar las alícuotas canceladas
        return alicuotaRepository.findByDepartamentoInAndPagoEstado(departamentos, "PAGADO");
    }

    @Override
    public List<Alicuota> findAlicuotasByDepartamentos(List<Departamento> departamentos) {
        // Implementación opcional para obtener todas las alícuotas de esos departamentos
        return alicuotaRepository.findByDepartamentoIn(departamentos);
    }
}