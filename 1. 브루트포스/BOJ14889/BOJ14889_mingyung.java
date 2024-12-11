import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_mingyung {
	// S1_14889_스타트와 링크
	// 결과 : 맞았습니다!!
	// 메모리 : 21,100 kb
	// 시간 : 292 ms
	
	// 축구 N명, 짝수로 스타트 팀과 링크 팀으로 나눔
	// 능력치 Sij :  i, j 사람이 같은 팀에 속했을 때 팀에 더해지는 능력치
	// 두 팀간 능력치 차이 최솟값 출력
	
	static int N, min;
	static int[][] syn;
	static boolean[] team;
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N 주어짐
		N = Integer.parseInt(br.readLine());
		// N줄에 걸쳐 S 주어짐
		syn = new int[N][N];
		for (int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				syn[r][c] = Integer.parseInt(st.nextToken());
			}
		} // 입력받기 완료
		
		// 필요한 변수 초기화
		team = new boolean[N];
		min = Integer.MAX_VALUE;
		
		comb(0,0);
		
		// 출력하기
		System.out.println(min);
	} // main
	
	// 조합 : 팀짜기
	static void comb(int idx, int sidx) {
		// 팀이 뽑아 졌을 때
		if (sidx == N/2) {
			// 팀 간의 시너지 찾기
			int sS = 0; // 스타트팀 시너지
			int lS = 0; // 링크팀 시너지
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					// 스타트팀이라면
					if (team[r] && team[c]) {
						sS += syn[r][c];
					}
					// 링크팀이라면
					else if (!team[r] && ! team[c]) {
						lS += syn[r][c];
					}
				}
			}
			
			// 다 더랬을 때 차이가 적으면 min값 갱신
			if(min > (Math.abs(sS-lS)))
				min = Math.abs(sS -lS);
			return;
		}
		
		// 팅이 안 뽑아졌는데 idx가 배열을 벗어났을 때
		if (idx == N) return;
		
		// 조합하기
		team[idx] = true;
		comb(idx+1, sidx+1);
		
		team[idx] = false;
		comb(idx+1, sidx);
	}
}