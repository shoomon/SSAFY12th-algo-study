import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_mingyung {
	// S1_14888_연산자 끼워넣기
	// 결과 : 맞았습니다!!
	// 메모리 : 16,628 kb
	// 시간 : 532 ms
	
	// N개의 수로 이루어진 수열
	// 그 사이 넣을 수 있는 연산자 N-1개 (+, - *, /)
	// 수의 순서는 바꾸지 못함
	// 식의 계산은 연산자 우선 순위 무시하고 앞에서부터 진행
	// 나눗셈은 정수 나눗셈 (몫만 취함) + 음수일 시 양수 몫을 음수로 변환
	// 만들 수 있는 식의 결과가 최대인 것과 최소인 것 구하기
	
	static int N, max, min, result;
	static int[] nums, cals, permC;
	static int[] cal = new int[4]; // {+, -, *, /}
	static boolean[] vis;
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		// 수의 개수 N (2<=N<=11)
		// 4게 정수 -> 덧셈, 뺄셈, 곱셈, 나눗셈 각 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st1.nextToken());
		}
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			cal[i] = Integer.parseInt(st2.nextToken());
		} // 입력받기 완
		
		// 필요한 변수 초기화 및 값 찾기
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		// 계산 순열 뽑기 위해 배열 새로 만들기
		cals = new int[N-1];
		int idx = 0;
		for (int i=0; i<cal[0]; i++) {
			cals[idx] = 0;
			idx++;
		}
		for (int i=0; i<cal[1]; i++) {
			cals[idx] = 1;
			idx++;
		}
		for (int i=0; i<cal[2]; i++) {
			cals[idx] = 2;
			idx++;
		}
		for (int i=0; i<cal[3]; i++) {
			cals[idx] = 3;
			idx++;
		}
		vis = new boolean[N-1];
		permC = new int[N-1];
		
		perm(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void perm(int idx) {
		if (idx==N-1) {
			int ans = calc();
			if (min>ans) {
				min = ans;
			}
			if (max<ans) {
				max=ans;
			}
			return;
		}
		
		for (int i=0; i<N-1; i++) {
			if (vis[i]) continue;
			permC[idx] = cals[i];
			vis[i] = true;
			perm(idx+1);
			vis[i] = false;
		}
	}
	
	static int calc() {
		result = nums[0];
		for (int i=0; i<N-1; i++) {
			if (permC[i]==0) {
				result += nums[i+1];
			} else if (permC[i]==1) {
				result -= nums[i+1];
			} else if (permC[i]==2) {
				result *= nums[i+1];
			} else if (permC[i]==3) {
				result /= nums[i+1];
			}
		}
		
		return result;
	}
}