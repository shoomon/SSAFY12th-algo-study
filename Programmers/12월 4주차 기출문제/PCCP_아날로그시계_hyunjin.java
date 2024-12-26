package PRO_12월_4주차;

public class PCCP_아날로그시계_hyunjin {
	public static void main(String[] args) {
		int h1 = 0;
		int m1 = 5;
		int s1 = 30;
		int h2 = 0;
		int m2 = 7;
		int s2 = 0;
		PCCP_아날로그시계_hyunjin sol = new PCCP_아날로그시계_hyunjin();
		System.out.println(sol.solution(h1, m1, s1, h2, m2, s2));
	}

	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		// 만나는 횟수 == 알람 울리는 횟수
		int answer = 0;

		// 시작하는 초 / 끝나는 초 확인
		double start = h1 * 3600 + m1 * 60 + s1;
		double end = h2 * 3600 + m2 * 60 + s2;

		// 시작부터 만나 있는 경우
		if (start == 0 || start == 12 * 3600) {
			answer += 1;
		}

		while (start < end) {
			// 시침 => 1시간(60*60초)에 30도 => 1도 => 120초 걸림
			// 분침 => 60분(60*60초)에 360도 => 1도 => 10초 걸림
			// 초침 => 60초에 360도 => 1도 => 1/6초 걸림

			// 시침, 분침, 초침의 시작 각도
			double hd = start / 120 % 360;
			double md = start / 10 % 360;
			double sd = start * 6 % 360;

			// 다음 초 일때 각도 구하기
			double nexthd = calc((start + 1) / 120 % 360);
			double nextmd = calc((start + 1) / 10 % 360);
			double nextsd = calc((start + 1) * 6 % 360);

			// 시침과 초침이 만나는 경우
			if (sd < hd && nexthd <= nextsd) {
				answer += 1;
			}
			// 분침과 초침이 만나는 경우
			if (sd < md && nextmd <= nextsd) {
				answer += 1;
			}

			// 초침, 시침, 분침이 모두 겹치는 경우 중복으로 추가됨
			if (nextsd == nexthd && nextmd == nexthd) {
				answer -= 1;
			}

			start += 1; // 시간 1초씩 추가

		}

		return answer;
	}

	private double calc(double d) {
		if (d == 0) {
			return 360;
		}
		return d;
	}
}
