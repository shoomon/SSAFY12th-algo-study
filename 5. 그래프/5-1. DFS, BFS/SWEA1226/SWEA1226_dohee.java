package codingTest;
import java.io.*;
import java.util.*;

// 24m
// 16,080 kb   106 ms

public class SWEA1226 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dr = {0,0,1,-1};
		int[] dc = {1,-1,0,0};
		
		for(int tc = 1; tc <= 10; tc++) {
			int map[][] = new int[16][16];
			boolean visited[][] = new boolean[16][16];
			
			br.readLine();
			int res = 0;
			int[] start = new int[2], end = new int[2];
			
			for (int i=0; i<16; i++) {
				String input = br.readLine();
				for (int j=0; j<16; j++) {
					map[i][j] = input.charAt(j) -'0';
					
					if(map[i][j] == 2) start = new int[] {i,j};
					if(map[i][j] == 3) end = new int[] {i,j};
				}
			}
			
			Queue <int[]> q = new ArrayDeque<int[]>();
			visited[start[0]][start[1]] = true;
			q.add(start);

			outer:
			while(!q.isEmpty()) {
				int[] now = q.poll();
				for (int i=0; i<4; i++) {
					int nr = now[0] + dr[i];
					int nc = now[1] + dc[i];
					
					if (nr<0||nr>=16||nc<0||nc>=16||map[nr][nc]==1||visited[nr][nc]) continue;
					if (nr == end[0] && nc == end[1]) {
						res = 1;
						break outer;
					}
					
					visited[nr][nc] = true;
					q.add(new int[] {nr,nc});
				}
			}
			System.out.println("#"+tc+" "+res);
		}
		
	}
}
