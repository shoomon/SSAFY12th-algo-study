//무게별 인원 배열에 체크
import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int[] cnt = new int[1001];
        int len = weights.length;
        
        //lon(N) < 500
        Arrays.sort(weights);
        
        for(int i = 0; i < len; i++){
            cnt[weights[i]]++;
        }
        
        for(int i = 0; i < len; i++){
            
            if(cnt[weights[i]] < 1) continue;
            
            if(weights[i]*2 < 1001 && cnt[weights[i]*2] > 0) {
                answer += (long)cnt[weights[i]]*cnt[weights[i]*2];
            }
            if(weights[i]%2 == 0){
                if((weights[i]/2)*3 < 1001 && cnt[(weights[i]/2)*3] > 0) {
                    answer += (long)cnt[weights[i]]*(long)cnt[(weights[i]/2)*3];
                }
            }
            if(weights[i]%3 == 0){
                if((weights[i]/3)*4 < 1001 && cnt[(weights[i]/3)*4] > 0) {
                    answer += (long)cnt[weights[i]]*(long)cnt[(weights[i]/3)*4];
                }
            }
            if(cnt[weights[i]] > 1){
                answer += ((long)cnt[weights[i]]*(long)(cnt[weights[i]]-1))/2;
            }
            
            cnt[weights[i]] *= -1;
            
            // System.out.println(weights[i]+" "+answer);
        }
        
        return answer;
    }
}