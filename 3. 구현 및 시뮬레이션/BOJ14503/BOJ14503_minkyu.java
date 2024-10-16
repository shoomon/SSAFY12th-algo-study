import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
로봇 청소기

4방으로 이동하는 로봇청소기가 칸을 청소 하거나, 뒤로 움직이거나, 현재 칸의 주변 4칸 중 청소가 되지 않은 칸이 있으면 90도 회전하고 한칸 앞으로간다.
청소를 진행한 칸의 수를 출력하시오.


메모리 : 11984 KB
시간 : 68 ms

*/

public class Main {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int rSize;
	static int cSize;
	static int[] curPos = new int[2];
	static int curDir;
	static int[][] map = {};
	static boolean[][] cleaned = {};
	static int cleanCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		rSize = Integer.parseInt(st.nextToken());
		cSize = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		curPos[0] = Integer.parseInt(st.nextToken());
		curPos[1] = Integer.parseInt(st.nextToken());
		curDir = Integer.parseInt(st.nextToken());
		map = new int[rSize][cSize];
		cleaned = new boolean[rSize][cSize];
		for (int i = 0; i < rSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < cSize; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		cleanCnt = 0;
		while(true) {
			// 현재 위치 청소를 진행하지 않았으면 청소 진행
			if (!cleaned[curPos[0]][curPos[1]]) {
				cleaned[curPos[0]][curPos[1]] = true;
				cleanCnt++;
			}
			boolean isCapable = false;
			// 4방 탐색하면서 탐색진행
			for (int i = 0; i < 4; i++) {
				int curR = curPos[0] + dr[i];
				int curC = curPos[1] + dc[i];
				if (0<=curR&&curR<rSize&&0<=curC&&curC<cSize) {
					if (map[curR][curC] == 0 && !cleaned[curR][curC])
						isCapable = true;
				}
			}
			
			// 4방 탐색해서 가능한 경우가 발견된 경우
			if (isCapable) {
				// 방향 전환
				curDir = (curDir + 3) % 4;
				// 해당 방향으로 이동
				int nextR = curPos[0] + dr[curDir];
				int nextC = curPos[1] + dc[curDir];
				if (0<=nextR&&nextR<rSize&&0<=nextC&&nextC<cSize) {
					if (map[nextR][nextC] == 0 && !cleaned[nextR][nextC]) {
						curPos[0] = nextR;
						curPos[1] = nextC;
					}
				}
			// 4방 탐색 결과 갈 수 있는 곳이 없는 경우
			}else {
				// 뒤로 한칸 이동
				int backR = curPos[0] - dr[curDir];
				int backC = curPos[1] - dc[curDir];
				if (0<=backR&&backR<rSize&&0<=backC&&backC<cSize&&map[backR][backC]!=1) {
					curPos[0] = backR;
					curPos[1] = backC;
				}else
					break;
			}
		}
		System.out.println(cleanCnt);
	}
	
	
}