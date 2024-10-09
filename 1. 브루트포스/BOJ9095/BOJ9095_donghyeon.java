package postorogicalqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1 {
	static int n, cnt, sum;
	static int [] target = {1, 2, 3};
	static int result[] = new int[n];
	static StringBuilder sb = new StringBuilder();
public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			perm(0);
			sb.append(cnt);
			
		}
		
}
	static void perm(int idx) {
		if(sum == n) {
			
		}
		
	}
}
