import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer,Integer> left = new HashMap<>();
        Set<Integer> right = new HashSet<>();
        
        for(int i : topping) left.put(i, left.getOrDefault(i,0)+1);
        
        for(int i = topping.length-1; i > -1; i--){
            int count = left.get(topping[i]);
            if(count == 1){
                left.remove(topping[i]);
            }else if(count > 1){
                left.put(topping[i], count-1);
            }
            right.add(topping[i]);
            
            if(left.size() == right.size()) answer++;
        }
        
        return answer;
    }
}