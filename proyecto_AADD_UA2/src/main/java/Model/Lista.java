package Model;

import java.util.List;

public class Lista {
	protected String Nombre;
	protected String Descripcion;
	protected Artista creador;
	protected List<Usuario> Subcriptores;
	protected int id;
	
	public Lista(String nombre, String descripcion, Artista creador) {
		super();
		Nombre = nombre;
		Descripcion = descripcion;
		this.creador = creador;
	}

	
	public Lista(String nombre, String descripcion, Artista creador, int id) {
		super();
		Nombre = nombre;
		Descripcion = descripcion;
		this.creador = creador;
		this.id = id;
	}


	public Lista() {
		super();
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Artista getCreador() {
		return creador;
	}

	public void setCreador(Artista creador) {
		this.creador = creador;
	}

	public List<Usuario> getSubcriptores() {
		return Subcriptores;
	}

	public void setSubcriptores(List<Usuario> subcriptores) {
		Subcriptores = subcriptores;
	}

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}
	
	
}
