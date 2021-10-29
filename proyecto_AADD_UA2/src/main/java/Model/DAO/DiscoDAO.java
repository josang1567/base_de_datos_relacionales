package Model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.MARIADB.MariaDBConexion;
import Model.Artista;
import Model.Cancion;
import Model.Disco;

public class DiscoDAO extends Disco {
	private static final String INSERT = "INSERT INTO disco"
			+ "(nombre, fecha_publicacion, foto, n_Reproduciones, id_Artista) VALUES (?,?,?,?,?)";

	private static final String MOSTRARTODOS = "SELECT * FROM cancion";

	private static final String MOSTRARPORNOMBRE = "SELECT *  FROM disco WHERE nombre=?";
	private static final String MOSTRARPORID = "SELECT *  FROM disco WHERE id=?";

	private static final String EDITAR = "UPDATE disco SET nombre=?, fecha_publicacion=?, foto=? WHERE id=?";
	private static final String BORRAR = "DELETE FROM disco WHERE id=?";

	private Connection con = null;

	public DiscoDAO() {
		super();
	}

	public DiscoDAO(String nombre, LocalDate fecha_publicacion, String foto, int n_Reproduciones, int id,
			Artista id_Artista) {
		super(nombre, fecha_publicacion, foto, n_Reproduciones, id, id_Artista);
	}

	public DiscoDAO(Disco d) {
		super(d.getNombre(), d.getFecha_publicacion(), d.getFoto(), d.getN_Reproduciones(), d.getId(),
				d.getId_Artista());
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
					ps.setDate(2, Date.valueOf(this.Fecha_publicacion));
					ps.setString(3, this.Foto);
					ps.setInt(4, this.N_Reproduciones);
					ps.setInt(5, this.id_Artista.getId());

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
				ps.setDate(2, Date.valueOf(this.Fecha_publicacion));
				ps.setString(3, this.Foto);
				ps.setInt(4, this.N_Reproduciones);
				ps.setInt(5, this.id_Artista.getId());

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

	public Disco mostrarPorID(int id) {
		Disco resultado = new DiscoDAO();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(MOSTRARPORID);
				ps.setInt(1, id);
				rs = ps.executeQuery();

				while (rs.next()) {

					ArtistaDAO x = new ArtistaDAO();
					Artista xs = x.mostrarPorID(rs.getInt("id_Artista"));
					resultado=(new Disco(

							rs.getString("Nombre"), rs.getDate("Fecha_publicacion").toLocalDate(), rs.getString("foto"),
							rs.getInt("NumeroReproducciones"), xs

					));
				}
			} catch (SQLException e) {
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

	public List<Disco> mostrarTodos() {
		List<Disco> resultado = new ArrayList<Disco>();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs = ps.executeQuery();

				while (rs.next()) {

					ArtistaDAO x = new ArtistaDAO();
					Artista xs = x.mostrarPorID(rs.getInt("id_Artista"));
					resultado.add(new Disco(

							rs.getString("Nombre"), rs.getDate("Fecha_publicacion").toLocalDate(), rs.getString("foto"),
							rs.getInt("NumeroReproducciones"), xs

					));
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
