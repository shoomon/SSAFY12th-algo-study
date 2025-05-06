import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<want.length;i++){
            map.put(want[i],number[i]);
        }
        Map<String,Integer> copy = new HashMap<>();
        int cnt=0;

        for(int i=0;i<=discount.length-10;i++){
            copy = new HashMap<>();
            copy.putAll(map);
      
            for(int j=i;j<i+10;j++){
                String key = discount[j];
                if(copy.containsKey(key)){
                    if(copy.get(key)-1==0){
                        copy.remove(key);
                    }else{
                        copy.put(key,copy.get(key)-1);
                    }
                }
            }
            if(copy.size()==0) cnt++;
            
        }

        return cnt;
    }
}
