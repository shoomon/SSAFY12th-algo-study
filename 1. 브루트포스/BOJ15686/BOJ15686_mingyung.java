import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686_mingyung {
	// G5_치킨 배달
	// 메모리 : 15,572 kb
	// 시간 : 156 ms
	
	// 크기 N*N 도시, 빈칸0, 집1, 치킨집2
	// 치킨 거리 = |r1-r2| + |c1+c2|
	// 도치의 치킨거리는 모든 집의 치킨 거리의 합
	// 치킨 집 M개 남겼을 때 치킨 거리의 최소값 찾기
	
	static int N, M, min;
	static List<int[]> house, chickens;
	static int[][] combChic;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());
		// 집과 치킨집 배열리스트에 넣기
		house = new ArrayList<int[]>();
		chickens = new ArrayList<int[]>();
		for (int r=0; r<N; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				int tmp = Integer.parseInt(st2.nextToken());
				if (tmp == 1) {
					house.add(new int[] {r, c});
				} else if (tmp==2) {
					chickens.add(new int[] {r, c});					
				}
			}
		} // 입력받기 완료
		
		// 필요한 변수 초기화
		combChic = new int[M][2];
		min = Integer.MAX_VALUE;
		comb(0, 0);
		
		//출력하기
		System.out.println(min);
	} // main
	
	// 치킨집 조합 찾기
	static void comb(int idx, int sidx) {
		// 치킨집 모두 뽑혔을 때
		if (sidx==M) {
			// 치킨 거리 구하기
			findChicDis();
			return;
		}
		
		// 뽑는 인덱스 배열 벗어나면 처리
		if (idx>=chickens.size()) return;
		
		// 조합하기
		combChic[sidx] = chickens.get(idx);
		comb(idx+1, sidx+1);
		comb(idx+1, sidx);
	}
	
	// 치킨 거리 구하기
	static void findChicDis() {
		// 치킨 거리 더한 값
		int sum = 0;
		// 치킨집만큼 돌면서 제일 가까운 치킨집 값 찾기
		int idx = 0;
		while (idx<house.size()) {
			int r1 = house.get(idx)[0];
			int c1 = house.get(idx)[1];
			// 해당 집의 치킨 거리
			int chicDis = Integer.MAX_VALUE;
			// 치킨집 거리 구하기
			for (int i=0; i<M; i++) {
				int dis = 0;
				int r2 = combChic[i][0];
				int c2 = combChic[i][1];
				dis = Math.abs(r1-r2) + Math.abs(c1-c2);
				if (chicDis > dis)
					chicDis = dis;
			}
			
			// 치킨거리 더해주기
			sum += chicDis;
			
			// 인덱스 늘려주기
			idx++;
			
			// 만약 min 넘어가면 그냥 그만해
			if (sum>min) break;
		} // 각 집의 치킨거리 구하기
		if (min > sum)
			min = sum;
	}
}