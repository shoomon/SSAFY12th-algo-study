import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        HashSet<Integer> set = new HashSet<>(); // 형
        HashMap<Integer, Integer> map = new HashMap<>(); // 동생

        // 동생한테 다 일단 넣기
        for (int i = 0; i < topping.length; i++) {
            map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);
        }

        // 형한테 하나씩 주면서, 동생은 하나씩 빼기
        // 형 == 동생 갯수 같은 경우 => answer++
        for (int i = 0; i < topping.length; i++) {
            // 형 토핑 추가
            set.add(topping[i]);

            // 형한테 추가한 토핑, 동생은 하나 빼기
            map.put(topping[i], map.get(topping[i]) - 1);

            // 아예 사라져버린 토핑 종류는 map에서 제거
            if (map.get(topping[i]) == 0) {
                map.remove(topping[i]);
            }

            // 형과 동생 토핑이 갯수가 같으면 추가
            if (set.size() == map.size()) {
                answer++;
            }
        }


        return answer;
    }
}