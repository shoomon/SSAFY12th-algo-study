import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BOJ_1929_Wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        long M = Long.parseLong(st.nextToken());
        long N = Long.parseLong(st.nextToken());

        for (long i = M; i <= N; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }


    }

    static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true; // 2는 소수
        }
        if (number % 2 == 0) {
            return false; // 2를 제외한 짝수는 소수가 아님
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
