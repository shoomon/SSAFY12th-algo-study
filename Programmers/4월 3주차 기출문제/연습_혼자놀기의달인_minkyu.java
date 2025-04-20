import java.util.*;

public class SelfPlayMaster {
    public static class Solution {
        public int solution(int[] cards) {
            int answer = 0;
            Set<Integer> no1 = new HashSet<>();
            Set<Integer> no2 = new HashSet<>();
            boolean[] visited = new boolean[cards.length];
            // 1번에 위치한 것부터 차례대로 확인해볼 것.
            for (int i = 0; i < cards.length; i++){
                no1 = new HashSet<>();
                visited = new boolean[cards.length];
                int next = i;
                while (!visited[next]){
                    no1.add(cards[next]);
                    visited[next] = true;
                    next = cards[next] - 1;
                }
                for (int j = 0; j < cards.length; j++){
                    if (visited[j]) continue;
                    no2 = new HashSet<>();
                    next = j;
                    while (!visited[next]){
                        no2.add(cards[next]);
                        visited[next] = true;
                        next = cards[next] - 1;
                    }
                    answer = Math.max(answer, no1.size() * no2.size());
                }
            }
            return answer;
        }
    }
}
