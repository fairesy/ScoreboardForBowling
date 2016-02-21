import java.util.ArrayList;


public class ScoreCalculator {

	public void calculateEachFrameTotalUntil(int _frameId, Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		for(int i=0; i<_frameId; i++){
			Frame currentFrame = frameList.get(i);
			
			//TODO index out of boundary 예외처리 : 9,10프레임에서 i, i+2 문제발생 
			Frame nextFrame = frameList.get(i+1);
			
			if(currentFrame.state == "strike"){ //지금 프레임이 스트라이크
				if(nextFrame.state == "strike"){ //연속스트라이크 
					Frame afterNextFrame = frameList.get(i+2);
					currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + afterNextFrame.firstRoll;
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

	public void calculatePlayerTotal(Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		int total = 0;
		for(int i=0; i<10; i++){
			total += frameList.get(i).totalScore;
		}
		_currentScoreboard.playerTotal = total;
	}

}
