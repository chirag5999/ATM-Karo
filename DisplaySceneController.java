package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class DisplaySceneController {
	@FXML
	private AnchorPane switchToDisplay;
	
	@FXML
	private Label receiptlabel;
	
	public void showbalance(String balance)
	{
		receiptlabel.setText(balance);
	}
	public void exitScene(ActionEvent event) throws IOException{
		Stage stage = (Stage)switchToDisplay.getScene().getWindow();
		stage.close();
	}
	public void BackToLogin(ActionEvent event ) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

