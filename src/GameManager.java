import java.util.Scanner;

public class GameManager {

	public void createScoreboard(int theNumberOfPlayers) {
		// TODO Auto-generated method stub
		ScoreboardPrinter printer = new ScoreboardPrinter();
		printer.init(theNumberOfPlayers);
	}

	public void playGame() {
		// TODO Auto-generated method stub
		boolean isGamePlaying = true;
		int frameCount = 1;
		while(isGamePlaying){
			
			if(frameCount>10){
				isGamePlaying = false;
			}
			
			System.out.println(frameCount + "번째 프레임입니다.");
			
			//한프레임 플레이(공 2번)
			Scanner roll = new Scanner(System.in);
			System.out.println("첫 번째 넘어뜨린 핀 수는?");
			int firstRoll = roll.nextInt();
			
			System.out.println("두 번째 넘어뜨린 핀 수는?");
			int secondRoll = roll.nextInt();
			
			frameCount++;
		}
	}

}
