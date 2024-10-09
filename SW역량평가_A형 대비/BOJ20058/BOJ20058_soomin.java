import java.util.*;
import java.io.*;
//L은 N의 약수여야 같은 크기로 쪼개기 가능
public class Main {
	static int[] dX = {0, 1, 0, -1};
	static int[] dY = {-1, 0, 1, 0};
	static int[][] map, copy;
	static int N, Q, size;
	static int[] cmd;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//변수 받아오기
		size = 1;
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			size *= 2;
		}
		map = new int[size][size];
		copy = new int[size][size];
		cmd = new int[Q];
		//맵 받아오기
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}
		//시뮬레이션
		for(int i : cmd) {
			int L = (int)Math.pow(2, i);
//			System.out.println(i+" "+L);
			if(i != 0) rotation(L);
//			prtMap();
			ice();
//			System.out.println("ice==========");
//			prtMap();
		}
//		prtMap();
		//답 구하기
		int max=0, cnt=0, sum=0;
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
//				debug(visited);
				if(map[i][j] < 1) continue;
				sum += map[i][j];
				if(!visited[i][j]) {
					//BFS
					visited[i][j] = true;
					cnt=1;
					q.offer(new int[]{i,j});
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						for(int k = 0; k < 4; k++) {
							int nY = cur[0]+dY[k];
							int nX = cur[1]+dX[k];
							if(-1 < nY && nY < size && -1 < nX && nX < size) {
								if(!visited[nY][nX] && map[nY][nX] > 0) {
									visited[nY][nX] = true;
									q.offer(new int[] {nY, nX});
									cnt++;
								}
							}
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}
		System.out.println(sum);
		System.out.println(max);
	}
	
	static void rotation(int L) {
		int iter = size/L, sY=0, sX=0;
		for(int i = 0; i < iter; i++) {
			for(int j = 0; j < iter; j++) {
				//쪼개진 각 배열의 시작점 좌상단 점
				sY = L*i;
				sX = L*j;
				//한 칸 돌리기
				for(int k = sY; k < sY+L; k++) {
					for(int l = sX; l < sX+L; l++) {
						//sY, sX+L-1 배열이 채워지기 시작하는 곳
						//행은 열이 증가할 때마다 증가 -> 0 <= l-sX < L
						//열은 행이 증가할 때마다 감소 -> 0<= k-sY < L
						copy[sY +l-sX][sX+L-1 -k+sY] = map[k][l];
					}
				}
			}
		}
		//돌린 배열 맵에 적용
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}
	
	static void ice() {
		int nY, nX, cnt;
		boolean[][] check = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(map[i][j] == 0) continue;
				cnt=0;
				for(int k = 0; k < 4; k++) {
					nY = i+dY[k];
					nX = j+dX[k];
					if(-1 < nY && nY < size && -1 < nX && nX < size) {
						if(map[nY][nX] != 0) cnt++;
					}
				}
				//바로 녹이지 말고 체크해뒀다가 한번에 녹여야됨.
				if(cnt > 2) continue;
				check[i][j] = true;
			}
		}
		//녹이기
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(check[i][j]) map[i][j]--;
			}
		}
	}
	
	static void prtMap() {
		System.out.println("map");
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("=====================");
	}

	static void debug(boolean[][] arr) {
		System.out.println("visited");
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("=====================");
	}
}
