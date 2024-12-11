import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891_mingyung {
	// G5_14891_톱니바퀴
	// 결과 : 맞았습니다!!
	// 메모리 : 14,272 kb
	// 시간 : 104 ms
	
	// 8개의 톱니를 가지고 있는 톱니바퀴 4개
	// 각 N(0), S(1)극 지니고 있음
	// 회전시킬 때 맞닿아 있는 부분이 다른 극이면 반대 방향으로 함께 회전
	// 아니면 회전 x
	// 12시방향부터 시계방향으로 톱니바퀴 초기 상태가 주어지고,
	// 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴 상태 구하는 프로그램 만들기
	
	static char[] w1 = new char[8];
	static char[] w2 = new char[8];
	static char[] w3 = new char[8];
	static char[] w4 = new char[8];
	
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 4번째 줄까지 각 톱니바퀴 상태
		w1 = br.readLine().toCharArray();
		w2 = br.readLine().toCharArray();
		w3 = br.readLine().toCharArray();
		w4 = br.readLine().toCharArray();
		
		int K = Integer.parseInt(br.readLine());
		int[][] turn = new int[K][2];
		for (int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<2; j++) {
				turn[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력받기 완
		
		for (int i=0; i<K; i++) {
			// 첫번째 톱니 기준
			if (turn[i][0]==1) {
				// 일단 내꺼 돌릴 방향 체크
				if (turn[i][1]==-1) {
					// 뒤에 붙어있으면 뒤에도 돌려
					if (check(w1, w2)) {
						// 먼저 붙어있는지 체크해야 제대로 확인하고 돌림!
						if (check(w2, w3)) {
							if (check(w3, w4)) {
								turnC(w4);
							}
							turnR(w3);
						}
						turnC(w2);
					}
					// 내꺼 돌려 => 돌릴 방향 체크하고 돌려야해!!
					turnR(w1);
				} else {
					// 뒤에 붙어있으면 뒤에도 돌려
					if (check(w1, w2)) {
						if (check(w2, w3)) {
							if (check(w3, w4)) {
								turnR(w4);
							}
							turnC(w3);
						}
						turnR(w2);
					}
					// 내꺼 돌려
					turnC(w1);
				}
			}
			
			// 4번째 톱니 기준
			else if (turn[i][0]==4) {
				// 일단 내꺼 돌릴 방향 체크
				if (turn[i][1]==-1) {
					// 앞에 붙어있으면 앞에도 돌려
					if (check(w3, w4)) {
						if (check(w2, w3)) {
							if (check(w1, w2)) {
								turnC(w1);
							}
							turnR(w2);
						}
						turnC(w3);
					}
					// 내꺼 돌려
					turnR(w4);
				} else {
					// 앞에 붙어있으면 앞에도 돌려
					if (check(w3, w4)) {
						if (check(w2, w3)) {
							if (check(w1, w2)) {
								turnR(w1);
							}
							turnC(w2);
						}
						turnR(w3);
					}
					// 내꺼 돌려
					turnC(w4);
				}
			}
			
			// 2번째 기준
			else if (turn[i][0]==2) {
				// 일단 내꺼 돌릴 방향 체크
				if (turn[i][1]==-1) {
					// 앞에 붙어있으면 앞에도 돌려
					if (check(w1, w2)) {
						turnC(w1);
					}
					// 뒤에 붙어있으면 뒤에도 돌려
					if (check(w2, w3)) {
						if (check(w3, w4)) {
							turnR(w4);
						}
						turnC(w3);
					}
					// 내꺼 돌려
					turnR(w2);
				} else {
					// 앞에 붙어있으면 앞에도 돌려
					if (check(w1, w2)) {
						turnR(w1);
					}
					// 뒤에 붙어있으면 뒤에도 돌려
					if (check(w2, w3)) {
						if (check(w3, w4)) {
							turnC(w4);
						}
						turnR(w3);
					}
					// 내꺼 돌려
					turnC(w2);
				}
			}
			
			// 3번째 톱니
			else if (turn[i][0]==3) {
				// 일단 내꺼 돌릴 방향 체크
				if (turn[i][1]==-1) {
					// 뒤에 붙어있으면 뒤에도 돌려
					if (check(w3, w4)) {
						turnC(w4);
					}
					// 앞에 붙어있으면 앞에도 돌려
					if (check(w2, w3)) {
						if (check(w1, w2)) {
							turnR(w1);
						}
						turnC(w2);
					}
					// 내꺼 돌려
					turnR(w3);
				} else {
					// 뒤에 붙어있으면 뒤에도 돌려
					if (check(w3, w4)) {
						turnR(w4);
					}
					// 앞에 붙어있으면 앞에도 돌려
					if (check(w2, w3)) {
						if (check(w1, w2)) {
							turnC(w1);
						}
						turnR(w2);
					}
					// 내꺼 돌려
					turnC(w3);
				}
			}
		} // 돌리기
		
		// 출력하기
		int ans = 0;
		if (w1[0]=='1') ans+=1;
		if (w2[0]=='1') ans+=2;
		if (w3[0]=='1') ans+=4;
		if (w4[0]=='1') ans+=8;
		
		System.out.println(ans);
	} // main
	
	// 붙어있는지 체크
	// arr1 : 앞 톱니, arr2 : 뒤 톱니
	static boolean check(char[] arr1, char[] arr2) {
		if (arr1[2]!=arr2[6]) {
			return true;
		}
		return false;
	}
	
	// 시계방향으로 돌리기
	static void turnC(char[] arr) {
		char tmp = arr[7];
		for (int i=7; i>0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = tmp;
	}
	
	// 반시계방향으로 돌리기
	static void turnR(char[] arr) {
		char tmp = arr[0];
		for (int i=0; i<7; i++) {
			arr[i] = arr[i+1];
		}
		arr[7] = tmp;
	}
}