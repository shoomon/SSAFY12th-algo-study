//25.01.05
//수학, 자료형 주의
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(long i = 1; i <= r2; i++){
            if(i < r1){
                answer += Math.floor(Math.sqrt((long)r2*r2-i*i)) - Math.ceil(Math.sqrt((long)r1*r1-i*i))+1;
            }else{
                answer += Math.floor(Math.sqrt((long)r2*r2-i*i))+1;
            }
        }
        
        answer *= 4;
        
        return answer;
    }
}