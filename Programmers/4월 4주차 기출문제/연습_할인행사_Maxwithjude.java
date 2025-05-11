import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // 맵 쓰는게 좋을듯
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int answer = 0;

        // 10일 연속 구간을 확인
        for (int i = 0; i <= discount.length - 10; i++) {
            // i일부터 i+9일까지 (10일치) 부분 배열 만들기
            String[] verifyDays = Arrays.copyOfRange(discount, i, i + 10);
            if (isValid(wantMap, verifyDays)) {
                answer++;
            }
        }

        return answer;
    }

    // 조건 만족하는지 확인
    static boolean isValid(Map<String, Integer> wantMap, String[] discountWindow) {
        // 현재 10일 간의 할인 품목 수량을 카운트
        Map<String, Integer> discountMap = new HashMap<>();

        for (String item : discountWindow) {
            discountMap.put(item, discountMap.getOrDefault(item, 0) + 1);
        }

        // 비교
        for (String key : wantMap.keySet()) {
            if (discountMap.getOrDefault(key, 0) < wantMap.get(key)) {
                return false;
            }
        }

        return true;
    }
}
