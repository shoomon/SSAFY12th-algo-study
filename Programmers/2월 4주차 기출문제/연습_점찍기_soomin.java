//25.03.03

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        int end = d/k;
        
        for(int i = 0; i <= end; i++){
            long max = (long)Math.floor(Math.sqrt((long)d*d-(long)i*i*k*k));
            answer += max/k+1;
        }
        return answer;
    }
}