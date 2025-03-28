import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        stack.push(0);
        
        int cur = 1;
        for(int i : order){
            if(i != cur){ //현재랑 다름
                if(stack.peek() == i){ //스택에 있음
                    stack.pop();
                    answer++;
                }else{ //스택에 없음
                    while(cur < i) stack.push(cur++); //i 전까지 모두 스택에 넣음
                    if(cur == i){ //i랑 같음
                        cur++;
                        answer++;
                    }else{
                        return answer;
                    }
                }
            }else{
                cur++;
                answer++;
            }
        }
        return answer;
    }
}