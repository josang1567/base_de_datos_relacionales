package proyecto_AADD_UA2.proyecto_AADD_UA2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.UsuarioDAO;
import Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class C_Usuario_Controller {
	static UsuarioDAO uD= new UsuarioDAO();
	static String direccionurl="src/main/Imagenes/portadas/";
	static List<String> campos = new ArrayList<String>();
	@FXML
	private TextField nombretext;
	@FXML
	private TextField correotext;
	
	@FXML
	private PasswordField contraseña1text;
	
	@FXML
	private PasswordField contraseña2text;
	@FXML
	private ImageView foto;
	@FXML
	private TextField urltext;
	@FXML
	private Button seleccionarFoto;
	@FXML
	private ImageView cancelar;
	
	@FXML
	//metodo del metodo de agregar
	private void switchtousuarios() throws IOException{
		if(!validarFormulario()) 
			return;
		uD.setNombre(nombretext.getText());
		uD.setCorreo(correotext.getText());
		uD.setContraseña(contraseña1text.getText());
		Utils.saveImage(urltext.getText(),direccionurl+uD.getNombre());
		uD.setFoto(direccionurl+uD.getNombre()+".jpg");
		uD.setFoto(direccionurl);
		try {
			
		}catch(Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Alert");
			alert.setContentText("ERROR AL GUARDAR");
			alert.showAndWait();
		}
		mostrarAlertInfo();
		Utils.tipopestaña="todos";
		App.setRoot("Login");
		
	}
	
	private boolean validarFormulario() {
		boolean result = true;
		
		if (nombretext.getText().trim().equals("")) {
			result = false;
			campos.add("nombre");
		}
		if (correotext.getText().trim().equals("")) {
			result = false;
			campos.add("Correo");
		}
		if (contraseña1text.getText().trim().equals("")) {
			result = false;
			campos.add("contraseña 1");
		}
		if (contraseña2text.getText().trim().equals("")) {
			result = false;
			campos.add("contraseña 2");
		}
		
		if (contraseña1text.getText().trim()!=contraseña2text.getText().trim()) {
			result = false;
			campos.add("Las contraseñas no coinciden");
		}
		
		mostrarAlert();
		return result;
	}
	
	
	private void mostrarAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Alert");
		alert.setContentText("Rellene todos los campos: " + campos);
		alert.showAndWait();
	}
	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Usuario guardado");
		alert.showAndWait();
	}

}
