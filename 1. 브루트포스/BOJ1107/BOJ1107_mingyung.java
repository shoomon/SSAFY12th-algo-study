// 못품!
import java.util.Scanner;

public class BOJ1107_mingyung {
	// 골드5_리모컨
	// 0~9까지 숫자와 +, -
	// 이동하려는 채널 N
	// 고장난 버튼 주어졌을 때
	// 채널 N으로 이동하기 위해 최소 몇 번 눌러야하는지 구하는 프로그램 작성
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 이동하려는 채널
		int ch = sc.nextInt();
		// 망가진 리모컨 버튼 배열
		int N = sc.nextInt();
		int[] f = new int[N];
		for (int i=0; i<N; i++) {
			f[i] = sc.nextInt();
		}
		
		// 그냥 +, - 누르기
		int pm = Math.abs(ch-100);
		
		// 가장 가까운 채널로 이동해 +, - 누르기
		int cl = 0;
		// 가장 가까운 채널로 어떻게 이동할까
		
		
		// 그냥 누른 것과 채널 눌러서 돌린 것 비교해서 출력
		System.out.println(Math.min(pm, cl));
	}
}