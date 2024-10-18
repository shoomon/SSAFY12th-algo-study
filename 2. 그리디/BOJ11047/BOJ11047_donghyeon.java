package aps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//반복문보다 빠름 
public class Main2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int arr [] = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int ans = 0; //답
		int middle = k; //나머지
		int i = n-1;
		while(i >= 0) {
			if(middle == 0)break;
			//뒤에서부터, 나누어지면, 거기서부터 시작
			if(middle / arr[i] != 0) {
				ans += middle / arr[i];
				middle %= arr[i];
			}
			i--;
    }
		System.out.println(ans);
	}
}
