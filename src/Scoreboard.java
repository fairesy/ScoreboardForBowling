import java.util.ArrayList;

/* Scoreboard
 * 각 플레이어의 점수판scoreboard는 10개의 프레임과 보너스 1프레임으로 이루어진다.
 * 보너스프레임(11번째프레임)은 10번째 프레임이 Strike/Spare 상태일 경우에만 사용된다.  
 * */

public class Scoreboard {

	ArrayList<Frame> frameList= new ArrayList<Frame>(); 
	int playerTotal;

	public Scoreboard(){
		for(int i=0; i<11; i++){
			Frame frame = new Frame();
			this.frameList.add(frame);
		}
		this.playerTotal = 0;
	}
}
