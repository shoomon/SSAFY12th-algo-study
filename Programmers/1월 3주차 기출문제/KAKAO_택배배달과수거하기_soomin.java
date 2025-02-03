//먼 곳부터 끝내기
//출발: 마지막 집부터 배달해야 할 개수 합 < cap
//회수: 마지막 집부터 수거해야 할 개수 합 < cap
//1회 왕복은 출발+회수
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int cnt = 0, deliver=0, pickup=0;
        
        for(int i = n-1; i > -1; i--){
            cnt = 0;
            deliver -= deliveries[i];
            pickup -= pickups[i];
            while(deliver < 0 || pickup < 0){
                cnt++;
                deliver += cap;
                pickup += cap;
            }
            answer += (i+1)*cnt*2;
        }
        
        return answer;
    }
}