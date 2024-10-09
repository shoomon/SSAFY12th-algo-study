package io;

import java.io.*;
import java.util.*;

public class BOJ_17425 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int M = 1000001;
		long [] fx = new long[M];
		long [] gx = new long[M];
		
		Arrays.fill(fx, 1);
		
		/*
		 * 1*1 1*2 1*3 1*4 1*5 1*6 1*7 1*8 1*9 1*10 1*11 1*12
		 * 2*1 2*2 2*3 2*4 2*5 2*6
		 * 3*1 3*2 3*3 3*4
		 * 4*1 4*2 4*3
		 * 5*1 5*2
		 * 6*1 ...
		 */
			
		for (int i=2; i<M; i++) {
			for(int j=1;i*j<M;j++) {
				fx[i*j] += i;
			};
		}
		for (int i=1; i<M; i++) {
			gx[i] = gx[i-1] + fx[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t < T + 1; t++) {
			sb.append(gx[Integer.parseInt(br.readLine())]+"\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
