package codingTest;

// 69m
// 12680KB	104ms
import java.io.*;
import java.util.*;

public class BOJ14499 {
	static int N, M, x, y, K, map[][];
	static int[] dy = {0, 0, -1, 1}; // 동 서 북 남
	static int[] dx = {1, -1, 0, 0};
	
	static int[] dice = {0,0,0,0,0,0}; // 동 서 북 남 위 아래
	static int floorNum = 0; // 아랫면 좌표에 적힌 주사위 숫자
	
//	  북
//	서 윗 동
//	  남
//	 바닥
	
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
//			System.out.println("dice 동 서 북 남 위 아래 : " + Arrays.toString(dice));
			int dir = Integer.parseInt(st.nextToken())-1;
			
			// 이동 (x, y 좌표 변경)
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			// 바깥 이동 불가(해당 명령 무시 및 출력X)
			if (overLimit(ny, nx)) continue;
			
			// 좌표 업데이트
			x = nx;
			y = ny;
			
			// 바닥면 체크
			// dice = {동 서 북 남 위 아래}
			floorNum = dice[dir];
//			System.out.println("dir : "+dir);
			if (map[ny][nx]==0) {
				// 이동한 칸에 쓰여있는 수가 0 -> 바닥면에 쓰여있는 수가 칸에 복사
				map[ny][nx] = floorNum;
			} else {
				// 0이 아닌 경우 -> 칸에 쓰여 있는 수가 바닥면에 복사, 칸에 쓰여있는 수 0 
				floorNum = map[ny][nx];
				map[ny][nx] = 0;
			}
			
			// 주사위 숫자 변경 (아랫면 제외) 윗면-반댓면-아랫면 순대로 업데이트
			
			int oppsiteDir = Oppsite(dir);
			dice[dir] = dice[4];
			dice[4] = dice[oppsiteDir];
			dice[oppsiteDir] = dice[5];
			// 아랫면 주사위에 업데이트
			dice[5] = floorNum;

			// 이동 완료 후 상단에 쓰여있는 값 출력
			System.out.println(dice[4]);
		}
	}
	
	public static boolean overLimit(int y, int x) {
		return (y<0 || y>=N || x<0 || x>=M);
	}
	
	public static int Oppsite(int d) {
		switch (d){
		case 0: //동
			return 1; 
		case 1: //서
			return 0;
		case 2: //북
			return 3;
		case 3: //남
			return 2;
		case 4: //위
			return 5;
		case 5: //아래
			return 4;
		}
		
		return -1;
	}
}
