//25.01.19
//설계: 5분
//구현: 15분
//게임이 끝났는지 -> DFS, O가 3칸 연속되었는데 O == X 인 경우, X가 3칸 연속인데 O > X인 경우
//-1 < O - X < 2
class Solution {
    static int[] dY = {-1,0,1,0};
    static int[] dX = {0,1,0,-1};
    static int end1, end2;
    public int solution(String[] board) {
        int answer = -1, cnt1=0, cnt2=0, end1=0, end2=0;
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i].charAt(j) == 'O'){
                    cnt1++;
                }else if(board[i].charAt(j) == 'X'){
                    cnt2++;
                }
            }
            //가로
            if(board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)){
               if(board[i].charAt(0) == 'O'){
                   end1++;
               }else if(board[i].charAt(0) == 'X'){
                   end2++;
               }
            }
            //세로
            if(board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)){
                if(board[0].charAt(i) == 'O'){
                   end1++;
               }else if(board[0].charAt(i) == 'X'){
                   end2++;
               }
            }
        }
        //대각선
        if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
            if(board[0].charAt(0) == 'O'){
                   end1++;
               }else if(board[0].charAt(0) == 'X'){
                   end2++;
               }
        }else if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
            if(board[0].charAt(2) == 'O'){
                   end1++;
               }else if(board[0].charAt(2) == 'X'){
                   end2++;
               }
        }
        System.out.println(end1+" "+end2);
        
        if((end1 == 1 && cnt1 == cnt2) || (end2 == 1 && cnt1 > cnt2) || !(-1 < cnt1-cnt2 && cnt1-cnt2 < 2)){
            answer = 0;
        }else {
            answer = 1;
        }
        return answer;
    }
}