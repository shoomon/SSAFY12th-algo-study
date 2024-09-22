package io;

import java.io.*;
import java.util.*;

public class BOJ_1929 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int end = (int) Math.sqrt(M);
		boolean nprime[] = new boolean[M+1];
		nprime[0] = true;
		nprime[1] = true;
		
		for (int i=2;i<end+1;i++) {
			if(nprime[i]) continue;
			
			for(int j=i*i;j<M+1;j=i+j) {
				nprime[j] = true;
			}
		}
		for(int i=N;i<M+1;i++) {
			if(!nprime[i]) System.out.println(i);
		}
		
	}
}
