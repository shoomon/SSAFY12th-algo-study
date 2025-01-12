public class mining{
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] picks = {1,3,2};
		String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
		int ans = sol.solution(picks, minerals);
		System.out.println(ans);
	}
	
	static class Solution {
		static int min = Integer.MAX_VALUE;
		static int[] pick;
		static String[] mineral;
	    public int solution(int[] picks, String[] minerals) {
	        pick = picks.clone();
	        mineral = minerals.clone();
	        recur(0, 0);
	        return min;
	    }
	    
	    public void recur(int idx, int fatig) {
	    	// 이미 전에 나온 최솟값보다 더 많이 나온 경우는 굳이 생각할 필요가 없음
	    	if (fatig > min) return;
	    	
	    	if (idx == mineral.length) {
	    		min = Math.min(fatig, min);
	    		return;
	    	}
	    	
	    	boolean hasWorked = false;
	    	// 다이아 곡괭이로 작업하는 경우
	    	if (pick[0] > 0) {
	    		int curFatig = fatig;
	    		for (int i = 0; i < 5; i++) {
	    			if (idx + i == mineral.length) {
	    				min = Math.min(curFatig, min);
	    				return;
	    			}
	    			curFatig++;
	    		}
	    		pick[0]--;
	    		recur(idx+5, curFatig);
	    		pick[0]++;
	    		hasWorked = true;
	    	}
	    	// 철 곡괭이로 작업하는 경우
	    	if (pick[1] > 0) {
	    		int curFatig = fatig;
	    		for (int i = 0; i < 5; i++) {
	    			if (idx + i == mineral.length) {
	    				min = Math.min(curFatig, min);
	    				return;
	    			}
	    			if (mineral[idx+i] == "diamond") curFatig += 5;
	    			else curFatig++;
	    		}
	    		pick[1]--;
	    		recur(idx+5, curFatig);
	    		pick[1]++;
	    		hasWorked = true;
	    	}
	    	// 돌 곡괭이로 작업하는 경우
	    	if (pick[2] > 0) {
	    		int curFatig = fatig;
	    		for (int i = 0; i < 5; i++) {
	    			if (idx + i == mineral.length) {
	    				min = Math.min(curFatig, min);
	    				return;
	    			}
	    			if (mineral[idx+i] == "diamond") curFatig += 25;
	    			else if (mineral[idx+i] == "iron") curFatig += 5;
	    			else curFatig++;
	    		}
	    		pick[2]--;
	    		recur(idx+5, curFatig);
	    		pick[2]++;
	    		hasWorked = true;
	    	}
	    	
	    	if (!hasWorked) {
	    		min = Math.min(fatig, min);
	    		return;
	    	}
	    }
	}
}