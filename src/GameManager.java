import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

	ScoreboardPrinter scoreboardPrinter;
	ArrayList<Scoreboard> scoreboardList;
	
	public void createScoreboard(int _theNumberOfPlayers) {
		this.scoreboardList = new ArrayList<Scoreboard>();
		for(int i=0; i<_theNumberOfPlayers; i++){
			Scoreboard scoreboard = new Scoreboard();
			this.scoreboardList.add(scoreboard);
		}
		
		this.scoreboardPrinter = new ScoreboardPrinter(this.scoreboardList);
		
	}

	public void playGame() {
		
		//TODO 여러명 플레이어 가능하게. 
		
		Scanner roll = new Scanner(System.in);
		
		FrameStateChecker checker = new FrameStateChecker();
		
		for(int frameId=0; frameId<10; frameId++){
			System.out.println((frameId+1) + "번째 프레임입니다.");
			//TODO 여러명 플레이어 가능하게. -> scoreboardList.get(0)부분.
			Frame currentFrame = this.scoreboardList.get(0).frameList.get(frameId);
			
			
			System.out.println("첫 번째 넘어뜨린 핀 수는?");
			int firstRoll = roll.nextInt();
			//넘어뜨린 핀 수는 leftPins보다 많 수 없다.
			if(firstRoll > currentFrame.leftPins){
				//throw exception.
			}
			
			currentFrame.firstRoll = firstRoll;
			currentFrame.leftPins = currentFrame.leftPins - firstRoll;
			
			String currentFrameState = checker.checkFirst(currentFrame);
			
			if(currentFrameState == "strike"){
				this.scoreboardPrinter.printCurrentScore();
			}else{
				System.out.println("두 번째 넘어뜨린 핀 수는?");
				int secondRoll = roll.nextInt();
				
				if(secondRoll > currentFrame.leftPins){
					//throw exception.
				}
				
				currentFrame.secondRoll = secondRoll;
				currentFrame.leftPins = currentFrame.leftPins - firstRoll;
				
				currentFrameState = checker.checkSecond(currentFrame);
				
				this.scoreboardPrinter.printCurrentScore();
			}
			
		}
	}
}
