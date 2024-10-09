import java.io.*;
import java.util.*;
//24.10.08
//설계 시간: 1분
//구현 시간: 30분
//메모리: 76876 kb
//시간: 376 ms
//조합, 3개의 벽만 세우면 됨 -> 재귀보다 반복문이 유리함.
public class Main {
	static int N,M, answer;
	static int[][] map, simulMap;
	
	static int[] dX = {0, 1, 0, -1};
	static int[] dY = {-1, 0, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		map = new int[N][M];
		simulMap = new int[N][M];
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//2차원 배열 한줄로 펼치기
		int len = N*M;
		for(int i = 0; i < len; i++) {
			for(int j = i+1; j < len; j++) {
				for(int k = j+1; k < len; k++) {
					if(map[i/M][i%M] == 0 && map[j/M][j%M] == 0 && map[k/M][k%M] == 0) {
						setMap(i,j,k);
						simulation();
					}
				}
			}
		}
		
		bw.write(answer+"\n");
		bw.close();
	}
	
	static void setMap(int a, int b, int c) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				simulMap[i][j] = map[i][j];
			}
		}
		simulMap[a/M][a%M] = 1;
		simulMap[b/M][b%M] = 1;
		simulMap[c/M][c%M] = 1;
	}
	
	static void simulation() {
		int count=0;
		Queue<int[]> q = new ArrayDeque<>();
//		boolean[][] visited = new boolean[N][M]; 맵에 직접 체크하므로 필요 없음.
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2) {
					q.offer(new int[] {i,j});
				}
			}
		}
		//BFS로 바이러스 퍼트리기
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int nY;
			int nX;
			
			for(int i = 0; i < 4; i++) {
				nY = cur[0]+dY[i];
				nX = cur[1]+dX[i];
				if(check(nY,nX) && simulMap[nY][nX] == 0) {
					simulMap[nY][nX] = 2;
					q.offer(new int[] {nY,nX});
				}
			}
		}
		//안전구역 수를 세고 정답 업데이트
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <M; j++) {
				if(simulMap[i][j] == 0) {
					count++;
				}
			}
		}
		answer = Math.max(answer, count);
	}
	
	static boolean check(int y, int x) {
		if(-1 < y && y < N && -1 < x && x < M) return true;
		return false;
	}
}