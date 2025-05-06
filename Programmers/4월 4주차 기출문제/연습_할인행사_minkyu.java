import java.util.*;

class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            int answer = 0;
            Map<String, Integer> map = new HashMap<>();

            // 초기 10일 윈도우 설정
            for (int i = 0; i < 10 && i < discount.length; i++) {
                map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
            }

            // 초기 윈도우 확인
            if (checkIsOK(want, number, map)) {
                answer++;
            }

            // 슬라이딩 윈도우로 나머지 확인
            for (int i = 1; i <= discount.length - 10; i++) {
                // 이전 항목 제거
                String prevItem = discount[i - 1];
                map.put(prevItem, map.get(prevItem) - 1);
                if (map.get(prevItem) == 0) {
                    map.remove(prevItem);
                }

                // 새 항목 추가
                String newItem = discount[i + 9];
                map.put(newItem, map.getOrDefault(newItem, 0) + 1);

                // 현재 윈도우 확인
                if (checkIsOK(want, number, map)) {
                    answer++;
                }
            }

            return answer;
        }

        public static boolean checkIsOK(String[] want, int[] number, Map<String, Integer> map) {
            for (int i = 0; i < want.length; i++) {
                if (!map.containsKey(want[i]) || map.get(want[i]) < number[i]) {
                    return false;
                }
            }
            return true;
        }
    }