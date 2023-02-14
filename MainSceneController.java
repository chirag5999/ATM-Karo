package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainSceneController {
	@FXML
	private AnchorPane mainScene;
	
	@FXML
	private Label label;
	
	@FXML
	private TextField userid,pin;
	
	mysql obj=new mysql();
	
	public void showUid(String uid)
	{
		label.setText(uid);
	}
	public void switchToLogin(ActionEvent event) throws IOException{
		if(userid.getText().length()==0||pin.getText().length()==0)
		{
			label.setText("Incomplete Information");
		}
		else if(obj.check(userid.getText(), pin.getText()))
		{
			obj.setUid(Integer.parseInt(userid.getText()));
			Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			label.setText("Incorrect UserId or Pin");
		}
	}
	
	public void switchToRegister(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

