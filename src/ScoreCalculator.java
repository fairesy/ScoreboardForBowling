import java.util.ArrayList;

public class ScoreCalculator {

	public void calculateEachFrameTotalUntil(int _frameId, Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		
		//보너스프레임까지 플레이한 경우. 
		if(_frameId == 10){
			for(int i=0; i<10; i++){
				Frame currentFrame = frameList.get(i);
				if(currentFrame.state == "strike"){
					Frame nextFrame = frameList.get(i+1);
					if(nextFrame.state == "strike"){
						if(i == 9){//마지막 프레임에서 스트라이크, 보너스프레임에서 또 스트라이크였을 때.
							currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + nextFrame.secondRoll;							
						}else{
							Frame afterNextFrame = frameList.get(i+2);
							currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + afterNextFrame.firstRoll;
						}
					}else{
						currentFrame.totalScore = currentFrame.firstRoll + nextFrame.firstRoll + nextFrame.secondRoll;
					}
				}else if(currentFrame.state == "spare"){
					Frame nextFrame = frameList.get(i+1);
					currentFrame.totalScore = currentFrame.firstRoll + currentFrame.secondRoll + nextFrame.firstRoll;
				}else{
					currentFrame.totalScore = currentFrame.firstRoll + currentFrame.secondRoll;
				}
			}
		}
		
		else{
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
	}

	public void calculatePlayerTotal(Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList= _currentScoreboard.frameList;
		int total = 0;
		for(int i=0; i<11; i++){
//			total += frameList.get(i).totalScore;
			//TODO 토탈 구하는 조건 수정 필요 : 마지막프레임이 노말이 아닐 경우 총합이 더해지지 않고 끝난다. 
			if(!frameList.get(i).additionalCalculationNeeded){
				total += frameList.get(i).totalScore;
			}
		}
		_currentScoreboard.playerTotal = total;
	}

}
