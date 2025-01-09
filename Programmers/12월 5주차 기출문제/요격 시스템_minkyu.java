import java.util.*;
public class missile_system {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
		int ans = sol.solution(targets);
		System.out.println(ans);
	}

	static class Solution {
	    public int solution(int[][] targets) {
	    	// 정렬 진행
	        Arrays.sort(targets, (o1,o2) -> {
	            if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
	            return Integer.compare(o1[1],o2[1]);
	        });

	        int missileCnt = 0;
	        int pos = 0;
	        
	        for(int i = 0; i < targets.length; i++){
	            if(targets[i][0] >= pos){
	                missileCnt++;
	                pos = targets[i][1];
	            }
	        }
	        return missileCnt;

	    }
	}
}
