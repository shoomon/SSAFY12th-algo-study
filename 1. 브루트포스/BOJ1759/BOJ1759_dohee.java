package recursion;

import java.io.*;
import java.util.*;

/*
13096KB	68ms
43m
 */
public class BOJ_1759 {
	
	static int L, C;
	static char[] pw;
	static boolean[] selected;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		L = Integer.parseInt(st[0]);
		C = Integer.parseInt(st[1]);
	
		
		pw = new char[C];
		selected = new boolean[C];

		String line = br.readLine();
		for(int i=0; i<C;i++) {
			pw[i] = line.charAt(2*i);
		}
		
		Arrays.sort(pw);
		// 조합
		combi(0,0,0);
		
		System.out.println(sb.toString());
	}
	
	static void combi(int cnt, int start, int vCnt) {
		// 자음 모음 개수 이상인 경우에만 sb에 넣음
		if(cnt==L && vCnt>=1 && (L-vCnt)>=2) {
			for(int i=0; i<C;i++) {
				if(selected[i]) sb.append(pw[i]);
			}
			sb.append("\n");
			return;
		}
		
		
		for (int i=start; i<C; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			combi(cnt+1, i+1, isVowel(i) ? vCnt+1 : vCnt);
			selected[i] = false;
		}
	}
	
	// 모음 여부 체크
	static boolean isVowel(int idx) {
		char c = pw[idx];
		if( c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
			return true;
		}
		return false;
	}
}
