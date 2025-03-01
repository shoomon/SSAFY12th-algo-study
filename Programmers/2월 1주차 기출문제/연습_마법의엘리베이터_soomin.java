class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0){
            int num = storey%10;
            storey /= 10;
            
            if(num < 5){
                answer += num;
            }else if(num > 5){
                answer += 10-num;
                storey += 1;
            }else{
                if(storey%10 < 5){
                    answer += num;
                }else if(storey%10 >= 5){
                    answer += num;
                    storey += 1;
                }
            }
        }
        
        return answer;
    }
}