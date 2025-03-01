package Programmers.PRO_2월_2주차;

public class 연습_마법의엘리베이터_hyunjin {
	public int solution(int storey) {
		int answer = 0;

		while(storey != 0){
			int one = storey % 10; //  1의 자리
			int ten = (storey / 10) % 10; // 10의 자리

			if (one > 5) { //  올림
				answer += (10 - one);
				storey += 10;
			} else if (one == 5) { // 10의 자리가 5이상이면 올림, 5미만이면 내림
				answer += one;
				storey += (ten >= 5 ? 10 : 0);
			} else { //  내림
				answer += one;
			}

			// 10으로 나누어 1의 자리 없애고 다음 계산 진행
			storey /= 10;
		}

		return answer;
	}
}
