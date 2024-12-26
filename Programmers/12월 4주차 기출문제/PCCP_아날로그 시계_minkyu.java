import java.util.*;
import java.io.*;

public class PCCP_analog_clock {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int ans = sol.solution(0,5,30,0,7,0);
		System.out.println(ans);
	}
	
	public static class Solution {
	    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
	    	// 시작시간(단위 : 초), 종료시간(단위 : 초)
	        int start = h1 * 3600 + m1 * 60 + s1;
	        int end = h2 * 3600 + m2 * 60 + s2;
	        
	        int answer = 0;
	        // 시작을 동일한 위치에서 시작하는 경우 값을 하나 추가할 것.
	        double h = (start/120.0)%360;
            double m = (start/10.0)%360;
            double s = (start*6)%360;
            if (h == s || m == s) answer++;
	        
	        // 종료시간 될때까지 1초씩 증가
	        while(start<end){
	        	// 시작할때의 시침, 분침, 초침 각도 계산
	            double hAngle = (start/120.0)%360; 
	            double mAngle = (start/10.0)%360;
	            double sAngle = (start*6)%360;
	            
	            // 1초 후의 시침, 분침, 초침 각도 계산
	            double hAngleNext = ((start+1)/120.0)%360;
	            double mAngleNext = ((start+1)/10.0)%360;
	            double sAngleNext = ((start+1)*6)%360;
	            
	            // 각도가 0도보다 작으면 360 더해주기
	            if(hAngleNext<=0) hAngleNext+=360;
	            if(mAngleNext<=0) mAngleNext+=360;
	            if(sAngleNext<=0) sAngleNext+=360;
	            
	            // 더해졌는지 여부 확인용
	            boolean isAdded = false;
	            if(sAngle<hAngle && sAngleNext>=hAngleNext) {
	            	answer++;
	            	isAdded = true;
	            }
	            if(sAngle<mAngle && sAngleNext>=mAngleNext) {
	            	answer++;
	            	isAdded = true;
	            }
	            // 더해지고, 해당 값이 더해진 경우에, 시와 분이 동일했다면 하나 빼주기.
	            if(hAngleNext==mAngleNext && isAdded) answer--;
	            
	            // 1초 증가
	            start++;
	        }
	        
	        return answer;
	    }
	}
}
