import java.util.*;
import java.io.*;

/*
인구 이동

연합간 인구이동을 반복할때, 인구이동이 불가해지는 시점을 구하시오

메모리 : 298780 KB
시간 : 684ms

*/

public class Main {
	static int N,L,R;
	static int[][] countries;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static List<Union> unions;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 값들 입력
		// N = 땅의 크기, L = 인구 차이 최소, R = 인구 차이 최대
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 땅 크기 초기화
		countries = new int[N][N];
		
		// N개의 크기 만큼 각 나라의 인구수가 주어짐
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				countries[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// BFS를 위한 기본 요소 갖추기.
		boolean[][] visited;
		Queue<int[]> q = new ArrayDeque<>();
		
		int dayCnt = 0;
		
		while(true) {
			// 연합 집합 초기화
			unions = new ArrayList<>();
			// 방문 배열 초기화
			visited = new boolean[N][N];
			//BFS를 통해 연합을 구성할 것.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 방문하지 않은 곳만 지정해서 추가할 것.
					if (!visited[i][j]) {
						Union union = new Union();
						q.offer(new int[] {i,j,countries[i][j]});
						visited[i][j] = true;
						// 연합에 해당 국가 추가
						union.countries.add(new Country(countries[i][j],new int[] {i,j}));
						union.peopleCnt += countries[i][j];
						while(!q.isEmpty()) {
							int[] tmp = q.poll();
							for (int k = 0; k < 4; k++) {
								int r = tmp[0] + dr[k];
								int c = tmp[1] + dc[k];
								if (0<=r&&r<N&&0<=c&&c<N&&!visited[r][c]) {
									// 인원수 차이를 통한 같은 연합인지 파악할 것
									int diff = Math.abs(countries[r][c] - tmp[2]);
									if (diff<L||diff>R) continue;
									q.offer(new int[] {r,c,countries[r][c]});
									visited[r][c] = true;
									union.countries.add(new Country(countries[r][c],new int[] {r,c}));
									union.peopleCnt += countries[r][c];
								}
							}
						}
						// 2개 이상의 국가가 포함된 연합의 경우에만 추가.
						if (union.countries.size() > 1)
							unions.add(union);
					}
				}
			}
			
			//연합이 존재하는지에 대해서 확인이 필요하다.
			if (unions.size()==0)break;
			
			// 인구 이동 날짜 추가.
			dayCnt++;
			
			// 연합이 존재하는 경우, 해당 연합들은 전부 같은 값으로 변경할 수 있도록 진행.
			for (int i = 0; i < unions.size(); i++) {
				Union curUnion = unions.get(i);
				int avg = curUnion.peopleCnt / curUnion.countries.size();
				// 해당 국가들의 값들은 전부 avg값으로 변경
				for (int j = 0; j < curUnion.countries.size(); j++) {
					Country curCon = curUnion.countries.get(j);
					countries[curCon.pos[0]][curCon.pos[1]] = avg;
				}
			}
			
		}
		
		//며칠 동안 인구 이동이 발생하는지 출력
		System.out.println(dayCnt);
	}
	
	static class Country {
		int peopleCnt;
		int[] pos;
		public Country(int peopleCnt, int[] pos) {
			this.peopleCnt = peopleCnt;
			this.pos = pos;
		}
	}
	
	static class Union{
		int peopleCnt;
		List<Country> countries;
		public Union() {
			peopleCnt = 0;
			countries = new ArrayList<>();
		}
	}
}