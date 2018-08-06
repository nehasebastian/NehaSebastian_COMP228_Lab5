package exercise1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application{
	
	Connection con = null;  
    CallableStatement cstmt = null;  
    ResultSet rs = null;

	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		DisplayUI displayUI = new DisplayUI();
		displayUI.displayInfo(primaryStage);
	}
	
}
