//24.12.17
//설계: 2분
//구현: 40분
//이분탐색+소요 시간 계산 -> O(Nlon(N))
//양수 조건 주의
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0, left, right, mid;
        
        left = 0;
        right = 300000;
        mid = 0;
        while(left < right){
            mid = (left+right)/2;
            
            if(calculateTime(diffs,times,limit,mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        while(!calculateTime(diffs,times,limit,mid))mid++;            
 
        answer = mid;
        if(answer == 0) answer += 1;
        
        return answer;
    }
    
    static boolean calculateTime(int[] diffs, int[] times, long limit, int level){
        long totalTime=0;
        int len = diffs.length;
        
        for(int i = 0; i < len; i++){
            if(diffs[i] <= level){
                totalTime += times[i];
            }else{
                if(i > 0){
                    totalTime += (diffs[i]-level)*(times[i]+times[i-1])+times[i];
                }else{
                    totalTime += (diffs[i]-level)*times[i]+times[i];
                }
                
            }
        }
        
        if(totalTime <= limit) return true;
        return false;
    }
}