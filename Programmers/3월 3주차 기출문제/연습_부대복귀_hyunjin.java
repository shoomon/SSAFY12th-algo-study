import java.util.*;
import java.io.*;

class Solution {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < roads.length; i++) {
            int A = roads[i][0];
            int B = roads[i][1];

            adjList.get(A).add(B);
            adjList.get(B).add(A);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // destination에서 시작 지점까지 갈 수 있는 최단 거리 찾기
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        dist[destination] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 연결된 다른 지역 찾기
            for (int next : adjList.get(now)) {
                // 한 번 방문 시, 최단 거리 확정 (가중치가 모두 1이므로)
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    queue.add(next);
                }
            }

        }

        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
}