import java.util.Scanner;

public class BOJ6064_mingyung {
	// S1_카잉달력 _ 맞았습니다!!
	// 최대 M, N => 멸망하는 년도 M, N의 최소공배수
	// x, y 주어지면 몇 번째 해인지 구하기
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력받기
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			// tc별 입력받기 완료
			
			// 최대공약수 구해 최소공배수 수하기
			int gcd = getGCD(M, N);
			int lcm = M*N/gcd;
			
			while (true) {
				// x==y가 되면 그 값이 닶
				if (x==y) {
					break;
				}
				// x와 y가 최소공배수보다 크면 멸망하는 해를 지난 것임
				if (x>=lcm && y>=lcm) {
					x=-1;
					break;
				}
				// 작은 값에 최대값 더해주기
				int min = Math.min(x, y);
				if (min==x) {
					x+=M;
				} else {
					y+=N;
				}
			}
			
			System.out.println(x);
		} // tc
	} // main
	
	static int getGCD(int num1, int num2) {
		if (num1%num2 == 0) {
			return num2;
		}
		return getGCD(num2, num1%num2);
	}
	// 이거 해봤는데 답도 -1만 나오고 무한루프 돌아요....
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
//		for (int tc=1; tc<=T; tc++) {
//			int M = sc.nextInt();
//			int N = sc.nextInt();
//			int x = sc.nextInt();
//			int y = sc.nextInt();
//			int ans =0 ;
//			while (true) {
//				x = x%M + 1;
//				y = y%N + 1;
//				ans++;
//				
//				if (x==y) {
//					break;
//				}
//				
//				if (x==M && y==N) {
//					ans = -1;
//					break;
//				}
//			}
//			System.out.println(ans);
//		} // tc
//	} // main
}