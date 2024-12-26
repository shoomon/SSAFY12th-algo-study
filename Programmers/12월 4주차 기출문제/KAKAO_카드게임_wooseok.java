package codingTest;

import java.util.*;

public class KAKAO_카드게임_wooseok {

    static class Pair {
        int cost;
        int card1, card2;

        public Pair(int cost, int card1, int card2) {
            this.cost = cost;
            this.card1 = card1;
            this.card2 = card2;
        }
    }

    public static void main(String[] args) {
        KAKAO_카드게임_wooseok sol = new KAKAO_카드게임_wooseok();
        System.out.println(sol.solution(4, new int[]{3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4})); // 5
        System.out.println(sol.solution(3, new int[]{1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12})); // 2
        System.out.println(sol.solution(2, new int[]{5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7})); // 4
        System.out.println(sol.solution(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18})); // 1
    }

    public static int solution(int coin, int[] cards) {
        int n = cards.length;
        int answer = 1;

        Map<Integer, Boolean> hand = new HashMap<>();
        Map<Integer, Integer> cardCost = new HashMap<>();

        // PriorityQueue에 Comparator로 정렬 기준 적용
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.cost));

        // 초기 카드 n/3장을 손에 넣음
        for (int i = 0; i < n / 3; i++) {
            hand.put(cards[i], true);

            int complement = n + 1 - cards[i];
            if (hand.getOrDefault(complement, false)) {
                pq.offer(new Pair(0, cards[i], complement));
            }
        }

        // 나머지 카드 처리
        for (int i = n / 3; i < n; i++) {
            hand.put(cards[i], true);
            cardCost.put(cards[i], cardCost.getOrDefault(cards[i], 0) + 1);

            int complement = n + 1 - cards[i];
            if (hand.getOrDefault(complement, false)) {
                int cost = cardCost.get(cards[i]) + cardCost.getOrDefault(complement, 0);
                pq.offer(new Pair(cost, cards[i], complement));
            }

            // 라운드가 시작될 때마다 처리
            if ((i - n / 3 + 1) % 2 == 0) {
                if (!pq.isEmpty()) {
                    Pair pair = pq.poll();
                    if (pair.cost > coin) {
                        break;
                    }
                    coin -= pair.cost;
                    answer++;
                } else {
                    break;
                }
            }
        }

        return answer;
    }

}

