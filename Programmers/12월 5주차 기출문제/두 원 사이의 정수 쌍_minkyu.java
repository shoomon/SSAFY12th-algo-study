
public class two_circle_num {
	public static void main(String[] args) {
		
	}
	
	static class Solution {
	    public long solution(int r1, int r2) {
	        long cnt = 0;
	        for(int i=0;i<r2;i++){
	            if(i<r1) cnt += Math.floor(Math.sqrt(Math.pow(r2,2)-Math.pow(i,2))) - Math.ceil(Math.sqrt(Math.pow(r1,2)-Math.pow(i,2)))+1;
	            else cnt += Math.floor(Math.sqrt(Math.pow(r2,2)-Math.pow(i,2)));
	        }
	        return cnt * 4;
	    }
	}
}
