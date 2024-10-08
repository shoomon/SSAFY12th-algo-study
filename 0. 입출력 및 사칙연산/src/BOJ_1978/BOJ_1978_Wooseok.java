import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1978_Wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        int[] K = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            K[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            int check = 0;
            for (int j = 1; j <= K[i]; j++) {
                if (K[i] % j == 0) {
                    check++;
                }
            }
            if (check == 2) {
                count++;
            }
        }


        System.out.println(count);
    }
}

