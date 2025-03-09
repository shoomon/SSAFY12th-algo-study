import java.util.*;

class Solution {
    public List<Integer> solution(int e, int[] starts) {
        List<Integer> answer = new ArrayList<>();
        int[] count = new int[e+1];
        
        for(int i = 1; i < e+1; i++){
            for(int j = i; j < e+1; j += i) count[j]++;
            //for(int j = 1; i*j < e+1; j++) count[j]++;
        }
        
        int[] maxCnt = new int[e+1];
        int maxIdx=e;
        int maxVal=count[e];
        
        for(int i = e; i > 0; i--){
            if(count[i] >= maxVal){
                maxIdx = i;
                maxVal = count[i];
            }
            
            maxCnt[i] = maxIdx;
        }
        
        for(int i : starts){
            answer.add(maxCnt[i]);
        }
        
        return answer;
    }
}