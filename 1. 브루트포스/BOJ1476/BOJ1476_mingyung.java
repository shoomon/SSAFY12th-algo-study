
import java.util.Scanner;

public class BOJ1476_mingyung {
	// 실버5_날짜 계산_맞았습니다!!
	// 1<=E<=15	| 1<=S<=28 | 1<=M<=19
	// 1년이 1 1 1 (E S M)이고, 16년 뒤면 1, 16, 16이 됨 (최댓값)
	// E, S, M이 주어졌을 때 우리가 알고 있는 연도로 몇 년인지 구하기
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력받기
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		// 세 숫자가 같아질 때까지 반복
		while (!(E==S && S==M && M==E)) {
			// 최소 숫자에 각 최댓값 더하기
			int min = Math.min(S, M);
			min = Math.min(E, min);
			if (min == E) {
				E += 15;
			}
			else if (min == S) {
				S += 28;
			}
			else if (min == M) {
				M += 19;
			}
		}
		
		System.out.println(E);
	}
}