import java.util.*;
import java.io.*;

public class 연습_시소짝꿍_mingyung {
	public long solution(int[] weights) {
        long answer = 0;
        
        // 일단 정렬해서 앞에서부터 체크하기
        Arrays.sort(weights);
        
        // Map 활용해 있던 값인지 체크
        Map<Double, Integer> map = new HashMap<>();
        
        for (int i : weights) {
            // 앞에 등록된 값과 비교하기 위해 현재 값 계산
            double a = i*1.0;
            double b = (i*1.0)/2;
            double c = (i*2.0)/3;
            double d = (i*3.0)/4;
            // 가지고 있다면 그 갯수만큼 answer에 더해주기
            if (map.containsKey(a)) answer += map.get(a);
            if (map.containsKey(b)) answer += map.get(b);
            if (map.containsKey(c)) answer += map.get(c);
            if (map.containsKey(d)) answer += map.get(d);
            // 현재 값 map에 넣기 _ 있다면 갯수 증가
            map.put(a, map.getOrDefault(a, 0) +1);
        }
        
        return answer;
    }
}
