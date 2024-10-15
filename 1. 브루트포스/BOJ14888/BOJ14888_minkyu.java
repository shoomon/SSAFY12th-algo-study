import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
연산자 끼워넣기

피연산자 사이에 연산자를 끼워넣어 결과가 최대인 것과 최소인 것을 구하시오.

메모리 : 12472 KB
시간 : 64ms

*/

public class Main {
	static int N;
	static int[] nums = {};
	static int[] calcCnt = {};
	static int min;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		// 수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		// 연산자
		// 0 : 덧셈,  1 : 뺄셈,  2 : 곱셈,  3 : 나눗셈
		st = new StringTokenizer(br.readLine());
		calcCnt = new int[4];
		for (int i = 0; i < 4; i++)
			calcCnt[i] = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		int[] curCnt = new int[4];
		select(0, nums[0], curCnt);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void select(int pos, int curVal, int[] curCnt) {
		// 마지막까지 고른 경우
		if (pos == N-1) {
			min = Math.min(min, curVal);
			max = Math.max(max, curVal);
			return;
		}
		
		// 덧셈
		if (curCnt[0] < calcCnt[0]) {
			curCnt[0]++;
			select(pos + 1, curVal + nums[pos+1], curCnt);
			curCnt[0]--;
		}
		// 뺄셈
		if (curCnt[1] < calcCnt[1]) {
			curCnt[1]++;
			select(pos + 1, curVal - nums[pos+1], curCnt);
			curCnt[1]--;
		}
		// 곱셈
		if (curCnt[2] < calcCnt[2]) {
			curCnt[2]++;
			select(pos + 1, curVal * nums[pos+1], curCnt);
			curCnt[2]--;
		}
		// 나눗셈
		if (curCnt[3] < calcCnt[3]) {
			curCnt[3]++;
			select(pos + 1, (curVal < 0) ? -(Math.abs(curVal) / nums[pos+1]) : curVal / nums[pos+1], curCnt);
			curCnt[3]--;
		}
	}
}