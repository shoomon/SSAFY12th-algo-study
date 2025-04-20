package PRO_3월_4주차;

class 연습_2차원동전뒤집기_hyunjin {
    static int n, m;
    static int [][] board; // 초기 상태에서 행을 뒤집은 상태 저장하는 배열
    static int[][] t; // 목표 상태
    static int answer = Integer.MAX_VALUE; // 뒤집는 최소 횟수
    
    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        
        board = new int[n][m];
        
        for(int i=0; i<n; i++){
            // 초기 상태 그대로 저장
            board[i] = beginning[i].clone(); // 깊은 복사
        }
        // 목표 상태
        t = target;
        
        dfs(0, 0);
        
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    // r : 뒤집는 행
    // cnt : 지금까지 뒤집은 횟수
    public void dfs(int r, int cnt){
        // 모든 행을 다 돌았다면,
        if(r == n){
            boolean flag = true;
            int tempCnt = cnt;
            
            for(int i=0; i<m; i++){
                int result = checkCol(i);
                if(result == -1){
                    flag = false;
                    break; // 아예 만들기 어려운 상태
                }
                // 뒤집어야 하는 열의 수 추가
                tempCnt += result;
            }
            
            if(flag){
                answer = Math.min(answer, tempCnt);
            }
            return;
        }
        
        // 1. 현재 행을 뒤집기
        filpRow(r);
        dfs(r + 1, cnt + 1); // 다음 dfs 진행
        filpRow(r); // 원상 복구 (백트래킹)
        
        // 2. 현재 행은 안 뒤집고, 다음 행 뒤집기
        dfs(r + 1, cnt); 
        
    }
    
    // r번째 행의 모든 값을 뒤집는다. 
    // 0 -> 1 , 1 -> 0
    public void filpRow(int r){
        for(int i=0; i<m; i++){
            board[r][i] = (board[r][i] + 1) % 2;
        }
    }
    
    // 열 확인하기 
    public int checkCol(int c){
        int check = 0;
        for(int i = 0; i<n; i++){
            if(t[i][c] == board[i][c]){
                check++;
            }
        }
        
        if(check == n) return 0; // 모든 값이 target과 board 같음 => 뒤집을 필요 없음
        else if(check == 0) return 1; // 모든 열이 반대 -> 모두 뒤집어야 함
        else return -1; // 섞여 있음 => 뒤집어도 만들 수 없음.
    }
}