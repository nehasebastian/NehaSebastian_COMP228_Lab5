package exercise1;

import javafx.beans.property.SimpleStringProperty;

public class Game {
	

	
	private final SimpleStringProperty  gameName;
	
	private final SimpleStringProperty  score;
	
	private final SimpleStringProperty  dateOfPlay;

	public Game(String gameName, String score, String dateOfPlay) {
		super();
		this.gameName = new SimpleStringProperty(gameName);
		this.score = new SimpleStringProperty(score);
		this.dateOfPlay = new SimpleStringProperty(dateOfPlay);
	}

	
	
	  public String getGameName() {
          return gameName.get();
      }

      public void setGameName(String gName) {
          gameName.set(gName);
      }

      public String getScore() {
          return score.get();
      }

      public void setScore(String gScore) {
    	  score.set(gScore);
      }

      public String getDateOfPlay() {
          return dateOfPlay.get();
      }

      public void setDateOfPlay(String DOP) {
    	  dateOfPlay.set(DOP);
      }
	

	

	

}
