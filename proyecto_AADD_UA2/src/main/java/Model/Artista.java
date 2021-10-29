package Model;

public class Artista {
	protected String Nombre;
	protected String Nacionalidad;
	protected String Foto;
	protected int id;
	
	
	
	public Artista(String nombre, String nacionalidad, String foto) {
		super();
		Nombre = nombre;
		Nacionalidad = nacionalidad;
		Foto = foto;
	}
	
	
	public Artista(String nombre, String nacionalidad, String foto, int id) {
		super();
		Nombre = nombre;
		Nacionalidad = nacionalidad;
		Foto = foto;
		this.id = id;
	}


	public Artista() {
		super();
	}

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public String getFoto() {
		return Foto;
	}
	public void setFoto(String foto) {
		Foto = foto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
