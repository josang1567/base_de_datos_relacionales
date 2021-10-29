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

public class ArtistaDAO extends Artista {
	private static final String INSERT = "INSERT INTO artista"
			+ " (nombre, nacionalidad, foto) VALUES (?,?,?)";

	private static final String MOSTRARTODOS = "SELECT * FROM artista";

	private static final String MOSTRARPORNOMBRE = "SELECT *  FROM artista WHERE nombre=?";
	private static final String MOSTRARPORID = "SELECT *  FROM artista WHERE id=?";

	private static final String EDITAR = "UPDATE artista SET nombre=?, nacionalidad=?, foto=? WHERE id=?";
	private static final String BORRAR = "DELETE FROM artista WHERE id=?";

	private Connection con = null;

	public ArtistaDAO() {
		super();
	}

	public ArtistaDAO(String nombre, String nacionalidad, String foto,int id) {
		super(nombre, nacionalidad, foto,id);
	}
	
	public ArtistaDAO(Artista a) {
		super(a.getNombre(),a.getNacionalidad(),a.getFoto(),a.getId());
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
					ps.setString(2, this.Nacionalidad);
					ps.setString(3, this.Foto);

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
				ps.setString(2, this.Nacionalidad);
				ps.setString(3, this.Foto);
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
	
	public List<Artista> mostrarTodos() {
		List<Artista> resultado = new ArrayList<Artista>();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs = ps.executeQuery();

				while (rs.next()) {
					
					ArtistaDAO x= new ArtistaDAO();
					
					resultado.add(new Artista(
						
							rs.getString("Nombre"),
							rs.getString("Nacionalidad"), 
							rs.getString("foto")
							
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

	public Artista mostrarPorID(int id) {
		// TODO Auto-generated method stub
	Artista resultado= new Artista();
		
		con=MariaDBConexion.getConexion();
		if(con != null) {
			PreparedStatement ps= null;
			ResultSet rs= null;
			try {
				ps= con.prepareStatement(MOSTRARPORID);
				ps.setInt(1, id );
				rs = ps.executeQuery();

				while (rs.next()) {
					
					ArtistaDAO x= new ArtistaDAO();
					
					resultado=new Artista(
						
							rs.getString("Nombre"),
							rs.getString("Nacionalidad"), 
							rs.getString("foto")
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

	public Artista mostrarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		Artista resultado= new Artista();
		
		con=MariaDBConexion.getConexion();
		if(con != null) {
			PreparedStatement ps= null;
			ResultSet rs= null;
			try {
				ps= con.prepareStatement(MOSTRARPORNOMBRE);
				ps.setString(1, nombre );
				rs = ps.executeQuery();

				while (rs.next()) {
					
					ArtistaDAO x= new ArtistaDAO();
					
					resultado=new Artista(
						
							rs.getString("Nombre"),
							rs.getString("Nacionalidad"), 
							rs.getString("foto")
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
