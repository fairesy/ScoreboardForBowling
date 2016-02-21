
public class FrameStateChecker {

	String strike = "strike";
	String spare = "spare";
	String normal = "normal";
	
	public String checkStrike(Frame _currentFrame) {
		if(_currentFrame.leftPins == 0){
			_currentFrame.state = this.strike;
			return this.strike;
		}else{
			return this.normal;
		}
	}

	public String checkSpare(Frame _currentFrame) {
		if(_currentFrame.leftPins == 0){
			_currentFrame.state = this.spare;
			return this.spare;
		}else{
			return this.normal;
		}
	}


}
