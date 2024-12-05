package com.kristina.tarea3dweskristina.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "ejemplares")
public class Ejemplar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "idplanta")
	private Planta planta;

	// @OneToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name = "idejemplar")
	// private List<Mensaje> mensajes = new LinkedList<Mensaje>();

	public Ejemplar() {
		super();
	}

	public Ejemplar(Long id, String nombre, Planta planta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
