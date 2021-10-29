package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.MARIADB.MariaDBConexion;
import Model.Cancion;
import Model.Disco;

public class CancionDAO extends Cancion {
	private static final String INSERT = "INSERT INTO cancion"
			+ "(nombre, duracion, genero, id_Disco) VALUES (?,?,?,?)";

	private static final String MOSTRARTODOS = "SELECT * FROM cancion ";

	private static final String MOSTRARPORNOMBRE = "SELECT *  FROM cancion WHERE nombre=?";
	private static final String MOSTRARPORDISCO = "SELECT *  FROM cancion WHERE id_disco=?";
	private static final String EDITAR = "UPDATE cancion SET nombre=?,duracion=?, genero=?, n_reproducciones=? WHERE id=?";
	private static final String BORRAR = "DELETE FROM cancion WHERE id=?";

	private Connection con = null;

	public CancionDAO() {
		super();
	}


	public CancionDAO(String nombre, int duracion, String genero, int n_reproducciones, int id, Disco id_Disco) {
		super(nombre, duracion, genero, n_reproducciones, id, id_Disco);
	}


	public CancionDAO(Cancion c) {
		super(c.getNombre(), c.getDuracion(), c.getGenero(), c.getN_reproducciones(), c.getId(),c.getId_Disco());
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
					ps.setInt(2, this.Duracion);
					ps.setString(3, this.getGenero());
					ps.setInt(4, this.id_Disco.getId());

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
				ps.setInt(2, this.Duracion);
				ps.setString(3, this.getGenero());
				ps.setInt(4, this.id_Disco.getId());
				
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
	
	public List<Cancion> mostrarTodos() {
		List<Cancion> resultado = new ArrayList<Cancion>();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs = ps.executeQuery();

				while (rs.next()) {
					
					DiscoDAO x= new DiscoDAO();
					Disco xs=x.mostrarPorID(rs.getInt("id_disco"));
					resultado.add(new Cancion(
						
							rs.getString("Nombre"),
							rs.getInt("Duracion"),
							rs.getString("Genero"), 
							rs.getInt("NumeroReproducciones"),
							xs
							
							
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
