
public class Frame {
	int firstRoll;
	int secondRoll;
	int totalScore;
	int leftPins;
	
	String state;
	boolean additionalCalculationNeeded;
	
	public Frame(){
		this.firstRoll = 0;
		this.secondRoll = 0;
		this.leftPins = 10;
		this.state = "normal";
		this.additionalCalculationNeeded = false;
	}

}
