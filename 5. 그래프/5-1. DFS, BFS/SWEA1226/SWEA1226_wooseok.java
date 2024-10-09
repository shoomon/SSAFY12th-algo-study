import java.util.Scanner;
  
public class Solution {
      
    static int[][] maze; //미로 배열
    static boolean[][] check; //체크 배열
    //델타
    static int[] di =  {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean ans; //출발지부터 도달할 수 있는지 없는지
      
      
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
          
          
        for(int tc=1; tc<=10; tc++) {
            int T = sc.nextInt(); //테스트케이스
              
            //나중에 범위를 따로 지정하지 않기 위해 배열 크기를 2 크게 만들어서 벽 생성
            maze = new int[18][18]; 
            check = new boolean[18][18]; 
              
            int startX = 0; //출발 x 좌표
            int startY = 0; //출발 y 좌표
              
            for(int i=0; i<18; i++) { 
                String str; //문자열 입력 받자
                if(i>=1 && i<17) {
                    str = sc.next(); 
                } else str = "1111111111111111"; //상하 벽
                  
                for(int j=0; j<18; j++) {
                    if(j==0 || j==17) { //좌우 벽
                        maze[i][j] = 1;
                        check[i][j] =true; 
                    } else {
                        maze[i][j] = str.charAt(j-1) - '0'; //int 형으로 변환해서 미로에 넣어주기
                          
                        if(maze[i][j] == 1) {
                            check[i][j] = true; //1로 입력되면 벽이니까 true로 변경
                        }
                          
                        if(maze[i][j] == 2) { //시작점 지정
                            startX = i;
                            startY = j;
                        }
                    }
                }
            }
              
            ans = false;
              
            dfs(startX, startY);
              
            if(ans) System.out.println("#"+tc+" "+1);
            else System.out.println("#"+tc+" "+0);
        }
  
    }
  
    private static void dfs(int x, int y) {
          
        if(check[x][y]==true) return; //벽을 만나면 끝
          
        if(maze[x][y]==3) { //도착점을 만나면
            ans = true; //정답 true로 바꿔주기
            return;
        } else {
              
            check[x][y] = true; //방문지점은 체크하고
              
            for(int d=0; d<4; d++) { //상하좌우 돌면서
                int ni = x+di[d];
                int nj = y+dj[d];
                  
                dfs(ni, nj); 
            }
        }
    }
  
}