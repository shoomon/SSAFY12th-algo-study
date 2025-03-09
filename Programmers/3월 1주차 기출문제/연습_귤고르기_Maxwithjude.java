import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        //처음엔 냅색처럼 접근했다가 ㅈㅈ
        // int dp [][] = new int [max+1][k+1]
        // 카운팅
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int size : tangerine) {
            cnt.put(size, cnt.getOrDefault(size, 0) + 1);
        }

        // 어차피 작은 값은 최소가 아닐테니 가지치기
        List<Integer> cntli = new ArrayList<>(cnt.values());
        Collections.sort(cntli, Collections.reverseOrder()); // 내림차순 정렬

        // 3. 개수가 많은 종류부터 선택하여 k개를 채움
        int total = 0;
        int kind = 0;
        for (int count : cntli) {
            total += count;
            kind++;
            if (total >= k) {
                return kind; // 최소한의 종류 수 반환
            }
        }
        
        return kind;
    }
}
