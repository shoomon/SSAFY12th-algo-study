package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1037 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] arr = new int[n];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(arr[i], min);
			max = Math.max(arr[i], max);
		}
		System.out.println(min * max);
	}
}
