class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left+1)];
        
        for(int i = (int)(left/n); i <= (int)(right/n); i++){
            for(int j = (i == (int)(left/n) ? (int)(left%n) : 0); j <= (i == (int)(right/n) ? (int)(right%n) : n); j++) answer[(int)(i*n+j-left)] = Math.max(i,j)+1;
        }
        
        return answer;
    }
}