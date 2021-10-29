package Model;

public class Usuario {
	protected String Nombre;
	protected String Correo;
	protected String Foto;
	protected String Contraseña;
	protected int id;

	public Usuario(String nombre, String correo, String foto, String contraseña) {
		super();
		Nombre = nombre;
		Correo = correo;
		Foto = foto;
		Contraseña = contraseña;
	}
	
	public Usuario(String nombre, String correo, String foto, String contraseña, int id) {
		super();
		Nombre = nombre;
		Correo = correo;
		Foto = foto;
		Contraseña = contraseña;
		this.id = id;
	}

	public Usuario() {
		super();
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}

}
