package com.proyecto.entity;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gastos_comunales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoComunal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String descripcion;
	@Nonnull
	private double monto;
	@Nonnull
	private LocalDate fecha;
	// Puede agruparse por mes
	@Nonnull
	 private int mes;
	@Nonnull
	 private int anio;

}
