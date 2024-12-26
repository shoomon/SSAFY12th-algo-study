import java.util.*;
import java.io.*;

public class PCCP_oil_dig {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};	
		int ans = sol.solution(land);
		System.out.println(ans);
	}
	
	public static class Solution{
		int[] dr = {1,-1,0,0};
		int[] dc = {0,0,1,-1};
		
		public int solution(int[][] land) {
	        int rowCnt = land.length;
	        int colCnt = land[0].length;
	        
	        Map<Integer, Integer> info = new HashMap<>();
	        
	        Queue<int[]> q = new ArrayDeque<>();
	        for (int i = 0; i < rowCnt; i++) {
	        	for (int j = 0; j < colCnt; j++) {
	        		// 새로운 덩어리가 나온 경우.
	        		if (land[i][j] == 1) {
	        			int curSize = 0;
	        			Set<Integer> columns = new HashSet<>();
	        			land[i][j] = 0;
	        			q.offer(new int[] {i,j});
	        			curSize++;
	        			columns.add(j);
	        			while(!q.isEmpty()) {
	        				int[] tmp = q.poll();
	        				for (int k = 0; k < 4; k++) {
	        					int r = tmp[0] + dr[k];
	        					int c = tmp[1] + dc[k];
	        					if (0<=r&&r<rowCnt&&0<=c&&c<colCnt&&land[r][c]==1) {
	        						land[r][c] = 0;
	        						q.offer(new int[] {r,c});
	        						curSize++;
	        						columns.add(c);
	        					}
	        				}
	        			}
	        			
	        			// 존재하는 열마다 추가해줄 것.
	        			for (int k : columns) {
	        				if (info.containsKey(k))
	        					info.put(k, info.get(k) + curSize); 
	        				else
	        					info.put(k, curSize);
	        			}
	        		}
	        	}
	        }
	        
	        // 각 열별로 최대 개수를 가져올 것
	        int answer = 0;
	        for (int k : info.keySet())
	        	answer = Math.max(answer, info.get(k));
	        
	        return answer;
	    }
	}
}
