import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<FireBall> fireballs = new ArrayList<>();
	static int boardSize;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		int fireballCnt = Integer.parseInt(st.nextToken());
		int commandCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < fireballCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			// 1행 1열부터 시작하기 때문에 1씩 빼준 값이 실제 인덱스와 일치한다.
			fireballs.add(new FireBall(row - 1, col - 1, speed, dir, mass));
		}
		
		// 이동 명령한 횟수만큼 이동해서 고려
		for (int i = 0; i < commandCnt; i++) {
			// 속도 만큼 해당 방향으로 이동
			move();
			// 합쳐진 파이어볼의 처리
			merge();
		}
		
		int massSum = 0;
		for (int i = 0; i < fireballs.size(); i++)
			massSum += fireballs.get(i).mass;
		System.out.println(massSum);
	}
	
	public static void debugCnt(String info) {
		int[][] board = new int[boardSize][boardSize];
		for (int i = 0; i < fireballs.size(); i++) {
			board[fireballs.get(i).r][fireballs.get(i).c]++;
		}
		
		System.out.println(info);
		System.out.println("cnt---------------------");
		// 찍어보기
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static void debugMass() {
		int[][] board = new int[boardSize][boardSize];
		for (int i = 0; i < fireballs.size(); i++) {
			board[fireballs.get(i).r][fireballs.get(i).c] += fireballs.get(i).mass;
		}
		
		System.out.println("mass---------------------");
		// 찍어보기
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static void move() {
		for (int i = fireballs.size() - 1; i >= 0; i--) {
			FireBall curFireBall = fireballs.get(i);
			// 넘어가도 다시 돌아오도록 설정
			int curR = curFireBall.r + curFireBall.speed * curFireBall.moveR;
			int curC = curFireBall.c + curFireBall.speed * curFireBall.moveC;
			while (curR < 0)
				curR += boardSize;
			while(curC < 0)
				curC += boardSize;
			curFireBall.r = curR % boardSize;
			curFireBall.c = curC % boardSize;
		}
	}
	
	public static void merge() {
		List<int[]> positions = new ArrayList<>();
		List<Integer> positionsCnt = new ArrayList<>();
		
		for (int i = 0; i < fireballs.size(); i++) {
			FireBall curFireBall = fireballs.get(i);
			// 중복되는 위치가 있으면 해당위치의 카운트 증가
			boolean isExist = false;
			for (int j = 0; j < positions.size(); j++) {
				int[] curPosition = positions.get(j);
				// 같은 위치에 중복되는 경우
				if (curPosition[0] == curFireBall.r && curPosition[1] == curFireBall.c) {
					positionsCnt.set(j, positionsCnt.get(j) + 1);
					isExist = true;
					break;
				}
			}
			// 중복되지 않는 경우 새로운 위치를 등록함
			if (!isExist) {
				int[] newPosition = {curFireBall.r, curFireBall.c};
				positions.add(newPosition);
				positionsCnt.add(1);
			}
		}
		
		for (int i = 0; i < positions.size(); i++) {
			// 겹친 파이어볼이 없는 경우 스킵
			if (positionsCnt.get(i) == 1)
				continue;
			
			int[] curPos = positions.get(i);
			boolean isFirst = true;
			int rest = 0;
			boolean isRestSame = true;
			int sumMass = 0;
			int sumSpeed = 0;
			for (int j = fireballs.size() - 1; j >= 0; j--) {
				FireBall curFireBall = fireballs.get(j);
				// 위치가 같은 경우 합쳐진 파이어볼 중 하나
				if (curFireBall.r == curPos[0] && curFireBall.c == curPos[1]) {
					sumMass += curFireBall.mass;
					sumSpeed += curFireBall.speed;
					// 방향이 모두 짝수, 홀수인 경우에는 0 2 4 6
					// 그 외의 경우에는 			 1 3 5 7
					if (isFirst) {
						rest = curFireBall.dir % 2;
						isFirst = false;
					} else {
						if (rest != curFireBall.dir % 2)
							isRestSame = false;
					}
					fireballs.remove(j);
				}
			}
			
			int indMass = sumMass / 5;
			int indSpeed = sumSpeed / positionsCnt.get(i);
			// 합친 값이 0이 아닌 경우에만 새로운 파이어볼을 추가해준다.
			if (indMass != 0) {
				int curDir = 0;
				if (!isRestSame)
					curDir = 1;
				// 파이어볼이 4갈래로 갈라짐
				for (int j = 0; j < 4; j++) {
					fireballs.add(new FireBall(curPos[0], curPos[1], indSpeed, curDir, indMass));
					curDir += 2;
				}
			}
		}
	}
}

class FireBall{
	int r;
	int c;
	int speed;
	int dir;
	int moveR;
	int moveC;
	int mass;
	
	FireBall(int r, int c, int speed, int dir, int mass){
		this.r = r;
		this.c = c;
		this.speed = speed;
		this.dir = dir;
		getDirection(dir);
		this.mass = mass;
	}
	
	// 방향 배분하기
	private void getDirection(int dir) {
		switch(dir) {
		case 0:
			moveR = -1;
			moveC = 0;
			break;
		case 1:
			moveR = -1;
			moveC = 1;
			break;
		case 2:
			moveR = 0;
			moveC = 1;
			break;
		case 3:
			moveR = 1;
			moveC = 1;
			break;
		case 4:
			moveR = 1;
			moveC = 0;
			break;
		case 5:
			moveR = 1;
			moveC = -1;
			break;
		case 6:
			moveR = 0;
			moveC = -1;
			break;
		case 7:
			moveR = -1;
			moveC = -1;
			break;
		}
	}
}