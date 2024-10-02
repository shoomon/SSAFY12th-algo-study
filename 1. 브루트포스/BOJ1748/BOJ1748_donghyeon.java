package postorogicalqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int k, sum;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int digit = 0;
		while(k != n) {
			int i = 1;
			int h = (int) Math.pow(n, i);
			k = n % h;
			
			
			i++;
		}
		//k는 n의 자릿수
		while(true) {
			//기저조건
			if(k == n) {
				for(int i = 1; i<= n; i++) {
					sum += i; 
				}
			}
			
		}
		
}
}
