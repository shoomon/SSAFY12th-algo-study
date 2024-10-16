package postorogicalqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static int n,m,q,w;
	static int arr[][];
	static int dc[] = {-1, 0, 1, 0};//상우하좌
	static int dr[] = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int [n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		int max = Integer.MIN_VALUE;
		//최대값 찾기 최대값에서 가는게 낫다고 판단 
		
		//근데 최대값이 여러개 일 때가 있다
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] > max) {
					max = arr[i][j]; 
				}
				
			}
		}
		
		
		
		
}
}
