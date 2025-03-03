package codingTest;

import java.util.*;

public class 연습__디펜스게임wooseok {
    public static int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙

        for (int e : enemy) {
            if (n >= e) { // 병사로 막을 수 있는 경우
                n -= e;
                pq.offer(e);
                answer++;
                continue;
            }

            if (k > 0) { // 병사가 부족하지만 무적권 사용 가능
                if (!pq.isEmpty() && pq.peek() > e) { // 기존에 막은 적보다 현재 적이 작으면 교체
                    n += pq.poll(); // 가장 많은 병사를 소모한 라운드 병사 복구
                    n -= e; // 현재 라운드 방어
                    pq.offer(e); // 새로운 적을 저장
                }
                answer++;
                k--;
            } else { // 무적권도 없고 병사도 부족하면 종료
                break;
            }
        }
        return answer;
    }

    /*
    public static int solution(int n, int k, int[] enemy) {
    int answer = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < enemy.length; i++) {
        if (n >= enemy[i]) {
            n -= enemy[i];
            pq.offer(enemy[i]);
            answer++;
        } else {
            if (k > 0) {
                if (!pq.isEmpty()) {
                    if (pq.peek() > enemy[i]) {
                        n += pq.poll();
                        n -= enemy[i];
                        pq.offer(enemy[i]);
                    }
                }
                answer++;
                k--;
            } else break;
        }
    }

    return answer;
    }

     */

    /*
    // 조합을 통해 무적권을 사용할 라운드를 선택
    public static void combination(int[] enemy, int n, int k, int start, List<Integer> selected) {
        if (selected.size() == k) {
            calculateRounds(enemy, n, selected);
            return;
        }

        for (int i = start; i < enemy.length; i++) {
            selected.add(i);
            combination(enemy, n, k, i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    // 선택된 무적권 라운드를 제외하고, 나머지 라운드에서 병사로 최대한 막는 함수
    public static void calculateRounds(int[] enemy, int n, List<Integer> selected) {
        int total = 0;
        int round = 0;

        for (int i = 0; i < enemy.length; i++) {
            if (selected.contains(i)) continue; // 무적권 사용 라운드는 패스

            total += enemy[i]; // 병사로 막음

            if (total > n) break; // 병사가 부족하면 종료

            round++;
        }

        maxRounds = Math.max(maxRounds, round + selected.size()); // 무적권으로 패스한 라운드 포함
    }

    public static int solution(int n, int k, int[] enemy) {
        maxRounds = 0;
        combination(enemy, n, k, 0, new ArrayList<>());
        return maxRounds;
    }

     */
}
