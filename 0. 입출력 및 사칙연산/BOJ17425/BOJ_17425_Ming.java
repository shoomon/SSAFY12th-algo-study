package Week2;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_17425_Ming {
	// 틀렸습니다.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			int num = sc.nextInt();
			
			System.out.println(g(num));
		}
	}
	
	static int f(int N) {
		int sum = 0;
		List<Integer> div = new ArrayList<>();

		for (int i=1; i*i<=N; i++) {
			if (N%i == 0) {
				div.add(i);
				if (i != N/i) {
					div.add(N/i);
				}
			}
		}
		for (int i=0; i<div.size(); i++) {
			sum += div.get(i);
		}
		return sum;
	}
	
	static int[] gN = new int[1000001];
	static int g(int N) {
		if (N==1) {
			return 1;
		}
		return gN[N] =  f(N) + gN[N-1];
	}
}
