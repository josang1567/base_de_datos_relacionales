package Model;

import java.time.LocalDate;

public class Disco {
	protected String Nombre;
	protected LocalDate Fecha_publicacion;
	protected String Foto;
	protected int N_Reproduciones;
	protected int id;
	protected Artista id_Artista;
	
	public Disco(String nombre, LocalDate fecha_publicacion, String foto, int n_Reproduciones, Artista iD_Artista) {
		super();
		Nombre = nombre;
		Fecha_publicacion = fecha_publicacion;
		Foto = foto;
		N_Reproduciones = n_Reproduciones;
		id_Artista = iD_Artista;
	}
	
	public Disco(String nombre, LocalDate fecha_publicacion, String foto, int n_Reproduciones, int id, Artista id_Artista) {
		super();
		Nombre = nombre;
		Fecha_publicacion = fecha_publicacion;
		Foto = foto;
		N_Reproduciones = n_Reproduciones;
		this.id = id;
		this.id_Artista = id_Artista;
	}

	public Disco() {
		super();
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public LocalDate getFecha_publicacion() {
		return Fecha_publicacion;
	}

	public void setFecha_publicacion(LocalDate fecha_publicacion) {
		Fecha_publicacion = fecha_publicacion;
	}

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

	public int getN_Reproduciones() {
		return N_Reproduciones;
	}

	public void setN_Reproduciones(int n_Reproduciones) {
		N_Reproduciones = n_Reproduciones;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Artista getId_Artista() {
		return id_Artista;
	}

	public void setId_Artista(Artista id_Artista) {
		this.id_Artista = id_Artista;
	}

	
	
	
	
	
}
