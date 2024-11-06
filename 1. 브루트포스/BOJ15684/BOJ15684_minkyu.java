import java.util.*;
import java.io.*;

/*
사다리 조작

사다리의 가로줄을 추가하여 본인 줄에 다시 도착하도록 최대 3개까지 추가하여 최소 개수가 몇개인지
구하시오.

메모리 : 294548 KB
시간 : 2212 ms

*/

public class Main {
	static int N;
	static int M;
	static int H;
	static boolean[][] arr = {};
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new boolean[H][N];
		
		min = -1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		
		comb(0,0);
		
		System.out.println(min);
	}
	
	public static void comb(int cnt, int pos) {
		// 가능한 경우 뒤로 바로 돌아감
		if (checker()) {
			if (min == -1)
				min = cnt;
			else
				min = Math.min(min, cnt);
			return;
		}
		
		// 3개 고른 경우, 최소 회수만큼 진행된 경우 끝내고 뒤로 복귀
		if (cnt == min || cnt == 3)
			return;
		
		// 범위에서 아예 벗어난 경우
		if (pos == N * H)
			return;
		
		// 선택 안하고 넘기는 경우
		comb (cnt, pos + 1);
		// 현재 위치 변환
		int r = pos / N;
		int c = pos % N;
		// 현재 위치에 선이 없고 해당 선 위치 양 옆으로 선이 없는 경우 추가
		if (!arr[r][c] && c < N - 1) {
			boolean isAddable = true;
			if (c - 1 >=0 && arr[r][c - 1])
				isAddable = false;
			if (c + 1 < N && arr[r][c + 1])
				isAddable = false;
			
			if (isAddable) {
				arr[r][c] = true;
				comb(cnt + 1, pos + 1);
				arr[r][c] = false;
			}
		}
	}

	// 사다리가 제대로 본인 위치로 복귀하는지 확인
	public static boolean checker() {
		// 스택으로 어디어디 연결이 되는지 확인이 필요하다.
		// 연결 되는 것을 확인했으면, 해당 연결을 어떻게 파악할 것인지 확인할 것.
		for (int i = 0; i < N; i++) {
			Stack<int[]> stack = new Stack<>();
			int curN = i;
			// 밑 행까지 이동할 수 있도록 하는 과정이 필요.
			for (int j = 0; j < H; j++) {
				// 새로운 방향을 만난 경우 (우측 방향)
				if (arr[j][curN]) {
					// 동일한 방향으로 다시 복귀한 경우 해당 스택에 관해서는 문제가 발생하지 않음
					if (stack.size() > 0 && stack.peek()[1] == curN)
						stack.pop();
					// 서로 다르다면 해당 스택은 해결해야될 필요가 있음.
					else
						stack.push(new int[] { j, curN });
					curN = curN + 1;
					// 새로운 방향을 만난 경우 (좌측 방향)
				} else if (curN - 1 >= 0 && arr[j][curN - 1]) {
					if (stack.size() > 0 && stack.peek()[1] == curN - 1)
						stack.pop();
					else
						stack.push(new int[] { j, curN - 1 });
					curN = curN - 1;
				}
			}

			// 스택이 비어있지 않다면 원래 위치로 복귀하지 못한 것임
			if (stack.size() > 0)
				return false;
		}
		return true;
	}
}