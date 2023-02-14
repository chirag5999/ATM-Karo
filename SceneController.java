package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.*;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private AnchorPane switchToDisplay;
	
	@FXML
	private RadioButton resetpin;
	
	@FXML
	private RadioButton withdraw;
	
	@FXML
	private RadioButton deposit;
	
	@FXML
	private RadioButton balance;
	
	@FXML
	private Label label,registerlabel,lowbalance,depositlabel,pinlabel;
	
	@FXML
	private TextField name,dob,mobile,setpin,withdrawamount,depositamount,existingpin,newpin,confirmnewpin;
	
	@FXML
	private PasswordField pin;
	
	@FXML
	private TextField userid;
	
	mysql obj=new mysql();
	
	static String uid;
		
	public void backToMainScene(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToMainScene(ActionEvent event) throws IOException{
		if(name.getText().length()==0||dob.getText().length()==0||mobile.getText().length()==0||setpin.getText().length()==0)
		{
			registerlabel.setText("Incomplete Information");
		}
		else
		{
			uid=""+obj.generateUid();
		    obj.addData(uid,name.getText(),dob.getText(),mobile.getText(),Integer.parseInt(setpin.getText()));
		    FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScene.fxml"));
			Parent root=loader.load();
			MainSceneController msc=loader.getController();
			msc.showUid("Your User ID is "+uid);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	public void changeScene(ActionEvent event) throws IOException{
		uid=""+obj.getUid();
		if(resetpin.isSelected())
		{
			Parent root = FXMLLoader.load(getClass().getResource("ChangePinScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if(withdraw.isSelected())
		{
			Parent root = FXMLLoader.load(getClass().getResource("WithdrawScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if(deposit.isSelected())
		{
			Parent root = FXMLLoader.load(getClass().getResource("DepositScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if(balance.isSelected())
		{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayScene.fxml"));
			Parent root=loader.load();
			DisplaySceneController dsc=loader.getController();
			dsc.showbalance("Your current balance is Rs."+obj.getBalance(uid));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}
	
	public void switchToDisplay1(ActionEvent event) throws IOException{
		if(withdrawamount.getText().length()==0)
		{
			lowbalance.setText("Please enter the withdrawal amount");
		}
		else if(!obj.checkBalance(Integer.parseInt(withdrawamount.getText()),uid))
		{
			lowbalance.setText("Insufficient balance");	
		}
		else
		{
			FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayScene.fxml"));
			Parent root=loader.load();
			DisplaySceneController dsc=loader.getController();
			dsc.showbalance("Your updated balance is Rs."+obj.getBalance(uid));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void switchToDisplay2(ActionEvent event) throws IOException{
		if(depositamount.getText().length()==0)
		{
			depositlabel.setText("Please enter the deposit amount");
		}
		else
		{
			obj.updateBalance(Integer.parseInt(depositamount.getText()),uid);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayScene.fxml"));
			Parent root=loader.load();
			DisplaySceneController dsc=loader.getController();
			dsc.showbalance("Your updated balance is Rs."+obj.getBalance(uid));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	public void BackToLogin(ActionEvent event ) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void resetPin(ActionEvent event) throws IOException{
		if(existingpin.getText().length()==0||newpin.getText().length()==0||confirmnewpin.getText().length()==0)
		{
			pinlabel.setText("Incomplete Information");
		}
		else if(!obj.checkPin(uid,existingpin.getText()))
		{
			pinlabel.setText("Existing pin wrong");
		}
		else if(existingpin.getText().equals(newpin.getText()))
		{
			pinlabel.setText("New pin cannot be same as existing pin");
		}
		else if(!confirmnewpin.getText().equals(newpin.getText()))
		{
			pinlabel.setText("Confirm new pin and new pin doesn't match");
		}
		else
		{
			obj.changePin(uid,newpin.getText());
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
}
