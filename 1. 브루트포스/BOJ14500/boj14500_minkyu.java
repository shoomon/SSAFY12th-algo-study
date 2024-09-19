import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
소요 메모리 : 37236KB
소요 시간 : 812ms

테트로미노가 덮고 있을 수 있는 칸의 최대 크기
*/

public class Main {
	static int height;
	static int width;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static boolean[][] visited;
	static int maxVal;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new int[height][width];
		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[height][width];
		maxVal = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				visited[i][j] = true;
				recur(i,j,1,map[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(maxVal);
	}
	
	public static void recur(int r, int c, int cnt, int val) {
		if (cnt == 4) {
			if (maxVal < val)
				maxVal = val;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int curR = r + dr[i];
			int curC = c + dc[i];
			if (0<=curR&&curR<height&&0<=curC&&curC<width&&!visited[curR][curC]) {
				visited[curR][curC] = true;
				recur(curR,curC,cnt+1, val+map[curR][curC]);
				recur(r,c,cnt+1,val+map[curR][curC]);
				visited[curR][curC] = false;
			}
		}
	}
}