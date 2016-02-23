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
		String currentFrameState;
		
		for(int frameId=0; frameId<10; frameId++){
			for(int boardId = 0; boardId < this.scoreboardList.size(); boardId++){
				System.out.println((boardId+1) + "번째 플레이어, " + (frameId+1) + "번째 프레임입니다.");
				Scoreboard currentScoreboard = this.scoreboardList.get(boardId);
				Frame currentFrame = currentScoreboard.frameList.get(frameId);
				
				playFirstRoll(currentFrame);
				currentFrameState = checker.checkStrike(currentFrame);
				
				if(currentFrameState != "strike"){
					playSecondRoll(currentFrame);
					currentFrameState = checker.checkSpare(currentFrame);
				}
				
				//10번째 프레임이 스트라이크/스페어일 경우 보너스 프레임까지 플레이.
				if((frameId == 9) && (currentFrame.state != "normal")){
					playBonusRolls(currentFrame.state, currentScoreboard);
					frameId++;
				}
				
				calculator.calculateEachFrameTotalUntil(frameId, currentScoreboard);
				calculator.calculatePlayerTotal(currentScoreboard);
				checker.checkPrintableFrameTotals(frameId, currentScoreboard);
				this.scoreboardPrinter.printCurrentScore();
			}
		}
	}


	private void playFirstRoll(Frame currentFrame) {
		try{
			System.out.println("첫 번째 넘어뜨린 핀 수는?");
			int firstRoll = roll.nextInt();
			
			if(firstRoll > currentFrame.leftPins){
				throw new IllegalArgumentException();
			}
			currentFrame.firstRoll = firstRoll;
			currentFrame.leftPins = currentFrame.leftPins - firstRoll;
		}catch(IllegalArgumentException e){
			System.out.println("넘어뜨린 핀 수는 남아있는 핀 수보다 많을 수 없습니다!");
			playFirstRoll(currentFrame);
		}
	}
	
	private void playSecondRoll(Frame currentFrame) {
		try{
			System.out.println("두 번째 넘어뜨린 핀 수는?");
			int secondRoll = roll.nextInt();
			
			if(secondRoll > currentFrame.leftPins){
				throw new IllegalArgumentException();
			}
			currentFrame.secondRoll = secondRoll;
			currentFrame.leftPins = currentFrame.leftPins - secondRoll;		
		}catch(IllegalArgumentException e){
			System.out.println("넘어뜨린 핀 수는 남아있는 핀 수보다 많을 수 없습니다!");
			playSecondRoll(currentFrame);
		}
	}
	
	private void playBonusRolls(String _tenthState, Scoreboard _currentScoreboard) {
		System.out.println("보너스 프레임입니다.");
		Frame currentFrame = _currentScoreboard.frameList.get(10);
		
		System.out.println("첫 번째 보너스 롤");
		int firstRoll = roll.nextInt();
		currentFrame.firstRoll = firstRoll;
		currentFrame.leftPins = currentFrame.leftPins - firstRoll;
		
		if(_tenthState == "strike"){		
			System.out.println("두 번째 보너스 롤");
			int secondRoll = roll.nextInt();
			currentFrame.secondRoll = secondRoll;
			currentFrame.leftPins = currentFrame.leftPins - secondRoll;
		}		
	}
}
