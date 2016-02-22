import java.util.ArrayList;


public class ScoreCalculator {

	//TODO 열번째 프레임 이후 플레이하는 경우에서 각 프레임 토탈 계산이 이상하게 된다!(스트라이크인 프레임들만.)
	public void calculateEachFrameTotalUntil(int _frameId, Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		for(int i=0; i< (_frameId+1); i++){
			Frame currentFrame = frameList.get(i);
			
			if(_frameId<10){
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
			if(!frameList.get(i).additionalCalculationNeeded){
				total += frameList.get(i).totalScore;
			}
		}
		_currentScoreboard.playerTotal = total;
	}

}
