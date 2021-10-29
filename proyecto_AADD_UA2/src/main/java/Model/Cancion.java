package Model;

public class Cancion {
	protected String Nombre;
	protected int Duracion;
	protected String Genero;
	protected int N_reproducciones;
	protected int id;
	protected Disco id_Disco;
	
	public Cancion(String nombre, int duracion, String genero, int n_reproducciones, int id, Disco id_Disco) {
		super();
		this.Nombre = nombre;
		this.Duracion = duracion;
		this.Genero = genero;
		this.N_reproducciones = n_reproducciones;
		this.id = id;
		this.id_Disco = id_Disco;
	}

	public Cancion(String nombre, int duracion, String genero, int n_reproducciones, Disco id_Disco) {
		super();
		this.Nombre = nombre;
		this.Duracion = duracion;
		this.Genero = genero;
		this.N_reproducciones = n_reproducciones;
		this.id_Disco = id_Disco;
	}



	public Cancion() {
		super();
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getDuracion() {
		return Duracion;
	}

	public void setDuracion(int duracion) {
		Duracion = duracion;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public int getN_reproducciones() {
		return N_reproducciones;
	}

	public void setN_reproducciones(int n_reproducciones) {
		N_reproducciones = n_reproducciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Disco getId_Disco() {
		return id_Disco;
	}

	public void setId_Disco(Disco id_Disco) {
		this.id_Disco = id_Disco;
	}

	
	
	
	
}
