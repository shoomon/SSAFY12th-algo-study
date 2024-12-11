import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		if (M == 1)
			M++;
		main : for (int i = M; i <= N; i++) {
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0)
					continue main;
			}
			System.out.println(i);
		}
	}
}
