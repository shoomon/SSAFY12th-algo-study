package code;
import java.util.*;
import java.io.*;

//24.09.11
//설계 시간:
//구현 시간:
public class BOJ14500 {
	static int N, M, map[][], maxVal[][];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		maxVal = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	static void DFS(int curY, int curX) {
		
	}
	
	static void five(int centerY, int centerX) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(j == i) continue;
				if(!check(centerY+dy[j], centerX+dx[j])) break;
			}
		}
	}
	
	static boolean check(int i, int j) {
		if(-1 < i && i < N && -1 < j && j < M) return false;
		return true;
	}

}

