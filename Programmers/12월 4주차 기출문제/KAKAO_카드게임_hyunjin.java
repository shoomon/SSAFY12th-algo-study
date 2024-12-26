package PRO_12월_4주차;
import java.util.*;

public class KAKAO_카드게임_hyunjin {
	public static void main(String[] args) {
		int coin = 4;
		int[] cards = { 3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4 };
		KAKAO_카드게임_hyunjin sol = new KAKAO_카드게임_hyunjin();
		System.out.println(sol.solution(coin, cards));
	}

	static boolean[] used;
	static int coin;
	static boolean canNext = true; // 다음 라운드로 갈 수 있는지 체크

	public int solution(int coin, int[] cards) {
		this.coin = coin; // coin 값 static 으로

		int sumAns = cards.length + 1; // n+1 값 == 카드 2개의 합
		used = new boolean[cards.length]; // 사용한 카드인지 아닌지 확인

		// 내 카드 정보 List에 추가
		List<Integer> myCardList = new ArrayList<>();

		// 처음 시작 카드 배열 : n/3 개 카드 추가
		// [3,6,7,2]
		for (int i = 0; i < cards.length / 3; i++) {
			myCardList.add(cards[i]);
		}

		int round = 1; // 첫 번째 라운드

		for (int i = cards.length / 3; i < cards.length; i += 2) {
			// 카드 두 개 뽑기
			// 처음 : [3,6,7,2,1,10]
			myCardList.add(cards[i]);
			myCardList.add(cards[i + 1]);

			// 게임 시작
			game(myCardList, cards, sumAns);

			// coin을 다 썼거나, 더 이상 다음 라운드로 갈 수 없는 경우 => return
			if (this.coin < 0 || !canNext) {
				return round;
			}

			round++;
		}

		return round;
	}

	public static void game(List<Integer> myCardList, int[] cards, int sumAns) {

		// 초기 카드 배열에서 합이 n+1(sumAns)와 같은 값이 있는지 확인
		// [3,6,7,2] 에서 확인
		for (int i = 0; i < cards.length / 3; i++) {
			for (int j = i + 1; j < cards.length / 3; j++) {
				if (myCardList.get(i) + myCardList.get(j) == sumAns && !used[i] && !used[j]) {
					used[i] = true;
					used[j] = true;
					return;
				}
			}
		}

		// [3,6,7,2,1,10]에서 확인
		for (int i = 0; i < myCardList.size() - 1; i++) {
			for (int j = i + 1; j < myCardList.size(); j++) {
				if (myCardList.get(i) + myCardList.get(j) == sumAns && !used[i] && !used[j]) {
					used[i] = true;
					used[j] = true;

					// 선택한 값이 처음 뽑은 카드가 아닌 새로 뽑은 상태라면
					// coin 사용하기
					if (i >= cards.length / 3) {
						coin--;
					}

					if (j >= cards.length / 3) {
						coin--;
					}

					return;
				}
			}

		}

		// 합이 sumAns인 쌍을 찾지 못 한 경우
		canNext = false;

	}
}
