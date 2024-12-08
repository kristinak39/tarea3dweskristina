package com.kristina.tarea3dweskristina.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejemplares")
public class Ejemplar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "planta_codigo", nullable = false)
	private Planta planta;

	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Mensaje> mensajes;

	public Ejemplar() {

	}

	public Ejemplar(String nombre, Planta planta) {
		this.nombre = nombre;
		this.planta = planta;
	}

	public Ejemplar(Long id, String nombre, Planta planta, List<Mensaje> mensajes) {
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
		this.mensajes = mensajes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

}
