
public class FrameStateChecker {

	String strike = "strike";
	String spare = "spare";
	String normal = "normal";
	
	public String checkFirst(Frame _currentFrame) {
		if(_currentFrame.leftPins == 0){
			return this.strike;
		}else{
			return this.normal;
		}
	}

	public String checkSecond(Frame _currentFrame) {
		if(_currentFrame.leftPins == 0){
			return this.spare;
		}else{
			return this.normal;
		}
	}


}
