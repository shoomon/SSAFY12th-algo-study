import java.io.*;
import java.util.*;

public class Main {
    // N: 도시의 크기, M: 선택할 치킨집 수
    static int N, M, arr[][];
    // 방문 여부 배열
    static boolean visit[];
    // 최소 치킨 거리 합을 저장할 변수
    static int result = Integer.MAX_VALUE;
    // 집과 치킨집의 위치를 저장할 리스트
    static List<int[]> home = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시의 크기(N)와 선택할 치킨집 수(M)를 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        
        // 도시의 정보 입력 받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) {
                    // 위치가 집인 경우
                    home.add(new int[]{i, j});
                }
                else if(arr[i][j] == 2) {
                    // 위치가 치킨집인 경우
                    chicken.add(new int[]{i, j});
                }
            }
        }
        
        // 치킨집 방문 여부 배열 초기화
        visit = new boolean[chicken.size()];
        // 조합을 통해 M개의 치킨집 선택
        back(0, 0);
        // 결과 출력
        System.out.println(result);
    }
    
    // M개의 치킨집을 선택하는 백트래킹 메소드
    static void back(int start, int count) {
        // M개의 치킨집을 선택한 경우
        if(count == M) {
            int sum = 0; // 현재 조합에 대한 치킨 거리 합
            // 모든 집에 대해 최소 치킨 거리를 계산
            for(int[] h : home) {
                int min = Integer.MAX_VALUE;
                // 선택된 치킨집과의 거리 계산
                for(int c = 0; c < chicken.size(); c++) {
                    if(visit[c]) {
                        int dist = Math.abs(h[0] - chicken.get(c)[0]) + Math.abs(h[1] - chicken.get(c)[1]);
                        min = Math.min(min, dist);
                    }
                }
                sum += min; // 각 집의 최소 치킨 거리를 합산
            }
            // 최소 치킨 거리 합 업데이트
            result = Math.min(result, sum);
            return;
        }
        
        // 치킨집 조합 선택을 위한 백트래킹
        for(int i = start; i < chicken.size(); i++) {
            visit[i] = true;      // i번째 치킨집을 선택
            back(i + 1, count + 1); // 다음 치킨집 선택
            visit[i] = false;     // 선택 해제
        }
    }
}
