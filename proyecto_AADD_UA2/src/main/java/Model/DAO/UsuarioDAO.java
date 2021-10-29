package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.MARIADB.MariaDBConexion;
import Model.Usuario;

public class UsuarioDAO extends Usuario {
	private static final String INSERT = "INSERT INTO Usuario" + "(nombre, correo, foto, contraseña) VALUES (?,?,?,?)";
	private static final String MOSTRARTODOS = "SELECT * FROM usuario";
	private static final String MOSTRARPORNOMBRE = "SELECT * FROM usuario WHERE nombre=?";
	private static final String MOSTRARPORID = "SELECT * FROM usuario WHERE id=?";

	private static final String EDITAR = "UPDATE usuario SET nombre=?,foto=? WHERE id=?";
	private static final String BORRAR = "DELETE FROM usuario WHERE id=?";

	private Connection con = null;

	public UsuarioDAO(String nombre, String correo, String foto, String contraseña, int id) {
		super(nombre, correo, foto, contraseña, id);
	}

	public UsuarioDAO() {
		super();
	}

	public UsuarioDAO(Usuario u) {
		super(u.getNombre(), u.getCorreo(), u.getFoto(), u.getContraseña(), u.getID());
	}

	public void guardar() {
		if (id != -1) {
			editar();
		} else {
			con = MariaDBConexion.getConexion();
			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, this.Nombre);
					ps.setString(2, this.Correo);
					ps.setString(3, this.Foto);
					ps.setString(4, this.Contraseña);

					ps.executeUpdate();
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					// fin de extraer el id generado automaticamente en la db
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						ps.close();
						rs.close();
					} catch (SQLException e2) {
						// TODO: handle exception
					}
				}
			}
		}
	}

	public void editar() {
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(EDITAR);

				ps.setString(1, this.Nombre);
				ps.setString(2, this.Correo);
				ps.setString(3, this.Foto);
				ps.setString(4, this.Contraseña);

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
	}

	public void borrar() {
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(BORRAR);
				ps.setInt(1, this.id);
				ps.executeUpdate();
				this.id = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}

	}

	public List<Usuario> mostrarTodos() {
		List<Usuario> resultado = new ArrayList<Usuario>();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs = ps.executeQuery();

				while (rs.next()) {

					resultado.add(new Usuario(

							rs.getString("nombre"), 
							rs.getString("descripcion"),
							rs.getString("foto"),
							rs.getString("contraseña")
							
							)
							);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return resultado;
	}

	public Usuario mostrarPorID(int id) {
		// TODO Auto-generated method stub
		Usuario resultado= new Usuario();
		con=MariaDBConexion.getConexion();
		if(con != null) {
			PreparedStatement ps= null;
			ResultSet rs= null;
			try {
				ps=con.prepareStatement(MOSTRARPORID);
				ps.setInt(1, id);
				rs= ps.executeQuery();
				
				while (rs.next()) {

					resultado=(new Usuario(

							rs.getString("nombre"), 
							rs.getString("descripcion"),
							rs.getString("foto"),
							rs.getString("contraseña")
							
							)
							);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		
		return resultado;
	}

}
