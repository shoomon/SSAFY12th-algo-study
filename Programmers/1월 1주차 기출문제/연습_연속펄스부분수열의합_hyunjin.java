package PRO_1월_1주차;

public class 연습_연속펄스부분수열의합_hyunjin {
	public static void main(String[] args) {
		int[] sequence = { 2, 3, -6, 1, 3, -1, 2, 4 };
		연습_연속펄스부분수열의합_hyunjin sol = new 연습_연속펄스부분수열의합_hyunjin();
		System.out.println(sol.solution(sequence));
	}

	public long solution(int[] sequence) {
		int N = sequence.length; // 입력 배열 길이
		long[] caseOne = new long[N]; // [1, -1, 1...] 곱한 배열
		long[] caseTwo = new long[N]; // [-1, 1, -1...] 곱한 배열
		int n = 1;

		for (int i = 0; i < N; i++) {
			caseOne[i] = sequence[i] * n;
			n *= -1;
			caseTwo[i] = sequence[i] * n;
		}

		long[] dpOne = new long[N]; // caseOne에 대한 dp배열
		long[] dpTwo = new long[N]; // caseTwo에 대한 dp배열

		// 초기값 설정
		dpOne[0] = caseOne[0];
		dpTwo[0] = caseTwo[0];

		long answer = Math.max(dpOne[0], dpTwo[0]);

		for (int i = 1; i < N; i++) {
			// 연속된 부분 수열의 합 중 최대값 구하기
			dpOne[i] = Math.max(dpOne[i - 1] + caseOne[i], caseOne[i]);
			dpTwo[i] = Math.max(dpTwo[i - 1] + caseTwo[i], caseTwo[i]);

			long max = Math.max(dpOne[i], dpTwo[i]);
			answer = Math.max(answer, max);
		}

		return answer;
	}
}
