import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class robot{
	int x;
	int y;
	int dir;
	
	public robot(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
public class Main {
	static int n, m, cleancnt;
	static int arr[][];
	static int dr[] = {-1, 0, 1, 0};//북동남서
	static int dc[] = {0, 1, 0, -1};
	static boolean check[][];
	static Queue<robot> qu;
	public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        //first line
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        check = new boolean[n][m];
        //second line 바로 입력받아버리기
        qu = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        qu.offer(new robot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        //시작칸 항상 빈칸
        
        //배열 입력 1은 벽 0은 청소안된칸
        for(int i = 0; i <n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j <m; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        cleancnt = 0;
        foward();
        System.out.println(cleancnt);
	}
	static void foward() {
		
		while(!qu.isEmpty()) {
			robot curr = qu.poll();
			check[curr.x][curr.y]=true;//방문첵
			
			//청소가 안되어 있으면
			if(arr[curr.x][curr.y] == 0) {
				arr[curr.x][curr.y] = 2;
				cleancnt++;
			}
			boolean canMove = false;
		outer:for(int d= 0; d < 4; d++) {
				int nr = curr.x+dr[d];
				int nc = curr.y+dc[d];
				//경계조건 or 청소되지 않은 칸 존재 x
				if(nr >= 0 && nr < n && nc >= 0&& nc < m && arr[nr][nc] == 0 && !check[nr][nc]) {
					
					qu.offer(new robot(nr, nc, d));//갱신
					check[nr][nc] = true;
					canMove = true;
					break outer;
				}
			}
		if(!canMove) {
			int backDir = (curr.dir + 2) % 4;
            int backX = curr.x + dr[backDir];
            int backY = curr.y + dc[backDir];

            if (backX >= 0 && backX < n && backY >= 0 && backY < m && arr[backX][backY] != 1) {
                qu.offer(new robot(backX, backY, curr.dir)); // 후진
            } else {
                // 후진도 못하면 종료
                return;
            }
//		outer:for(int d= 0; d < 4; d++) {
//			int nr = curr.x+dr[d];
//			int nc = curr.y+dc[d];
//			//경계조건 or 청소되지 않은 칸 존재 시
//			if(nr < 0 ||  nr >= n || nc < 0 || nc >= m || arr[nr][nc] == 1) {
//					//반시계 90도 회전
//					if(curr.dir == 0)curr.dir = 4;
//					curr.dir -= 1;
//					
//					//북쪽인경우(후진이므로)
//					if(curr.dir == 0) {
//						nr = curr.x +1;
//						nc = curr.y;
//					}//방향 동쪽이면
//					else if(curr.dir == 1) {
//						nr = curr.x;
//						nc = curr.y -1;
//						//방향 남쪽이면
//					}else if(curr.dir == 2) {
//						nr = curr.x -1;
//						nc = curr.y;
//					}else {//서쪽이면
//						nr = curr.x;
//						nc = curr.y +1;
//					}
//					//청소 안되어 있으면
//					if(arr[nr][nc] == 0) {
//						qu.offer(new robot(nr, nc, curr.dir));
//						check[nr][nc] = true;
//						}
				//일단 방향은 유지
			
		}
		
	}
	
}
	
}
