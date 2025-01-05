//24.12.29
//설계: 분
//구현: 60+60분
//시뮬레이션, DFS 완탐 -> 16*16 = 2^8
class Solution {
    static class Car{
        int sY, sX, eY, eX;
    }
    
    static int N, M, answer;
    static int[] dY = {-1,0,1,0};
    static int[] dX = {0,1,0,-1};
    static int[][] map1, map2;
    static Car red, blue;
    public int solution(int[][] maze) {
        answer = Integer.MAX_VALUE;
        N = maze.length;
        M = maze[0].length;
        red = new Car();
        blue = new Car();
        map1 = new int[N][M];
        map2 = new int[N][M];
        
        //맵 탐색하며 수레 위치, 도착점 저장
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maze[i][j] == 1){
                    red.sY = i;
                    red.sX = j;
                }else if(maze[i][j] == 2){
                    blue.sY = i;
                    blue.sX = j;
                }else if(maze[i][j] == 3){
                    red.eY = i;
                    red.eX = j;
                }else if(maze[i][j] == 4){
                    blue.eY = i;
                    blue.eX = j;
                }else if(maze[i][j] == 5){
                    map1[i][j] = Integer.MAX_VALUE;
                    map2[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        setRedPath(1, red.sY, red.sX);
        
        return answer == Integer.MAX_VALUE ? 0 : answer-1;
    }
    
    static void setRedPath(int cnt, int curY, int curX){
        map1[curY][curX] = cnt;
        //빨간 수레가 도착했으면 파란 수레 경로 설정
        if(curY == red.eY && curX == red.eX){
            setBluePath(1, blue.sY, blue.sX);
            map1[curY][curX] = 0;
            return;
        }
        
        for(int i = 0; i < 4; i++){
            int nY = curY+dY[i];
            int nX = curX+dX[i];
            
            if(check(nY,nX) && map1[nY][nX] == 0) setRedPath(cnt+1, nY, nX);
        }
        map1[curY][curX] = 0;
    }
    
    static void setBluePath(int cnt, int curY, int curX){
        map2[curY][curX] = cnt;
        //파란 수레가 도착한 경우
        if(curY == blue.eY && curX == blue.eX){
            //어떤 수레가 먼저 도착했는지 확인
            //먼저 도착한 수레의 도착점이 다른 수레의 미래 경로상에 있다면 return
            //blue 먼저 도착
            if(map2[blue.eY][blue.eX] < map1[red.eY][red.eX]){
                //blue의 도착점을 지나지 않거나(0), blue가 도착하기 전에 지나감
                if(map1[blue.eY][blue.eX] < map2[blue.eY][blue.eX]){
                    answer = Math.min(answer, map1[red.eY][red.eX]);
                }
                map2[curY][curX] = 0;
                return;
            //red 먼저 도착
            }else if(map2[blue.eY][blue.eX] > map1[red.eY][red.eX]){
                //red의 도착점을 지나지 않거나, red가 도착하기 전에 지나감
                if(map2[red.eY][red.eX] < map1[red.eY][red.eX]){
                    answer = Math.min(answer, map2[blue.eY][blue.eX]);
                }
                map2[curY][curX] = 0;
                return;
            }else{
                answer = Math.min(answer, map2[blue.eY][blue.eX]);
                map2[curY][curX] = 0;
                return;
            }
        }
        //파란 수레는 같은 시점에 빨간 수레와 경로가 겹치면 안된다. -> map1[nY][nX] != cnt+1
        //수레는 서로 위치를 바꾸며 이동 불가. 다음 이동하려는 방향에 수레가 있고, 다른 수레의 다음 이동 방향이 현재 내 위치-> !(map1[nY][nX] == cnt && map1[curY][curX] == cnt+1)
        for(int i = 0; i < 4; i++){
            int nY = curY+dY[i];
            int nX = curX+dX[i];
            
            if(check(nY, nX) && map2[nY][nX] == 0 && map1[nY][nX] != cnt+1 && !(map1[nY][nX] == cnt && map1[curY][curX] == cnt+1)) setBluePath(cnt+1, nY, nX);
        }
        map2[curY][curX] = 0;
    }
    
    static boolean check(int y, int x){
        if(-1 < y && y < N && -1 < x && x < M) return true;
        return false;
    }
}