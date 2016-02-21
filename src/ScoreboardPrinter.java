import java.util.ArrayList;

public class ScoreboardPrinter {

	int theNumberOfPlayers;
	ArrayList<Scoreboard> scoreboardList;
	
	String indexString = "| player|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10   | total |";
	String line = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
	
	public ScoreboardPrinter(ArrayList<Scoreboard> _scoreboardList) {
		this.scoreboardList = _scoreboardList;
	}

	public void init(int _theNumberOfPlayers) {
		System.out.println(this.indexString);
		this.theNumberOfPlayers = _theNumberOfPlayers;
		
		for(int i=0 ; i<this.theNumberOfPlayers ; i++){
			System.out.println(this.line);
			System.out.println("|   "+ (i+1) +"   |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |       |");
			System.out.println("|       |     |     |     |     |     |     |     |     |     |        |       |");
		}
	}

	public void printCurrentScore() {
		//각 프레임마다 firstRoll, secondRoll score get & print
		System.out.println(this.indexString);
		for(int i=0 ; i<this.scoreboardList.size() ; i++){
			Scoreboard currentBoard = this.scoreboardList.get(i);
			
			//get current frame score;
			System.out.println(this.line);
			System.out.print("|   "+ (i+1) +"   ");
			for(int frameId=0; frameId<10; frameId++){
				Frame currentFrame = currentBoard.frameList.get(frameId);
				int fr = currentFrame.firstRoll;
				
				if(fr == 10){ System.out.print("|  X  "); }
				else{
					int sr = currentFrame.secondRoll;
					if(currentFrame.leftPins == 0){ //스페어
						if(fr == 0){ System.out.print("|-"); }
						else{ System.out.print("|" + fr); }
						System.out.print(" |/ ");
						
					}else{ //노멀 
						if(fr == 0){ System.out.print("|-"); }
						else{ System.out.print("|" + fr); }
						
						if(sr == 0){ System.out.print(" |- "); }
						else{ System.out.print(" |" + sr + " "); }
					}
				}
			}
			System.out.println("       |");

			//각 프레임 합산 점수 프린트 
			System.out.print("|       ");
			for(int j=0; j<10; j++){
				Frame currentFrame = currentBoard.frameList.get(j);
				int frameTotal = currentFrame.totalScore;
			
				if(frameTotal>9){ System.out.print("| "+frameTotal+"  "); }
				else{ System.out.print("|  "+frameTotal+"  "); }
			}
			System.out.println("| "+ currentBoard.playerTotal +" |");
		}
	}

}
