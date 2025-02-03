package 프로그래머스;

import java.util.*;

class 습_시소짝궁_mirim {
    public long solution(int[] weights) {
        long count = 0; // 시소 짝꿍의 개수를 저장할 변수
        Map<Integer, Integer> weightMap = new HashMap<>(); // 몸무게별 개수를 저장할 맵
        
        Arrays.sort(weights); // 정렬하여 중복을 쉽게 처리
        
        for (int weight : weights) {
            // 현재 몸무게와 균형을 맞출 수 있는 비율 (1:1, 2:3, 3:4, 2:4)
            count += weightMap.getOrDefault(weight, 0); // 동일한 몸무게 (1:1 비율)
            count += weightMap.getOrDefault(weight * 2 / 3, 0); // 2:3 비율
            count += weightMap.getOrDefault(weight * 3 / 4, 0); // 3:4 비율
            count += weightMap.getOrDefault(weight * 2 / 4, 0); // 2:4 비율
            
            // 현재 몸무게를 맵에 저장하여 이후 비교 시 활용
            weightMap.put(weight, weightMap.getOrDefault(weight, 0) + 1);
        }
        
        return count;
    }
}