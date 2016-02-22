import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

	ScoreboardPrinter scoreboardPrinter;
	ArrayList<Scoreboard> scoreboardList;
	private Scanner roll;
	
	public void createScoreboard(int _theNumberOfPlayers) {
		this.scoreboardList = new ArrayList<Scoreboard>();
		for(int i=0; i<_theNumberOfPlayers; i++){
			Scoreboard scoreboard = new Scoreboard();
			this.scoreboardList.add(scoreboard);
		}
		
		this.scoreboardPrinter = new ScoreboardPrinter(this.scoreboardList);
	}

	public void playGame() {		
		roll = new Scanner(System.in);
		
		FrameStateChecker checker = new FrameStateChecker();
		ScoreCalculator calculator = new ScoreCalculator();
		
		for(int frameId=0; frameId<10; frameId++){
			for(int boardId = 0; boardId < this.scoreboardList.size(); boardId++){
				System.out.println((boardId+1) + "번째 플레이어, " + (frameId+1) + "번째 프레임입니다.");
				Scoreboard currentScoreboard = this.scoreboardList.get(boardId);
				Frame currentFrame = currentScoreboard.frameList.get(frameId);
				
				
				System.out.println("첫 번째 넘어뜨린 핀 수는?");
				int firstRoll = roll.nextInt();
				//넘어뜨린 핀 수는 leftPins보다 많을 수 없다.
				if(firstRoll > currentFrame.leftPins){
					//throw exception.
				}
				
				currentFrame.firstRoll = firstRoll;
				currentFrame.leftPins = currentFrame.leftPins - firstRoll;
				
				String currentFrameState = checker.checkStrike(currentFrame);
				
				if(currentFrameState != "strike"){
					System.out.println("두 번째 넘어뜨린 핀 수는?");
					int secondRoll = roll.nextInt();
					
					if(secondRoll > currentFrame.leftPins){
						//throw exception.
					}
					
					currentFrame.secondRoll = secondRoll;
					currentFrame.leftPins = currentFrame.leftPins - firstRoll;
					
					currentFrameState = checker.checkSpare(currentFrame);
				}
				
				//10번째 프레임이 스트라이크/스페어일 경우 보너스 프레임까지 플레이.
				String tenthState = currentFrame.state;
				if((frameId == 9) && (tenthState == "spare")){
					System.out.println((frameId+1) + "보너스 프레임입니다.");
					currentScoreboard = this.scoreboardList.get(boardId);
					currentFrame = currentScoreboard.frameList.get(frameId+1);
					
					System.out.println("첫 번째 보너스 롤");
					firstRoll = roll.nextInt();
					currentFrame.firstRoll = firstRoll;
					currentFrame.leftPins = currentFrame.leftPins - firstRoll;
				}
				
				if((frameId == 9) && (tenthState == "strike")){
					System.out.println((frameId+1) + "보너스 프레임입니다.");
					currentScoreboard = this.scoreboardList.get(boardId);
					currentFrame = currentScoreboard.frameList.get(frameId+1);
					
					System.out.println("첫 번째 보너스 롤");
					firstRoll = roll.nextInt();
					currentFrame.firstRoll = firstRoll;
					currentFrame.leftPins = currentFrame.leftPins - firstRoll;
					
					System.out.println("두 번째 보너스 롤");
					int secondRoll = roll.nextInt();
					currentFrame.secondRoll = secondRoll;
					currentFrame.leftPins = currentFrame.leftPins - secondRoll;
				}
				
				calculator.calculateEachFrameTotalUntil(frameId, currentScoreboard);
				calculator.calculatePlayerTotal(currentScoreboard);
				this.scoreboardPrinter.printCurrentScore();
				
			}
		}
	}
}
