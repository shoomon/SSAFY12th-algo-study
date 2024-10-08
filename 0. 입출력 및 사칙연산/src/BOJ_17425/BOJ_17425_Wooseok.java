import java.io.*;

public class BOJ_17425_Wooseok {
    static final int MAX = 1000000;
    static long[] f = new long[MAX + 1];
    static long[] g = new long[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        // 모든 자연수 y의 약수의 합 f(y) 계산
        for (int i = 1; i <= MAX; i++) {
            for (int j = i; j <= MAX; j += i) {
                f[j] += i;
            }
        }

        // g(N) 계산
        for (int i = 1; i <= MAX; i++) {
            g[i] = g[i - 1] + f[i];
        }

        // 각 테스트 케이스 처리
        for (int t = 0; t < TC; t++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(g[N]);
        }
    }
}