
import java.util.Scanner;

//백준_4375_1
public class BOJ4375 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 입력이 있을 때까지 계속 반복합니다.
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = 0;

            // i가 0부터 시작하여 무한 반복합니다.
            for (int i = 0 ;; i++) {
                k = (k * 10 + 1) % n;

                // k가 0이 되면 현재 i 값에 1을 더해 출력하고 반복을 멈춥니다.
                if (k == 0) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
        sc.close();
    }
}
