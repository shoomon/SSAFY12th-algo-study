//25.03.01

import java.util.*;

class Solution {
    
    public int solution(int alp, int cop, int[][] problems) {
        int alMax=0, coMax=0;
        int[][] dp;
        
        for(int[] i : problems){
            alMax = Math.max(alMax, i[0]);
            coMax = Math.max(coMax, i[1]);
        }
        
        dp = new int[alMax+2][coMax+2];
        for(int i = 0; i < alMax+2; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        
        alp = Math.min(alp, alMax);
        cop = Math.min(cop, coMax);
        dp[alp][cop] = 0;
        
        for(int i = alp; i < alMax+1; i++){
            for(int j = cop; j < coMax+1; j++){
                
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                
                for(int[] p : problems){
                    if(i >= p[0] && j >= p[1]){
                        int al = Math.min(alMax, i+p[2]);
                        int co = Math.min(coMax, j+p[3]);
                        
                        dp[al][co] = Math.min(dp[al][co], dp[i][j]+p[4]);
                    }
                }
            }
        }
        
        return dp[alMax][coMax];
    }
}