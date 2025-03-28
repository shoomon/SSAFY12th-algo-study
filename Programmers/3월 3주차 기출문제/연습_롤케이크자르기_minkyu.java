import java.util.*;

public class CutRollCake {
    public static void main(String[] args) {

    }

    public static class Solution {
        public int solution(int[] topping) {
            int answer = 0;

            Map<Integer, Integer> m1 = new HashMap<>();
            for (int t : topping)
                m1.put(t, m1.getOrDefault(t, 0) + 1);

            Map<Integer, Integer> m2 = new HashMap<>();

            for (int i = 0; i < topping.length - 1; i++) {
                int tmp = topping[i];
                // 자르는 위치 변경
                m2.put(tmp, m2.getOrDefault(tmp, 0) + 1);
                m1.put(tmp, m1.get(tmp) - 1);
                if (m1.get(tmp) == 0) m1.remove(tmp);

                // 공평하게 나눌 수 있는 가짓수 증가
                if (m2.size() == m1.size()) answer++;
            }

            return answer;
        }
    }
}
