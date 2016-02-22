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

	public void checkPrintableFrameTotals(int _frameId, Scoreboard _currentScoreboard) {
		ArrayList<Frame> frameList = _currentScoreboard.frameList;

		//TODO 첫번째 프레임이 스페어인 경우 처리 안됨.
		if(_frameId>1){
			for(int i=_frameId ; i>=0; i--){
				Frame currentFrame = frameList.get(i);
				if(i==_frameId){
					Frame beforePrevFrame = frameList.get(i-2);
					Frame prevFrame = frameList.get(i-1);
					
					//현재 프레임이 스트라이크만 아니라면(연속스트라이크가 아니면) 지난번에 있는 스트라이크를 위한 보너스롤은 이미 다 플레이한 상태이다. 
					if(currentFrame.state != "strike"){
						if(prevFrame.state == "strike"){
							prevFrame.additionalCalculationNeeded = false;
						}
					}
					//현재 프레임이 어떻든 상관없이 지난 연속스트라이크는 계산이 끝난 상태가 되었다. 
					if(beforePrevFrame.state == "strike"){
						if(prevFrame.state == "strike"){
							beforePrevFrame.additionalCalculationNeeded = false;
						}
					}
					if(prevFrame.state == "spare"){
						prevFrame.additionalCalculationNeeded = false;
					}					
				}else{
					currentFrame.additionalCalculationNeeded = false;
				}
			}
		}
	}
}
