package codeplus_math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4375_1 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String input;
		int n;
		long digit, res; // 자료형 고려하기! long 타입 안쓰면 overflow 일어남
		while ((input=br.readLine())!=null) {
			try {
				n = Integer.parseInt(input);
				digit = 1; res = 1;
				while (res % n != 0) {
					res = (res *10 + 1) % n; //출력 초과를 막기 위한 모듈러연산 필요
					digit++;
				}
				System.out.println(digit);
			} catch (Exception e) {
				break;
				
			}
		}
		br.close();
		
	}

}
