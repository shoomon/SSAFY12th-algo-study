package codingTest;

import java.util.*;

public class 연습_시소짝궁_wooseok {

    public static long solution(int[] weights) {
        // 몸무게 개수를 저장하는 맵
        Map<Integer, Long> weightCount = new HashMap<>();
        for (int weight : weights) {
            weightCount.put(weight, weightCount.getOrDefault(weight, 0L) + 1);
        }

        long cnt = 0;

        // 시소 짝꿍을 찾기 위해 weightCount의 keySet을 순회
        for (int w : weightCount.keySet()) {
            long now = weightCount.get(w);

            // 같은 몸무게끼리 (nC2 조합)
            cnt += now * (now - 1) / 2;

            // 다른 몸무게와의 짝꿍 계산 (1:2, 2:3, 3:4 비율)
            cnt += now * weightCount.getOrDefault(w * 2, 0L);
            if (w % 3 == 0) cnt += now * weightCount.getOrDefault(w * 4 / 3, 0L);
            if (w % 2 == 0) cnt += now * weightCount.getOrDefault(w * 3 / 2, 0L);
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] weights = {100, 180, 360, 100, 270};
        System.out.println(solution(weights)); // 결과: 2
    }
}
