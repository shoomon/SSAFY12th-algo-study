package codingTest;
import java.io.*;
import java.util.*;

public class SWEA22654 {
	static int[] dr = {0,1,0,-1}; //우하좌상
	static int[] dc = {1,0,-1,0};
	static int N;
	static char map[][];
	static class loc{
		int r;
		int c;
		int dir;
		
		public loc(int r, int c, int dir){
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
//		'G' : RC카가 이동 가능한 땅
//		'T' : RC카가 이동이 불가능한 나무
//		'X' : 현재 RC카의 위치
//		'Y' : RC카를 이동 시키고자 하는 위치
		
//		'A' : 앞으로 이동 - 나무가 있는 곳이나 필드를 벗어나는 경우에는 아무 일도 일어나지 않는다.
//		'L' : 현재 바라보고 있는 방향에서 왼쪽으로 90도 회전
//		'R' : 현재 바라보고 있는 방향에서 오른쪽으로 90도 회전		
		
		// 위를 바라보고 시작함
		
		for (int tc = 1; tc<=T; tc++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			loc start = null;
					
			for (int i=0; i<N; i++) {
				String input = br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j] = input.charAt(j);
					
					if (map[i][j] == 'X') start = new loc(i,j,3);
				}
			}
			
			int Q = Integer.parseInt(br.readLine());
			
			sb.append("#"+tc).append(" ");
			for (int i=0; i<Q; i++) {
				String[] input = br.readLine().split(" ");
				int res = move(start, input);
				
				sb.append(res+" ");
			}
			
			
			System.out.println(sb.toString());
		}
	}
	
	public static int move(loc start, String[] cmds) {
		int C = Integer.parseInt(cmds[0]);
		
		loc now = start;
		
		for (int j = 0; j < C; j++) {
			char cmd = cmds[1].charAt(j);
			switch (cmd) {
			case 'A':
				int nr = now.r + dr[now.dir];
				int nc = now.c + dc[now.dir];
				
				if (nr<0||nr>=N||nc<0||nc>=N||map[nr][nc] == 'T') continue;
				
				now = new loc(nr,nc,now.dir);
				
				break;
			case 'L':
				now = new loc(now.r,now.c,(now.dir-1 + 4) % 4);
				break;
			case 'R':
				now = new loc(now.r,now.c,(now.dir+1) % 4);
				break;
			}
//			System.out.println(now.r+" "+now.c);
		}
//		System.out.println("=============");
		if(map[now.r][now.c] == 'Y') return 1;
		return 0;
	}
	
	
}
