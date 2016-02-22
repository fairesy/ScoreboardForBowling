import java.util.ArrayList;


public class ScoreCalculator {

	public void calculateEachFrameTotalUntil(int _frameId, Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		for(int i=0; i< (_frameId+1); i++){
			Frame currentFrame = frameList.get(i);
			
			if(_frameId<10){ //->TODO 보너스프레임 플레이할 경우 1~10프레임 토탈 점수가 제대로 계산안되는 문제 발생 
				Frame nextFrame = frameList.get(i+1);
				
				if(currentFrame.state == "strike"){ //지금 프레임이 스트라이크
					if(nextFrame.state == "strike"){ //연속스트라이크
						if(_frameId < 9){
							Frame afterNextFrame = frameList.get(i+2);  
							currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + afterNextFrame.firstRoll;
						}else{ //10번째 프레임에서 연속스트라이크일 경우 보너스프레임의 두 롤을 더한다. 
							currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + nextFrame.secondRoll;
						}
					}else{
						currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + nextFrame.secondRoll;
					}
				}else if(currentFrame.state == "spare"){ //지금 프레임이 스페어
					currentFrame.totalScore = currentFrame.firstRoll + currentFrame.secondRoll + nextFrame.firstRoll;
				}else{ //지금 프레임이 노말
					currentFrame.totalScore = currentFrame.firstRoll + currentFrame.secondRoll;
				}
			}
		}
	}

	public void calculatePlayerTotal(Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		int total = 0;
		for(int i=0; i<10; i++){
			total += frameList.get(i).totalScore;
		}
		_currentScoreboard.playerTotal = total;
	}

}
