package exercise1;

import java.sql.*;
import java.util.List;

import javax.swing.text.TabableView;

import org.w3c.dom.stylesheets.LinkStyle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DisplayUI {
	
	
	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;database=Game";

	String DATABASE_URL = "jdbc:sqlserver://localhost:1433;database=Game;integratedSecurity=true";
	//static final String USERNAME = "root";
	//static final String PASSWORD = "admin";
	
	public DisplayUI() {
		super();
		//getGameCode();
	 
	}
	//ObservableList<String> options = FXCollections.observableArrayList(listOfSomething);
	private final TableView<Game> table = new TableView<>();
    private final ObservableList<Game> data =
    		FXCollections.observableArrayList();
	//TableView<ObservableList<Game>> table = new TableView<>();
	ObservableList<String> options =  FXCollections.observableArrayList();
	//ComboBox<String> gameBox = new ComboBox<String>(options);
	ObservableList<String> subList =FXCollections.observableArrayList();
	private ObservableList<ObservableList> data1 = FXCollections.observableArrayList(); ;
    private TableView tableview;
	
	TextField fistName = new TextField();
	TextField lastName = new TextField();
	TextField postalCode = new TextField();
	TextField searchId = new TextField();
	TextField reportId = new TextField();
	TextField playerId = new TextField();
	
	TextField textarea = new TextField();
	TextField address = new TextField();
	TextField province = new TextField();
	TextField phoneNumber = new TextField();
	
	public void displayInfo( Stage pStage){

	// TODO Auto-generated method stub
	
			HBox hbMain = new HBox();
			hbMain.setSpacing(20);
			hbMain.setBorder(null);
			ListView<String> nList = new ListView<>();
			nList.setPrefSize(200, 50);
			
			
			BorderPane panel =new BorderPane();
			// TODO Auto-generated method stub
			GridPane pane = new GridPane();
			pane.setAlignment(Pos.TOP_LEFT);
			pane.setHgap(5.5);  // Sets horizontal gap
			pane.setVgap(5.5);	// Sets vertical gap
			
			pane.add(new Label("First Name:"), 1, 2); // Component, Column, Row
			pane.add(fistName, 2, 2);
			pane.add(new Label("Last Name:"), 1, 3);
			pane.add(lastName, 2, 3);
			pane.add(new Label("Postal Code:"), 1, 4);
			pane.add(postalCode, 2, 4);
			
			Label playerIdL = new Label("Your Player ID:");
			pane.add(playerIdL, 1, 6);
			pane.add(playerId, 2, 6);
			
			pane.add(new Label("Addres :"), 1, 7); // Component, Column, Row
			pane.add(address, 2, 7);
			pane.add(new Label("Province:"), 1, 8);
			pane.add(province, 2, 8);
			pane.add(new Label("Phone Number:"), 1, 9);
			pane.add(phoneNumber, 2, 9);
			
			
			Button btAdd1 = new Button("Add Details");
			pane.add(btAdd1, 1, 11);
			
			Button clear = new Button("Clear Details");
			pane.add(clear, 2, 11);

			pane.add(reportId, 1, 15);
			Button report = new Button("Report");
			pane.add(report, 2, 15);
			
			
			GridPane.setHalignment(report, HPos.CENTER);
			
			//panel.setLeft(pane);
			
			Button search = new Button("Search");
			
			HBox h1 = new HBox(new Label("Search Id: ") , searchId,search);
			h1.setSpacing(5);
			
			h1.setAlignment(Pos.CENTER);
			//panel.setCenter(h1);
			
			HBox hb = new HBox();
			 final Label label = new Label("Game Details");
		        label.setFont(new Font("Arial", 14));
		        table.setEditable(true);
			 
			 	TableColumn gameNameCol = new TableColumn("Game Name");
		        gameNameCol.setMinWidth(100);
		        gameNameCol.setCellValueFactory(
		                new PropertyValueFactory<>("gameName"));
		 
		        TableColumn scoreCol = new TableColumn("Score");
		        scoreCol.setMinWidth(100);
		        scoreCol.setCellValueFactory(
		                new PropertyValueFactory<>("score"));
		 
		        TableColumn dateCol = new TableColumn("Date Of Play");
		        dateCol.setMinWidth(200);
		        dateCol.setCellValueFactory(
		                new PropertyValueFactory<>("dateOfPlay"));
		 
		        table.setItems(data);
		        table.getColumns().addAll(gameNameCol, scoreCol, dateCol);
		        
		        table.setFixedCellSize(20);
		        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(3.01)));
		        table.minHeightProperty().bind(table.prefHeightProperty());
		        table.maxHeightProperty().bind(table.prefHeightProperty());
		        
		        final TextField addGameName = new TextField();
		        addGameName.setPromptText("Game Name");
		        addGameName.setMaxWidth(gameNameCol.getPrefWidth());
		        final TextField addScore = new TextField();
		        addScore.setMaxWidth(scoreCol.getPrefWidth());
		        addScore.setPromptText("Your Score");
		        final TextField addDate = new TextField();
		        addDate.setMaxWidth(dateCol.getPrefWidth());
		        addDate.setPromptText("Date you played");
		 
		        final Button addButton = new Button("Add");
		        addButton.setOnAction((ActionEvent e) -> {
		            data.add(new Game(
		            		addGameName.getText(),
		            		addScore.getText(),
		            		addDate.getText()));
		            
		            addGameName.clear();
		            addScore.clear();
		            addDate.clear();
		        });
		 
		        hb.getChildren().addAll(addGameName, addScore, addDate, addButton);
		        hb.setSpacing(3);
			
			VBox vb = new VBox(label,table,hb);
			vb.setSpacing(20);

			hbMain.getChildren().addAll(pane,h1,vb);
			//panel.setRight(vb);
			
			
			// nList.setItems(subList); 
		 
		//GridPane.setHalignment(textarea, HPos.CENTER);
			
			TableColumn player = new TableColumn("Name");
			player.setMinWidth(100);
		
	        TableColumn game = new TableColumn("Game");
	        game.setMinWidth(100);
	      
	 
	        TableColumn score1 = new TableColumn("Score");
	        score1.setMinWidth(200);
	        
	 
	       /* tableview.setItems(data1);
	      //
	        tableview.getColumns().addAll(player, game, score1);
	        */
	     /*   tableview.setFixedCellSize(20);
	        tableview.prefHeightProperty().bind(tableview.fixedCellSizeProperty().multiply(Bindings.size(tableview.getItems()).add(3.01)));
	        tableview.minHeightProperty().bind(tableview.prefHeightProperty());
	        tableview.maxHeightProperty().bind(tableview.prefHeightProperty());*/
			
			VBox vb1 = new VBox(hbMain,textarea);
			
			
			panel.setTop(vb1);
			
			report.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					
					getreport();
					
					
				}

			
			});
			
			clear.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					
					fistName.setText("");
			        lastName.setText("");
			        postalCode.setText("");
			        textarea.setText("");
			        searchId.setText("");
			        playerId.setText("");
			        phoneNumber.setText("");
			        province.setText("");
			        address.setText("");
			        data.clear();
					
				}
			});
			
			btAdd1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					insertDB();
					
					
				}

			});
			
			search.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
				/*	playerId.setVisible(true);
					playerIdL.setVisible(true);*/
					search();
					
				}

			
			});
			
			
			Scene scene = new Scene(panel, 1000 ,500);
			pStage.setTitle("Player Information");
			pStage.setScene(scene);
			pStage.show();
			
		
		}
	

	private void insertDB() {
		// TODO Auto-generated method stub

		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
	    try {
	    	con=DriverManager.getConnection(DATABASE_URL);
	        stmt =  con.createStatement();
	        stmt =  con.createStatement();
	        
	        // to check already existing ID and update
	        if(!playerId.getText().isEmpty())
	        {
	     
	        	 rs = stmt.executeQuery("SELECT PLAYER_ID AS ID FROM PLAYER WHERE PLAYER_ID ="+playerId.getText().trim()+"");
	        	 ResultSet rsNew =null;
	        	
	        	  if(rs.next())
	  	        {
	        		  String playerId = rs.getString("id");
	  	        	 
	        		  	rs = stmt.executeQuery("UPDATE PLAYER SET FIRST_NAME='"+fistName.getText().trim()+
	        		  			"',LAST_NAME='"+lastName.getText().trim()+"',PROVINCE='"+province.getText().trim()
	        		  			+"',PHONENUMBER='"+phoneNumber.getText().trim()+"',POSTAL_CODE='"+postalCode.getText().trim()+
	        		  			"',ADDRESS='"+address.getText().trim()+"' WHERE PLAYER_ID ="+playerId+"");
	  	        	
	  	 		   }
	        	  else{
	        		  //playerId = playerId.getText().trim();
					  String query = "INSERT INTO PLAYER (PLAYER_ID,FIRST_NAME,LAST_NAME,POSTAL_CODE,PROVINCE,ADDRESS,PHONENUMBER) VALUES (?,?,?,?,?,?,?)";
					  pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
					  pstmt.setString(1, playerId.getText());
					  pstmt.setString(2, fistName.getText().trim());
					  pstmt.setString(3,lastName.getText().trim());
					  pstmt.setString(4,postalCode.getText().trim());
					  pstmt.setString(5,province.getText().trim());
					  pstmt.setString(6,address.getText().trim());
					  pstmt.setInt(7,Integer.parseInt(phoneNumber.getText().trim()));

						//pstmt.setInt(3, 10000);
					  pstmt.executeUpdate();
					  rsNew = pstmt.getGeneratedKeys();
					  /*String SQL = "INSERT INTO PLAYER (PLAYER_ID,FIRST_NAME,LAST_NAME,POSTAL_CODE,PROVINCE,ADDRESS,PHONENUMBER) VALUES ('"+playerId.getText()+"','"+fistName.getText().trim()+"','"+lastName.getText().trim()+"','"
							  +postalCode.getText().trim()+"','"+province.getText().trim()+"','"+address.getText().trim()
							  +"','"+phoneNumber.getText().trim()+"')";
	        		  *//*stmt.executeQuery("INSERT INTO PLAYER (PLAYER_ID,FIRST_NAME,LAST_NAME,POSTAL_CODE,PROVINCE,ADDRESS,PHONENUMBER) VALUES ('"+playerId.getText()+"','"+fistName.getText().trim()+"','"+lastName.getText().trim()+"','"
	        				  				+postalCode.getText().trim()+"','"+province.getText().trim()+"','"+address.getText().trim()
	        				  				+"','"+phoneNumber.getText().trim()+"')");
	        		  */
	        			for(int i=0;i<data.size();i++){
	        				System.out.println("@@@@@@@@@@@@@@@@@@@@@@2" + data.get(i).getGameName());
	        				String q = "INSERT INTO GAMES (GAME_TITLE) VALUES(?)";
							pstmt1 = con.prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
	        				pstmt1.setString(1,data.get(i).getGameName());
	        				pstmt1.executeUpdate();
	        				rs = pstmt1.getGeneratedKeys();
	        				//rs = stmt.executeQuery("INSERT INTO GAMES (GAME_TITLE) VALUES('"+data.get(i).getGameName()+"')");
	        				 if(rs.next())
	        		  	        {
	        		  	        	 ResultSet rsN = null;
	        		  	        	 //to fetch the auto generated game ID
	        		  	        	 rsN = stmt.executeQuery("SELECT MAX(GAME_ID) AS id FROM GAMES");
	        		  	        	 while(rsN.next()){
	        		  	        		 String gameId = rsN.getString("id");
	        		  	        		String q2 = "INSERT INTO PLAYERANDGAME VALUES( ?,?,?,?)";
										 pstmt1 = con.prepareStatement(q2,Statement.RETURN_GENERATED_KEYS);
										 pstmt1.setInt(1,Integer.parseInt(gameId));
										 pstmt1.setInt(2,Integer.parseInt(this.playerId.getText()));
										 pstmt1.setDate(3,Date.valueOf(data.get(i).getDateOfPlay()));
										 pstmt1.setInt(4,Integer.parseInt(data.get(i).getScore()));
										 pstmt1.executeUpdate();

	        		  	        	ResultSet rsI = pstmt1.getGeneratedKeys();
	        		  	        	 }
	        		  	        }
	        				
	        			}
	        	  }
	  	        	
	  		       
	  	        
	        }


			fistName.setText("");
			lastName.setText("");
			postalCode.setText("");
			textarea.setText("");
			searchId.setText("");
			playerId.setText("");
			phoneNumber.setText("");
			province.setText("");
			address.setText("");
			data.clear();
	        
	    } 
	 
	  catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            rs.close();
	            
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private void search() {
		// TODO Auto-generated method stub
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	con=DriverManager.getConnection(DATABASE_URL);
	        stmt =  con.createStatement();
	       
	        rs =  stmt.executeQuery("SELECT * FROM PLAYER WHERE PLAYER_ID ='"+searchId.getText().trim()+"'"); //,'"+lastName.getText().trim()+"','"+postalCode.getText().trim()+"')");
	        while(rs.next()) {    
	        	fistName.setText(rs.getString("FIRST_NAME"));
		        lastName.setText(rs.getString("LAST_NAME"));
		        postalCode.setText(rs.getString("POSTAL_CODE"));
		        playerId.setText(rs.getString("PLAYER_ID"));
		        address.setText(rs.getString("ADDRESS"));
		        province.setText(rs.getString("PROVINCE"));
		        phoneNumber.setText(rs.getString("PHONENUMBER"));
	        }
	    } 
	  catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            rs.close();
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	private void getreport() {
		// TODO Auto-generated method stub
         String value = "ID"+ "\t\t\t"+"FIRST NAME" + "\t\t\t\t\t"+"LAST NAME "
				 + "\t\t\t\t\t\t"+"POSTAL CODE"+ "\n";
         

 		Connection con = null;
 	    CallableStatement stmt = null;
 	    ResultSet rs = null;
 	    
 	   data1 = FXCollections.observableArrayList();
        
        try {
        	con=DriverManager.getConnection(DATABASE_URL);


			/*System.out.println(".............................."+reportId.getText().trim());
			rs = stmt.executeQuery("SELECT P.Player_id, P.First_Name, G.Game_title , GP.score, GP.PLAYING_DATE " +
					"FROM PLAYER P INNER JOIN PLAYERANDGAME PG ON P.PLAYER_ID = PG.PLAYER_ID INNER JOIN GAMES G ON " +
					"PG.GAME_ID = G.GAME_ID where P.PLAYER_ID ="+reportId.getText().trim()+"");*/


			//return the select PLAYER, GAME , SCORE result over the three table join condition
			//calling the procedure
	        stmt = con.prepareCall("{call getGamePlayerDetails(?)}");

            stmt.setInt(1,Integer.parseInt(reportId.getText().trim()));
            rs = stmt.executeQuery();
			System.out.println(".............................."+rs.next());
            
            while(rs.next()) {
              //  value = value +rs.getString("PLAYER_ID")+"\t\t\t\t\t" +rs.getString("FIRST_NAME") + "\t\t\t\t\t"+rs.getString("LAST_NAME") + "\t\t\t\t\t"+rs.getString("POSTAL_CODE") + "\n";
            	
                  for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                     
                      final int j = i;                
                      TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                      col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                          public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                              return new SimpleStringProperty(param.getValue().get(j).toString());                        
                          }                    
                      });

                      tableview.getColumns().addAll(col); 
                      System.out.println("Column ["+i+"] ");
                  }

                
                  while(rs.next()){
                      //Iterate Row
                      ObservableList<String> row = FXCollections.observableArrayList();
                      for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                          //Iterate Column
                          row.add(rs.getString(i));
                      }
                     // System.out.println("Row [1] added "+row );
                      data1.add(row);

                  }

                  //FINALLY ADDED TO TableView
                  tableview.setItems(data1);
            
            }            
           // textarea.setText(value);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }	
		
	}
}
