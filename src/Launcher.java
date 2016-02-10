import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		
		//플레이 인원 수를 물어본다.
		int theNumberOfPlayers;
		Scanner in = new Scanner(System.in);
		
		System.out.println("플레이어 수를 말해주세요!");
		theNumberOfPlayers = in.nextInt();
		
		System.out.println(theNumberOfPlayers + "명으로 게임을 시작합니다.");
		
		//인원 수에 따라 점수판을 생성한다.
		GameManager manager = new GameManager();
		manager.createScoreboard(theNumberOfPlayers);
		
		//게임을 진행한다.
		manager.playGame();
	}

}
