// BOJ1476 날짜 계산

import java.util.Scanner;

public class BOJ1476_hyunjin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();

		int e = 1;
		int s = 1;
		int m = 1;
		int year = 1;
		
		// 둘 값이 같을 때 stop!
		while (!(e == E && s == S && m == M)) {
			e++;
			s++;
			m++;
			year++;

			if (e > 15) {
				e = 1;
			}
			
			if (s > 28) {
				s = 1;
			}
			
			if (m > 19) {
				m = 1;
			}
		}
		System.out.println(year);
	}
}
