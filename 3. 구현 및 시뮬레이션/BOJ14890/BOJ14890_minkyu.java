import java.util.*;
import java.io.*;

/*
경사로

지도에서 경사로를 설치했을 때 통과할 수 있는 길이 몇개인가.

메모리 : 12580 KB
시간 : 80ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int beforeNum = map[i][0];
			boolean[] impossible = new boolean[N];
			boolean possible = true;
			row : for (int j = 0; j < N; j++) {
				// 내려가는 경우
				if (beforeNum - map[i][j] == 1) {
					// L칸 이후까지 갈 수 있는 경우
					if (j + L <= N) {
						for (int k = 0; k < L; k++) {
							if (map[i][j] != map[i][j + k] || impossible[j + k]) {
								possible = false;
								break row;
							}
							impossible[j + k] = true;
						}
					}else {
						possible = false;
						break row;
					}
					beforeNum = map[i][j];
				// 한칸 올라가는 경우
				}else if (beforeNum - map[i][j] == -1) {
					// L칸 이전에 동일한 값이 있는 경우
					if (j - L >= 0) {
						for (int k = 0; k < L; k++) {
							if (map[i][j - k - 1] != beforeNum || impossible[j - k - 1]) {
								possible = false;
								break row;
							}
							impossible[j - k - 1] = true;
						}
					}else {
						possible = false;
						break row;
					}
					beforeNum = map[i][j];
				// 높이가 같은 경우는 문제가 발생할 소지가 없음
				}else if (beforeNum == map[i][j])
					continue;
				// 높이 차이가 2 이상인 경우 문제 발생한 것이기 때문에 개수 세지 않음
				else {
					possible = false;
					break row;
				}
			}
			
			if (possible) {
//				System.out.println(i + "행 가능");
				cnt++;
			}
			
			beforeNum = map[0][i];
			impossible = new boolean[N];
			possible = true;
			col : for (int j = 0; j < N; j++) {
				// 내려가는 경우
				if (beforeNum - map[j][i] == 1) {
					// L칸 이후까지 갈 수 있는 경우
					if (j + L <= N) {
						for (int k = 0; k < L; k++) {
							if (map[j][i] != map[j + k][i] || impossible[j + k]) {
								possible = false;
								break col;
							}
							impossible[j + k] = true;
						}
					}else {
						possible = false;
						break col;
					}
					beforeNum = map[j][i];
				}else if (beforeNum - map[j][i] == -1) {
					// L칸 이전에 동일한 값이 있는 경우
					if (j - L >= 0) {
						for (int k = 0; k < L; k++) {
							if (map[j - k - 1][i] != beforeNum || impossible[j - k - 1]) {
								possible = false;
								break col;
							}
							impossible[j - k - 1] = true;
						}
					}else {
						possible = false;
						break col;
					}
					beforeNum = map[j][i];
				// 같은 곳에서는 문제 발생 여지가 없음
				}else if (beforeNum == map[j][i])
					continue;
				// 높이 차이가 2이상 나는 곳에서는 아예 가능하지 않음
				else {
					possible = false;
					break col;
				}
			}
			
			if (possible) {
//				System.out.println(i + "열 가능");
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}