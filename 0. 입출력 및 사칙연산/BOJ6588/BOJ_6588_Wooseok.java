import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
public class BOJ_6588_Wooseok {
    static final int MAX = 1000001;

    static boolean[] isPrime = new boolean[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 2; i < MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        while (N != 0) {
            for (int i = 3; i <= N / 2; i += 2) {
                if (isPrime[i] && isPrime[N - i]) {
                    System.out.println(N + " = " + i + " + " + (N - i));
                    break;
                }
            }

            N = Integer.parseInt(br.readLine());
        }
    }
}
