package code;
import java.util.*;
import java.io.*;

//24.09.12
//설계 시간: 10분
//구현 시간: 30분
//메모리: 36112 kb
//시간: 824 ms
public class BOJ14500 {
	static int N, M, map[][], answer;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				DFS(i,j,0,0);
				five(i,j);
			}
		}
		System.out.println(answer);
	}
	
	static void DFS(int curY, int curX, int depth, int sum) {
		if(depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		visited[curY][curX] = true;
		
		for(int i = 0; i < 4; i++) {
			int nY = curY+dy[i];
			int nX = curX+dx[i];
			
			if(check(nY,nX) && !visited[nY][nX]) {
				DFS(nY,nX,depth+1,sum+map[curY][curX]);
			}
		}
		visited[curY][curX] = false;
	}
	
	static void five(int centerY, int centerX) {
		int sum, max=0;
		for(int i = 0; i < 4; i++) {
			sum=map[centerY][centerX];
			for(int j = 0; j < 4; j++) {
				if(j == i) continue;
				if(!check(centerY+dy[j], centerX+dx[j])) continue;
				sum += map[centerY+dy[j]][centerX+dx[j]];
			}
			max = Math.max(max, sum);
		}
		answer = Math.max(answer, max);
	}
	
	static boolean check(int i, int j) {
		if(-1 < i && i < N && -1 < j && j < M) return true;
		return false;
	}

}

