package code;
import java.util.*;
import java.io.*;
//24.09.10
//설계 시간: 30분
//구현 시간: 10분
//메모리: 14108 kb
//시간: 104 ms
public class BOJ1748 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		int len = (N+"").length();
		//+= 자리 수++*수 개수
		
		for(int i = 1; i < len; i++) {
			answer += i*((int)Math.pow(10, i)-(int)Math.pow(10,i-1));
		}
		
		answer += len*(N-(int)Math.pow(10, len-1)+1);
		System.out.println(answer);
	}

}
