
public class Frame {
	int firstRoll;
	int secondRoll;
	
	public Frame(){
		this.firstRoll = 0;
		this.secondRoll = 0;
	}

	public int getTotal() {
		int total = this.firstRoll + this.secondRoll;
		return total;
	}
}
