package com.kristina.tarea3dweskristina.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "plantas")
public class Planta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String codigo;

	@Column
	private String nombreComun;

	@Column
	private String nombreCientifico;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();

	public Planta() {
		super();
	}

	public Planta(Long id, String codigo, String nombreComun, String nombreCientifico, List<Ejemplar> ejemplares) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.ejemplares = ejemplares;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Id: " + id + " | codigo: " + codigo + " | nombre comun: " + nombreComun;
	}

}
