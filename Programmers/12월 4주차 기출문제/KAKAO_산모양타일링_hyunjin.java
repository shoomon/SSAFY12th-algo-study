package PRO_12월_4주차;
public class KAKAO_산모양타일링_hyunjin {

	public static void main(String[] args) {
		int n = 4;
		int[] tops = { 1, 1, 0, 1 };

		KAKAO_산모양타일링_hyunjin sol = new KAKAO_산모양타일링_hyunjin();
		System.out.println(sol.solution(n, tops));
	}

	public int solution(int n, int[] tops) {
		int answer = 0;

		// a와 b 배열 초기화: 타일링을 계산하기 위한 동적 계획법용 배열
		int[] a = new int[n + 1];
		int[] b = new int[n + 1];

		// 문제에서 주어진 모듈러 값 (값이 너무 커지는 것을 방지)
		int mod = 10007;

		// 초기값 설정 (a[0]과 b[0]): 타일링의 시작점
		a[0] = 0;  // a[0]은 타일링하지 않는 경우
		b[0] = 1;  // b[0]은 타일링을 한 가지 방법으로 완료한 경우

		// i=1부터 n까지 반복하며 a[i]와 b[i]를 계산
		for (int i = 1; i <= n; ++i) {
			// a[i]는 이전 단계의 a[i-1]과 b[i-1]의 합
			// a[i]는 '산 모양' 타일링이 포함되지 않는 경우를 의미
			a[i] = (a[i - 1] + b[i - 1]) % mod;

			// tops[i-1]이 1이면, 산 모양 타일링을 생성 가능
			if (tops[i - 1] > 0) {
				// b[i]는 a[i-1]의 두 배 + b[i-1]의 세 배
				// 산 모양 타일링을 추가적으로 고려
				b[i] = (2 * a[i - 1] + 3 * b[i - 1]) % mod;
			} else { // tops[i-1]이 0이면, 산 모양 타일링을 생성 불가능
				// b[i]는 a[i-1] + b[i-1]의 두 배
				// 산 모양 타일링을 제한적으로 고려
				b[i] = (a[i - 1] + 2 * b[i - 1]) % mod;
			}
		}

		// n번째 단계에서 가능한 모든 타일링 방법 수를 계산
		answer = (a[n] + b[n]) % mod;

		// 결과값 반환
		return answer;
	}

}
