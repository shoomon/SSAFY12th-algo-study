package 아이고;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ18352_mirim {
	static List<Integer>[] graph;  // 그래프를 저장할 인접 리스트
    static int[] distance;          // 각 도시까지의 거리 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기
        int n = sc.nextInt();  // 도시의 개수
        int m = sc.nextInt();  // 도로의 개수
        int k = sc.nextInt();  // 거리 K
        int x = sc.nextInt();  // 출발 도시 X
        
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 도로 정보 입력
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b); // 단방향 도로 추가
        }
        
        // 거리 배열 초기화
        distance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distance[i] = -1; // 초기화
        }
        distance[x] = 0; // 시작 도시의 거리는 0
        
        // BFS 수행
        bfs(x);

        // 결과 출력
        boolean found = false;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == k) {
                System.out.println(i);
                found = true; // 거리가 K인 도시가 존재함을 기록
            }
        }
        
        // 거리가 K인 도시가 없으면 -1 출력
        if (!found) {
            System.out.println(-1);
        }
        
        sc.close();
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll(); // 큐에서 노드 꺼내기

            // 인접한 노드 탐색
            for (int i = 0; i < graph[current].size(); i++) {
                int neighbor = graph[current].get(i); // 인접한 도시 가져오기
                if (distance[neighbor] == -1) { // 아직 방문하지 않은 도시
                    distance[neighbor] = distance[current] + 1; // 거리 업데이트
                    queue.add(neighbor); // 큐에 추가
                }
            }
        }
    }
}
