import java.util.ArrayList;

public class FrameStateChecker {

	String strike = "strike";
	String spare = "spare";
	String normal = "normal";
	
	public String checkStrike(Frame _currentFrame) {
		if(_currentFrame.leftPins == 0){
			_currentFrame.state = this.strike;
			_currentFrame.additionalCalculationNeeded = true;
			return this.strike;
		}else{
			return this.normal;
		}
	}

	public String checkSpare(Frame _currentFrame) {
		if(_currentFrame.leftPins == 0){
			_currentFrame.state = this.spare;
			_currentFrame.additionalCalculationNeeded = true;
			return this.spare;
		}else{
			return this.normal;
		}
	}

	public void checkIfAdditionalCalculationNeeded(int _frameId, Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList = _currentScoreboard.frameList;
		
		if(_frameId>0){
			Frame prevFrame = frameList.get(_frameId-1);
			//스페어는 그 다음 프레임이 뭐든 상관없이 한 프레임만 지나면 계산이 완료된다. 
			if(prevFrame.state == "spare"){
				prevFrame.additionalCalculationNeeded = false;
			}
		}
		
		if(_frameId>1){
			Frame currentFrame = frameList.get(_frameId);
			Frame prevFrame = frameList.get(_frameId-1);
			if(currentFrame.state == "strike"){
				Frame beforePrevFrame = frameList.get(_frameId-2);
				if(prevFrame.state == "strike" && beforePrevFrame.state == "strike"){
					beforePrevFrame.additionalCalculationNeeded = false;
				}
			}else{
				if(prevFrame.state == "strike"){
					prevFrame.additionalCalculationNeeded = false;
				}
			}
		}
		
		if(_frameId == 10){
			for(int i=_frameId ; i>=0; i--){
				Frame currentFrame = frameList.get(i);
				currentFrame.additionalCalculationNeeded = false;
			}
		}
	}
}
