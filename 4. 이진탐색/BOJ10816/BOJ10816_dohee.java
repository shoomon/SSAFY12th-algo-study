package oct_3th;

import java.io.*;
import java.util.*;

// 28m - 외우려는 템플릿 확인함
// 182404KB	1088ms	

public class BOJ10816_dohee {
	static int cards[], N, M;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		N = Integer.parseInt(br.readLine());
		cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int find = Integer.parseInt(st.nextToken());
			sb.append(upperBound(find) - lowerBound(find)).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static int lowerBound(int find) {
		int lo = -1;
		int hi = N;
		
		while(lo+1<hi) {
			int mid = (lo + hi) / 2;
			if (!(cards[mid] >= find)) lo = mid;
			else hi = mid;
		}
		return hi;
	}
	
	public static int upperBound(int find) {
		int lo = -1;
		int hi = N;
		
		while(lo+1<hi) {
			int mid = (lo + hi) / 2;
			if (!(cards[mid] > find)) lo = mid;
			else hi = mid;
		}
		return hi;
	}
}
