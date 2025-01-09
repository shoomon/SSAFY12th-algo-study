
public class subsequence_sum {
	public static void main(String[] args) {
		
	}
	
	static class Solution {
	    public int[] solution(int[] sequence, long k) {
	        int min = Integer.MAX_VALUE;
	        int left = 0, right = 0, cur = 0;
	        int ansStart = -1;
	        int ansEnd = -1; 
	        for(int i = 0; i < sequence.length; i++){
	            right++;
	            cur += sequence[i];
	            if(cur < k) continue;
	            if(cur == k){
	                if(min > right - left + 1){
	                    min = right - left + 1;
	                    ansStart = left;
	                    ansEnd = right - 1;
	                }
	            }
	            else{
	                while(cur > k){
	                    cur -= sequence[left++];
	                }
	                if(cur == k){
	                    if(min > right - left + 1){
	                        min = right - left + 1;
	                        ansStart = left;
	                        ansEnd = right - 1;
	                    }
	                } 
	            }
	        }
	        return new int []{ansStart, ansEnd};
	    }   
	}
}
