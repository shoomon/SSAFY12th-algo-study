
import java.util.Arrays;
import java.util.Scanner;

//백준_1037_약수
public class BOJ_1037_Wooseok {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int result = 0;
        if (N % 2 == 0) {
            result = arr[0] * arr[N - 1];
        } else if (N == 1) {
            result = (int) Math.pow((arr[0]), 2);
        } else {
            result = (int) Math.pow((arr[N / 2]), 2);
        }

        System.out.println(result);

    }
}
