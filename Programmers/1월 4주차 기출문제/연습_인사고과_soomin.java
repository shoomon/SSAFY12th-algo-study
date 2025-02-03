//정렬하면 [0]값은 신경쓰지 않아도 되고, [1] 값이 이전 값보다 작은 경우 인센티브를 받지 못함
import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1, cnt = 0, sum = 0;
        int[] max = {-1,-1};
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < scores.length; i++) list.add(new int[] {i,scores[i][0],scores[i][1]});
        
        Collections.sort(list, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] == o2[1] ? o1[2]-o2[2] : o2[1]-o1[1];
            }
        }
                   );
        
        for(int i = 1; i < list.size(); i++){
            if(list.get(i)[2] < list.get(i-1)[2]) {
                list.remove(i);
                i--;
            }
        }
        
        Collections.sort(list, (o1,o2) -> o2[1]+o2[2]-o1[1]-o1[2]);
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i)[0] == -1) continue;
            
            sum = list.get(i)[1]+list.get(i)[2];
            cnt=0;
            
            while(i < list.size() && list.get(i)[1]+list.get(i)[2] == sum){
                if(list.get(i)[0] == 0) return answer;
                cnt++;
                i++;
            }
            i--;
            answer += cnt;
        }
        
        // for(int[] i : list) System.out.println(i[0]+" "+i[1]+" "+i[2]);
        
        return -1;
    }
}