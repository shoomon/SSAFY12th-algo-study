// 시간초과
package 아이고아이고;

import java.util.Scanner;

public class _1748_수이어쓰기1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String st = "";
		for (int i = 1; i <= N; i++) {
			st += i;
		}
		int cnt = 0;
		for (int i = 0; i < st.length(); i++) {
			cnt++;
		}
		
		
		System.out.println(cnt);
		sc.close();
	} // end of main
} // end of class
