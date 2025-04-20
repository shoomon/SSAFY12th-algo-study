import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int length = elements.length;
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < length; i++){
            int sum = elements[i];
            set.add(sum);
            for(int j = 1; j < length; j++){
                sum += elements[(i+j)%length];
                set.add(sum);
            }
        }
        
        return set.size();
    }
}