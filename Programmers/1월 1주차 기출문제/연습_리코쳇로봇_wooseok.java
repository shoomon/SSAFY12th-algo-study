package codingTest;

import java.util.*;

public class 연습_리코쳇로봇_wooseok {
    public int solution(String[] board) {
        int answer = Integer.MAX_VALUE;
        boolean[][] visit = new boolean[board.length][board[0].length()];
        Queue<int[]> que = new LinkedList<>();

        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[i].length(); j++){
                if(board[i].charAt(j) == 'R') {
                    que.add(new int[] {i,j,0});
                    visit[i][j] = true;
                    while(!que.isEmpty()){
                        int[] tmp = que.poll();

                        if(board[tmp[0]].charAt(tmp[1]) == 'G') {
                            answer = Math.min(answer, tmp[2]);
                        }

                        for(int k = 0; k<4; k++){
                            int[] XY = move(k, tmp[0] , tmp[1], tmp[2], board);
                            int x = XY[0];
                            int y = XY[1];

                            if(!visit[x][y]){
                                que.add(XY);
                                visit[x][y] = true;
                            }
                        }
                    }
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // 상하좌우 장애물이나 맨 끝에 부딪힐때까지 미끄러지기
    public int[] move(int k, int x, int y, int cnt, String[] board){
        int[] result = new int[3];
        if(k == 0){
            for(int i = x; i>=0; i--){
                if(board[i].charAt(y) == 'D'){
                    return new int[]{i+1, y, cnt+1};
                }
            }
            result[0] = 0;
            result[1] = y;
            result[2] = cnt+1;
        }else if(k == 1){
            for(int i = x; i<board.length; i++){
                if(board[i].charAt(y) == 'D'){
                    return new int[]{i-1, y, cnt+1};
                }
            }
            result[0] = board.length - 1;
            result[1] = y;
            result[2] = cnt+1;
        }else if(k == 2){
            for(int i = y; i<board[0].length(); i++){
                if(board[x].charAt(i) == 'D'){
                    return new int[]{x, i-1, cnt+1};
                }
            }
            result[0] = x;
            result[1] = board[0].length()-1;
            result[2] = cnt+1;
        }else if(k == 3){
            for(int i = y; i>=0; i--){
                if(board[x].charAt(i) == 'D'){
                    return new int[]{x, i+1, cnt+1};
                }
            }
            result[0] = x;
            result[1] = 0;
            result[2] = cnt+1;
        }

        return result;
    }
}
