import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
차윤이의 RC 카

RC카를 명령에 따라 이동시켰을 때 목적지에 도착하는지 확인

메모리 : 16080 KB
시간 : 104 ms

*/

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][N];
			int[] curPos = new int[2];
			int[] lastPos = new int[2];
			for (int i = 0; i < N; i++) {
				String curLine = br.readLine();
				for (int j = 0; j < N; j++) {
					char curChar =curLine.charAt(j); 
					map[i][j] = curChar;
					if (curChar == 'X') {
						curPos[0] = i;
						curPos[1] = j;
					}else if (curChar == 'Y') {
						lastPos[0] = i;
						lastPos[1] = j;
					}
				}
			}
			
			int[] dr = {-1,0,1,0};
			int[] dc = {0,1,0,-1};
			
			int cmdCnt = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder("#").append(tc);
			for (int i = 0; i < cmdCnt; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int testCnt = Integer.parseInt(st.nextToken());
				int[] newPos = new int[] {curPos[0],curPos[1]};
				int dir = 0;
				String curCmdInfo = st.nextToken();
				for (int j = 0; j < testCnt; j++) {
					char curCmd = curCmdInfo.charAt(j);
					switch(curCmd) {
					case 'A':
						int newr = newPos[0] + dr[dir];
						int newc = newPos[1] + dc[dir];
						if (0<=newr&&newr<N&&0<=newc&&newc<N && map[newr][newc] != 'T') {
							newPos[0] = newr;
							newPos[1] = newc;
						}
						break;
					case 'L':
						dir = (dir + 3) % 4;
						break;
					case 'R':
						dir = (dir + 1) % 4;
						break;
					}
				}
				
				if (newPos[0] == lastPos[0] && newPos[1] == lastPos[1])
					sb.append(" ").append(1);
				else
					sb.append(" ").append(0);
			}
			
			System.out.println(sb);
		}
	}
}