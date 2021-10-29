package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.MARIADB.MariaDBConexion;
import Model.Artista;
import Model.Disco;
import Model.Lista;
import Model.Usuario;

public class ListaDAO extends Lista{
	private static final String INSERT = "INSERT INTO lista "
			+ "(nombre, descripcion, creador) VALUES (?,?,?)";

	private static final String MOSTRARTODOS = "SELECT * FROM lista ";
	private static final String MOSTRARPORNOMBRE = "SELECT *  FROM lista WHERE nombre=?";
	private static final String EDITAR = "UPDATE lista SET nombre=?, descripcion=?, creador=? WHERE id=?";
	private static final String BORRAR = "DELETE FROM lista WHERE id=?";

	 private Connection con= null;

	public ListaDAO() {
		super();
	}

	public ListaDAO(String nombre, String descripcion, Artista creador, int id) {
		super(nombre, descripcion, creador, id);
	}

	public ListaDAO(Lista a) {
		super(a.getNombre(),a.getDescripcion(),a.getCreador(),a.getID());
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
					ps.setString(2, this.Descripcion);
					ps.setInt(3, this.creador.getId());

					ps.executeUpdate();
					// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					// fin de extraer el id generado automaticamente en la db
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
		}
	}

	public void editar() {
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(EDITAR);

				ps.setString(1, this.Nombre);
				ps.setString(2, this.Descripcion);
				ps.setInt(3, this.creador.getId());
				
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
	
	public List<Lista> mostrarTodos() {
		List<Lista> resultado = new ArrayList<Lista>();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs = ps.executeQuery();

				while (rs.next()) {
					
					ArtistaDAO x= new ArtistaDAO();
					Artista xs=x.mostrarPorNombre(rs.getString("creador"));
					resultado.add(new Lista(
						
							rs.getString(""),
							rs.getString(""),
							xs,
							rs.getInt("")
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

}
