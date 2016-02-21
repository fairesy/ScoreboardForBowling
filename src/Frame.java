
public class Frame {
	int firstRoll;
	int secondRoll;
	int totalScore;
	int leftPins;
	
	public Frame(){
		this.firstRoll = 0;
		this.secondRoll = 0;
		this.leftPins = 10;
	}

	public int getTotal() {
		this.totalScore = this.firstRoll + this.secondRoll;
		return totalScore;
	}
}
