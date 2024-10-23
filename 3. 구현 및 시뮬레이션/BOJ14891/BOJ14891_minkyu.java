import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
톱니바퀴

자성을 가지는 톱니바퀴를 돌리는 과정을 거쳐 모든 톱니바퀴의
가장 위에 있는 값의 합을 구하시오.

메모리 : 11632 KB
시간 : 64 ms

*/

public class Main {
	static class Gear{
		int topPos;
		String info;
		char leftVal;
		char rightVal;
		public Gear(int topPos, String info) {
			super();
			this.topPos = topPos;
			this.info = info;
			setVals();
		}
		
		public void setVals() {
			this.leftVal = info.charAt((topPos + 6) % 8);
			this.rightVal = info.charAt((topPos + 2) % 8);
		}
		
		public void setTopPos(int dir) {
			topPos = (topPos + dir + 8) % 8;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Gear[] gears = new Gear[4];
		// 기어 정보 받아오기
		for (int i = 0; i < 4; i++) {
			String curLine = br.readLine();
			Gear gear = new Gear(0,curLine);
			gears[i] = gear;
		}
		
		int cmdCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < cmdCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int gearPos = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) * -1;
			
			// 현재 기어 왼쪽에 있는 기어들의 처리
			int beforeDir = dir;
			char beforeLeft = gears[gearPos].leftVal;
			for (int j = gearPos-1; j >= 0; j--) {
				// 현재 기어 왼쪽과 오른쪽이 서로 다른 값을 가진 경우
				if (gears[j].rightVal != beforeLeft) {
					int curDir = beforeDir * -1;
					beforeLeft = gears[j].leftVal;
					gears[j].setTopPos(curDir);
					gears[j].setVals();
					beforeDir = curDir;
				}else
					break;
			}
			
			// 현재 기어 오른쪽에 있는 기어들의 처리
			beforeDir = dir;
			char beforeRight = gears[gearPos].rightVal;
			for (int j = gearPos + 1; j < 4; j++) {
				// 현재 기어 오른쪽과 왼쪽이 서로 다른 값을 가진 경우
				if (gears[j].leftVal != beforeRight) {
					int curDir = beforeDir * -1;
					beforeRight = gears[j].rightVal;
					gears[j].setTopPos(curDir);
					gears[j].setVals();
					beforeDir = curDir;
				}else
					break;
			}
			
			// 현재 기어 회전처리
			gears[gearPos].setTopPos(dir);
			gears[gearPos].setVals();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 3; i >= 0 ; i--)
			sb.append(gears[i].info.charAt(gears[i].topPos));
		
		String curLine = sb.toString();
		System.out.println(Integer.parseInt(curLine,2));
	}
}