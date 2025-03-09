//4000만바이트 = 40000KB = 40MB
//정렬 -> log(1000만) = 약 23~24
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Integer[] count = new Integer[10000001];
        Arrays.fill(count,0);
        
        for(int i : tangerine) count[i]++;
        
        Arrays.sort(count, Collections.reverseOrder());
        
        int cnt = 0;
        for(int i : count){
            if(cnt >= k) break;
            cnt += i;
            answer++;
        }
        
        return answer;
    }
}