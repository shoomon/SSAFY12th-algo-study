import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
연구소

바이러스가 유출되었을 때, 3개의 벽을 세워 바이러스가 퍼져나가는 것을 막고
확보할 수 있는 안전 영역의 최대 크기를 구하시오

메모리 : 88444 KB
시간 : 372 ms

*/

public class Main {
	static int rSize;
	static int cSize;
	static int[][] lab = {};
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static int wallCnt;
	
	static int maxVal;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rSize = Integer.parseInt(st.nextToken());
		cSize = Integer.parseInt(st.nextToken());
		lab = new int[rSize][cSize];
		for (int i = 0; i < rSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < cSize; j++) {
				int curVal = Integer.parseInt(st.nextToken());
				lab[i][j] = curVal;
				if (curVal == 1)
					wallCnt++;
			}
		}
		
		comb(new int[] {0,0},0);
		
		System.out.println(maxVal);
	}
	public static void comb(int[] pos, int cnt) {
		if (pos[1] == cSize) {
			pos[0]++;
			pos[1] = 0;
		}
		
		// 벽 3개 다 고른 경우
		if (cnt == 3) {
			// 세균을 통한 bfs 진행
			int germCnt = 0;
			boolean[][] visited = new boolean[rSize][cSize];
			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					if (lab[i][j] == 2) {
						q.offer(new int[] {i,j});
						visited[i][j] = true;
						germCnt++;
					}
				}
			}
			
			while(!q.isEmpty()) {
				int[] tmp = q.poll();
				for (int i = 0; i < 4; i++) {
					int r = tmp[0] + dr[i];
					int c = tmp[1] + dc[i];
					if (0<=r&&r<rSize&&0<=c&&c<cSize&&!visited[r][c]&&lab[r][c]==0) {
						q.offer(new int[] {r,c});
						visited[r][c] = true;
						germCnt++;
					}
				}
			}
			
			// 안전영역계산
			int safeCnt = rSize * cSize - germCnt - (wallCnt + cnt);
			if (maxVal < safeCnt)
				maxVal = safeCnt;

			return;
		}
		
		if (pos[0] == rSize)
			return;
		
		// 빈 공간인 경우만 벽을 추가할 수 있음 (추가 안하는 경우도 발생 가능함)
		if (lab[pos[0]][pos[1]] == 0) {
			comb(new int[]{pos[0],pos[1]+1},cnt);
			lab[pos[0]][pos[1]] = 1;
			comb(new int[]{pos[0],pos[1]+1},cnt + 1);
			lab[pos[0]][pos[1]] = 0;
		}else
			comb(new int[]{pos[0],pos[1]+1},cnt);
	}
}