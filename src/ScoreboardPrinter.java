import java.util.ArrayList;

public class ScoreboardPrinter {

	int theNumberOfPlayers;
	ArrayList<Scoreboard> scoreboardList;
	
	String indexString = "| player|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |   10   | total |";
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
			
			//TODO theNumberOfPlayers는 scoreboardList의 size로 대체.
			
			//TODO get current frame score;
			System.out.println(this.line);
			System.out.print("|   "+ (i+1) +"   ");
			for(int frameId=0; frameId<10; frameId++){
				int fr = this.scoreboardList.get(i).frameList.get(frameId).firstRoll;
				int sr = this.scoreboardList.get(i).frameList.get(frameId).secondRoll;
				
				System.out.print("|" + fr + " |" + sr + " ");
				
			}
			System.out.println("       |");

			//각 프레임 합산 점수 프린트 
			System.out.print("|       ");
			for(int j=0; j<10; j++){
				int frameTotal = this.scoreboardList.get(i).frameList.get(j).getTotal();
				System.out.print("|  "+frameTotal+"  ");
			}
			System.out.println("|       |");
		}
	}

}
