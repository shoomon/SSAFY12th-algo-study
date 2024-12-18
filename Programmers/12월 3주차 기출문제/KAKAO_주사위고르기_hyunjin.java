
import java.util.*;

class SolKAKAO_주사위고르기_hyunjinution {
	public int[] solution(int[][] dice) {
		int[] answer = {};

		int totalDice = dice.length; // 전체 주사위 개수
		int num = totalDice / 2; // A와 B가 가져가는 주사위 개수

		List<int[]> aDiceList = getComb(totalDice, num); // 조합을 통해 어떤 번호의 주사위를 가져갈 것인지 구하기

		return answer;
	}

	// 주사위 조합 구하기
	public static List<int[]> getComb(int totalDice, int num) {
		List<int[]> list = new ArrayList<>();
		combination(list, new int[num], 0, totalDice, 0);
		return list;
	}

	// 조합 재귀 메서드
	private static void combination(List<int[]> list, int[] temp, int start, int totalDice, int depth) {
		if (depth == temp.length) {
			list.add(temp.clone()); // 현재 조합 저장
			return;
		}

		for (int i = start; i < totalDice; i++) {
			temp[depth] = i; // 현재 선택한 주사위 번호
			combination(list, temp, i + 1, totalDice, depth + 1); // 다음 번호 선택
		}
	}
}