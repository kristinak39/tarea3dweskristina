package com.kristina.tarea3dweskristina.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String mensaje;

	@Column(nullable = false)
	private LocalDateTime fechaHora;

	@ManyToOne
	@JoinColumn(name = "ejemplar_id", nullable = false)
	private Ejemplar ejemplar;

	@ManyToOne
	@JoinColumn(name = "persona_id", nullable = false)
	private Persona persona;

	public Mensaje() {

	}

	public Mensaje(Long id, String mensaje, LocalDateTime fechaHora, Ejemplar ejemplar, Persona persona) {
		this.id = id;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
		this.ejemplar = ejemplar;
		this.persona = persona;
	}

	

	public Mensaje(String mensaje, LocalDateTime fechaHora, Ejemplar ejemplar) {
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
		this.ejemplar = ejemplar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
