import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
스타트와 링크

스타트 팀과 링크 팀으로 나누어 시너지 차이가 최소가 되는 경우의 시너지 차이를 구하시오.

메모리 : 14136 KB
시간 : 256ms

*/

public class Main {
	static int N;
	static int[][] synergy = {};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		synergy = new int [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				synergy[i][j] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] selected = new boolean[N];
		recur(0,0,selected);
		System.out.println(min);
	}
	
	public static void recur(int idx, int cnt, boolean[] selected) {
		if (cnt == N/2) {
			int aSum = 0;
			int bSum = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					for (int j = 0; j < N; j++) {
						if (selected[j])
							aSum += synergy[i][j];
					}
				}else {
					for (int j = 0; j < N; j++) {
						if (!selected[j])
							bSum += synergy[i][j];
					}
				}
			}
			if (min > Math.abs(aSum - bSum))
				min = Math.abs(aSum - bSum);
			return;
		}
		
		if (idx == N)
			return;
		
		recur(idx + 1, cnt, selected);
		selected[idx] = true;
		recur(idx + 1, cnt + 1, selected);
		selected[idx] = false;
	}
}