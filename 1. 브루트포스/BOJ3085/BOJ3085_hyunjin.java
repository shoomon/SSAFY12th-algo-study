import java.util.Scanner;

// BOJ3085 사탕게임
public class BOJ3085_hyunjin {
	static int N;
	static int max = -1;
	static char[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		// 앞 에서 하나씩 찾으면서 두 값이 다르면 값 변경하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (arr[i][j] != arr[i][j + 1]) {
					char tmp = arr[i][j];
					arr[i][j] = arr[i][j + 1];
					arr[i][j + 1] = tmp;
				}
				max = Math.max(max, sum());
			}
		}
		System.out.println(max);

//		// TEST
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		
	} // main

	static int sum() {
		int cnt;
		// 가로 찾기
		for (int r = 0; r < N; r++) {
			cnt = 1;
			for (int c = 0; c < N - 1; c++) {
				if (arr[r][c] != arr[r][c + 1]) {
					// 값이 같지 않으면 cnt 1로 초기화
					cnt = 1;
					continue;
				} else {
					// 같으면 cnt++ 해주고 max 값 갱신
					cnt++;
					max = Math.max(max, cnt);
				}
			}
			max = Math.max(max, cnt);
		}

		// 세로 찾기
		for (int c = 0; c < N; c++) {
			cnt = 1;
			for (int r = 0; r < N - 1; r++) {
				if (arr[c][r] != arr[c][r + 1]) {
					cnt = 1;
					continue;
				} else {
					cnt++;
					max = Math.max(max, cnt);
				}
			}
			max = Math.max(max, cnt);
		}
		return max;
	}
}
