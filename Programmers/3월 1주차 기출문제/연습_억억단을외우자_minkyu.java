import java.util.*;
import java.io.*;

public class HundredMillions {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] starts = {1,3,7};
		int[] ans = sol.solution(8, null);
		System.out.println(Arrays.toString(ans));
	}
	
	public static class Solution {
	    public int[] solution(int e, int[] starts) {
	        // 1. 각 숫자가 등장하는 횟수를 저장하는 배열
	        int[] count = new int[e + 1];
	        
	        // 2. 약수 개수 계산 (억억단에서 등장 횟수)
	        for (int i = 1; i <= e; i++) {
	            for (int j = i; j <= e; j += i) {
	                count[j]++;
	            }
	        }
	        
	        // 3. 각 숫자까지의 최대 등장 횟수와 해당 숫자를 저장
	        int[] maxCount = new int[e + 1];
	        int[] answerNum = new int[e + 1];
	        
	        int currentMax = 0;
	        int currentNum = 0;
	        
	        // 뒤에서부터 순회하며 최대값 갱신
	        for (int i = e; i >= 1; i--) {
	            if (count[i] >= currentMax) {
	                currentMax = count[i];
	                currentNum = i;
	            }
	            maxCount[i] = currentMax;
	            answerNum[i] = currentNum;
	        }
	        
	        // 4. 각 start에 대해 정답 찾기
	        int[] answer = new int[starts.length];
	        for (int i = 0; i < starts.length; i++) {
	            answer[i] = answerNum[starts[i]];
	        }
	        
	        return answer;
	    }
	}
}
