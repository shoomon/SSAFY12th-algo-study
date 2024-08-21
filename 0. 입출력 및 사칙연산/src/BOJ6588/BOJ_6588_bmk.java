import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static final int maxVal = 1000000;
	public static void main(String[] args) throws IOException {
		
		Boolean[] odds = new Boolean[maxVal + 1];
		
		for (int i = 2; i <= maxVal; i++)
			odds[i] = true;

		for (int i = 2; i <= maxVal; i++) {
			for (int j = 2 * i; j <= maxVal; j += i) {
				if (!odds[i])
					continue;
				odds[i] = false;
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			boolean isOk = false;
			for (int i = 2; i <= N / 2; i++) {
				if (odds[i] && odds[N - i]) {
					System.out.println(N + " = " + i + " + " + (N-1));
					isOk = true;
					break;
				}
			}

			if (!isOk)
				System.out.println("Goldbach's conjecture is wrong.");
		}
	}
}
