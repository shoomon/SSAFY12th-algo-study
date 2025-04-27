import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int rear = 0;
        HashMap<String, Integer> count = new HashMap<>();
        
        for(; rear < 10; rear++){
            int cnt = count.getOrDefault(discount[rear], 0);
            count.put(discount[rear], cnt+1);
        }
        if(check(want,number,count)) answer++;

        for(; rear < discount.length; rear++){
            //같은 키에 동시에 접근할 경우 값이 덮어씌워질 수 있음
            if(!discount[rear].equals(discount[rear-10])){
                int inCnt = count.getOrDefault(discount[rear], 0);
                int outCnt = count.get(discount[rear-10]);

                count.put(discount[rear], inCnt+1);
                count.put(discount[rear-10], outCnt-1);
            }

            if(check(want,number,count)) answer++;
        }
        
        for(int i = 10; i > 0; i--){
            int cnt = count.get(discount[rear-i]);
            count.put(discount[rear-i],cnt-1);
            
            if(check(want,number,count)) answer++;
        }
        
        return answer;
    }
    
    static boolean check(String[] want, int[] number, HashMap<String, Integer> count){
        for(int i = 0; i < number.length; i++){
            if(count.getOrDefault(want[i], -1) < number[i]) return false;
        }
        return true;
    }
}