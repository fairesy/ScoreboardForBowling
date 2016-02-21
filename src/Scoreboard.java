import java.util.ArrayList;

public class Scoreboard {

	ArrayList<Frame> frameList= new ArrayList<Frame>(); 
	int playerTotal;

	public Scoreboard(){
		for(int i=0; i<10; i++){
			Frame frame = new Frame();
			this.frameList.add(frame);
		}
		this.playerTotal = 0;
	}
}
