// 못품!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085_mingyung {
	// 실버2_사탕 게임
	// N*N 크기에 사탕 채워 놓기
	// 색이 다른 인접한 두 칸 골라 서로 교환
	// 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분 골라먹기
	// 먹을 수 있는 사탕의 최대 개수 구하기
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사탕배열 만들기
		int N = Integer.parseInt(br.readLine());
		char[][] candy = new char[N][N];
		for (int r=0; r<N; r++) {
			String str = br.readLine();
			for (int c=0; c<N; c++) {
				candy[r][c] = str.charAt(c);
			}
		}
		
		// 기본 배열 최댓값 구하기
		int eat = eatCandy(candy);
		
		// 배열 바꾸면서 최댓값 구하기
		// 행 순회
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				
			}
		}
		
		// 출력
	}
	
	static int eatCandy(char[][] candy) {
		int max=0;
		// 행 순회
		for (int r=0; r<candy.length; r++) {
			for (int c=0; c<candy.length-1; c++) {
				int cnt=0;
				for (int i=1; i<candy.length-c; i++) {
					if (c+i<candy.length && candy[r][c]==candy[r][c+i]) {
						cnt++;
					}
				}
				if (max<cnt) {
					max = cnt;
				}
			}
		}
		
		// 열 순회
		for (int c=0; c<candy.length; c++) {
			for (int r=0; r<candy.length-1; r++) {
				int cnt=0;
				for (int i=1; i<candy.length-r; i++) {
					if (r+i<candy.length && candy[r][c]==candy[r+i][c]) {
						cnt++;
					}
				}
				if (max<cnt) {
					max = cnt;
				}
			}
		}
		return max;
	}
}