import java.util.*;
import java.io.*;

public class KAKAO_card_game {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] cards = { 3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4 };
		int ans = sol.solution(4, cards);
		System.out.println(ans);
	}

	public static class Solution {
		int round, tarNum;
		int[] cardDeck;
		Set<Integer> selectedCards = new HashSet<>();
		Set<Integer> additionalCards = new HashSet<>();

		public int solution(int coin, int[] cards) {
			tarNum = cards.length + 1;
			cardDeck = cards;

			// n/3 카드로 선택된 뭉치들 추가
			for (int i = 0; i < cards.length / 3; i++)
				selectedCards.add(cards[i]);

			int idx = cards.length/3;
			while(true) {
				round++;
				if (idx == cards.length) break;
				additionalCards.add(cards[idx]);
				additionalCards.add(cards[idx+1]);
				idx+=2;
				boolean isPossible = false;
				
				// 코인 쓰지 않고 해결할 수 있는지 파악
				for (int i : selectedCards) {
					if (selectedCards.contains(tarNum - i)) {
						selectedCards.remove(i);
						selectedCards.remove(tarNum - i);
						isPossible = true;
						break;
					}
				}
				
				// 각각 한 개의 코인만을 가지고 해결할 수 있는지 파악
				if (!isPossible && coin > 0) {
					for (int i : selectedCards) {
						if (additionalCards.contains(tarNum - i)) {
							selectedCards.remove(i);
							additionalCards.remove(tarNum-i);
							coin--;
							isPossible = true;
							break;
						}
					}
				}
				
				
				// 두 개의 코인을 가지고 해결할 수 있는지 파악
				if (!isPossible && coin > 1) {
					for (int i : additionalCards) {
						if (additionalCards.contains(tarNum-i)) {
							additionalCards.remove(i);
							additionalCards.remove(tarNum - i);
							coin-=2;
							isPossible = true;
							break;
						}
					}
				}
				
				// 해결을 하지 못하면 게임을 종료함
				if (!isPossible) break;
			}

			return round;
		}
	}
}
