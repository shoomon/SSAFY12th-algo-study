package code;
import java.util.*;
import java.io.*;
//24.09.10
//설계 시간: 20분
//구현 시간: 10분
//유효하지 않은 표현 판단 어려움 -> M*N 최대 16억
//메모리: 17796 kb
//시간: 296 ms

public class BOJ6064 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//총 연도 M과 N의 최소공배수
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			long M = Integer.parseInt(st.nextToken());
			long N = Integer.parseInt(st.nextToken());
			long x = Integer.parseInt(st.nextToken());
			long y = Integer.parseInt(st.nextToken());
			
			while(x < M*N && y < M*N && x != y) {
				if(x < y) {
					x += M;
				}else if(x > y) {
					y += N;
				}
			}
			
			if(x == y) {
				System.out.println(x);
			}else {
				System.out.println(-1);
			}
		}
		
	}

}

