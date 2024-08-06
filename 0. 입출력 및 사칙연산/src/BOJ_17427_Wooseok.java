import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17427_Wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] f = new long[N + 1];
        long g = 0;

        // 모든 자연수 y의 약수의 합 f(y) 계산
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                f[j] += i;
            }
        }

        // g(N) 계산
        for (int i = 1; i <= N; i++) {
            g += f[i];
        }

        System.out.println(g);
    }
}
