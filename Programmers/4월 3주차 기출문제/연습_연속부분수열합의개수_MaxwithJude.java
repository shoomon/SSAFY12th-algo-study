import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        Set<Integer> set = new HashSet<>();

        for (int len = 1; len <= n; len++) { // 부분 수열 길이
            for (int start = 0; start < n; start++) { // 시작 인덱스
                int sum = 0;
                for (int i = 0; i < len; i++) { // 길이만큼 더하기
                    sum += elements[(start + i) % n]; // 원형 배열처럼 처리
                }
                set.add(sum);
            }
        }

        return set.size(); 
    }
}
