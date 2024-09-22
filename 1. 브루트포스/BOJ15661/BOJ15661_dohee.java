package recursion;
/*
36m

 */
import java.io.*;
import java.util.*;

public class BOJ_15661 {
	static int N, map[][];
	static StringTokenizer st;
	static int minDiff = Integer.MAX_VALUE;
	public static void main (String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		findStart(0,new boolean[N]);
		
		System.out.println(minDiff);
	}
	
	static void findStart(int cnt, boolean[] selected) {
		if(cnt == N) {
			int startCapa=0;
			int linkCapa=0;
			for (int i = 0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(selected[i] && selected[j]) {
						startCapa +=map[i][j];
					} else if (!selected[i] && !selected[j]) {
						linkCapa += map[i][j];
					}
				}
			}
			int diff = Math.abs(startCapa - linkCapa);
			minDiff = Math.min(minDiff, diff);
			
			return;
			
		}
		
		selected[cnt] = true;
		findStart(cnt+1, selected);
		selected[cnt] = false;
		findStart(cnt+1, selected);
	}
	

}
