import java.io.*;

public class Main {	//1,2,3 더하기

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(countWays(n) + "\n");
        }
        bw.flush();
    }

    // n을 1, 2, 3의 합으로 표현할 수 있는 경우의 수를 계산하는 함수
    private static int countWays(int n) {
        if (n == 0) return 1; // 아무 것도 더하지 않은 경우 1가지
        if (n < 0) return 0; // 음수가 되는 경우는 불가능하므로 0 반환
        
        // 1, 2, 3을 각각 사용하여 n을 만드는 경우의 수를 재귀적으로 더함
        return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
    }
}