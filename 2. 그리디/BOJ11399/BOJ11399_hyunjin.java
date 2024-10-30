package greedy;

import java.util.Arrays;
import java.util.Scanner;

// BOJ11399_ATM
// 메모리 : 16440KB
// 시간 : 128ms
public class BOJ11399_hyunjin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 배열 오름차순 정렬
		Arrays.sort(arr); 

		int ans = 0; // 정답
		int preTime = 0;
		for (int i = 0; i < N; i++) {
			preTime += arr[i]; // 이번 사람이 걸린 시간
			ans += preTime; // 모든 사람이 걸린 시간 합 
		}
		
		System.out.println(ans);
	}

}
