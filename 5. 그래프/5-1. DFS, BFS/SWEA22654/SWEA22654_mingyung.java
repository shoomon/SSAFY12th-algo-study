import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA22654_mingyung {
	// SWEA_22654_차윤이의 RC카
	// 결과 : Pass
	// 메모리 : 18792 kb
	// 시간 : 105 ms
	
	// RC카 조종 연습
	// 커맨드가 주어졌을 때, 목적지에 도달할 수 있는지 구하기
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		// 첫번째 줄 테스트케이스 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 회전 위한 델타
		//          상  우  하  좌
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// 테스트케이스만큼 반복
		for (int tc=1; tc<=T; tc++) {
			// 각 테스트케이스 첫 번째 줄에는 필드 크기 N
			int N = Integer.parseInt(br.readLine());
			int sR = 0;
			int sC = 0;
			int fR = 0;
			int fC = 0;
			// 이후 N개의 줄에 걸쳐 필드 정보 공백 없이
			char[][] map = new char[N][N];
			for (int r=0; r<N; r++) {
				String str = br.readLine();
				for (int c=0; c<N; c++) {
					map[r][c] = str.charAt(c);
					// X면 현재 RC카 위치
					if (map[r][c] == 'X') {
						sR = r;
						sC = c;
					}
					// Y면 이동시키고자 하는 위치
					else if (map[r][c] == 'Y') {
						fR = r;
						fC = c;
					}
				}
			} // 필드 정보 입력 완
			
			// 조종 횟수 Q
			int Q = Integer.parseInt(br.readLine());
			// 출력 : #t 커맨드마다 실행 후 목적지면 1, 아니면 0
			// => 커맨드마다 출력여부 담기
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc);
			
			// 커맨드 반복
			for (int q=0; q<Q; q++) {
				// 커맨드 입력받기
				StringTokenizer st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				char[] cmd = new char[C];
				String str = st.nextToken();
				// 필요한 변수 초기화
				int r = sR;
				int c = sC; // 시작점 입력
				int d = 0; // 회전 위한 델타 인덱스 입력
				for (int i=0; i<C; i++) {
					cmd[i] = str.charAt(i);
					
					// 반복문 돌면서 체크하고 이동
					if (cmd[i] == 'A') {
						// 배열 범위 벗어나거나 T 아니면 이동
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc] != 'T') {
							r = nr;
							c = nc;
						}
					} else if (cmd[i] == 'L') {
						d = (d-1)%4; // 왼쪽으로 돌면 델타 인덱스 -1 + 인덱스 배열 범위 벗어나지 않게 모듈러
					} else if (cmd[i] == 'R') {
						d = (d+1)%4; // 오른쪽으로 돌면 델타 인덱스 +1 + 인덱스 배열 범위 벗어나지 않게 모듈러
					}
				} // 커맨드 입력 및 이동 완
				
				sb.append(" ").append((r==fR && c==fC) ? 1 : 0); // r, c 이동 후 도착점이면 1, 아니면 0 출력
			} // 커맨드 반복 완료
			
			System.out.println(sb);
		} // tc
	} // main
}