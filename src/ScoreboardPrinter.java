
public class ScoreboardPrinter {

	public void init(int theNumberOfPlayers) {
		// TODO Auto-generated method stub
		System.out.println("| player|  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |   10   |");
		
		for(int i=0 ; i<theNumberOfPlayers ; i++){
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.println("|   "+ (i+1) +"   |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
			System.out.println("|       |     |     |     |     |     |     |     |     |     |        |");
		}
	}

}
