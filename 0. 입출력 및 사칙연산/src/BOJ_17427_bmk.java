import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long sum = 0;
		for (int i = N; i > 0; i--) {
			// 해당 숫자가 더해지는 개수 * 해당 숫자
			sum += (N / i) * i;
		}
		System.out.println(sum);
	}
}