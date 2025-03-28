class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        for(int[] cmd : skill){
            if(cmd[0] == 1) cmd[5] = -cmd[5];
            sum[cmd[1]][cmd[2]] += cmd[5];
            sum[cmd[1]][cmd[4]+1] -= cmd[5];
            sum[cmd[3]+1][cmd[2]] -= cmd[5];
            sum[cmd[3]+1][cmd[4]+1] += cmd[5];
        }
        
        for(int i = 0; i < board.length+1; i++){
            for(int j = 1; j < board[0].length+1; j++){
                sum[i][j] += sum[i][j-1];
            }
        }
        
        for(int j = 0; j < board[0].length+1; j++){
            for(int i = 1; i < board.length+1; i++){
                sum[i][j] += sum[i-1][j];
            }
        }
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] += sum[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}