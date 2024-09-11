package 아이고아이고;

import java.util.Scanner;

public class _1748_수이어쓰기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long cnt = 0;
		for (int i = 1; i <= N; i++) {
			if(i < 10) cnt += 1;
			else if(10 <= i && i < 100) cnt += 2;
			else if(100 <= i && i < 1000) cnt += 3;
			else if(1000 <= i && i < 10000) cnt += 4;
			else if(10000 <= i && i < 100000) cnt += 5;
			else if(100000 <= i && i < 1000000) cnt +=6;
			else if(1000000 <= i && i < 10000000) cnt += 7;
			else if(10000000 <= i && i < 100000000) cnt += 8;
			else cnt += 9;
		}

		System.out.println(cnt);
		sc.close();
	} // end of main
} // end of class
