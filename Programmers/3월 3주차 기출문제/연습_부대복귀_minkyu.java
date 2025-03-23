import java.util.*;

public class ReturnToArmy {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] roads = {{1,2},{2,3}};
        int[] sources = {2,3};
        int destination = 1;
        int[] ans = sol.solution(n,roads,sources,destination);
        System.out.println(ans);
    }

    public static class Solution {
        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            // 인접 리스트 생성
            List<Integer>[] adjList = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++)
                adjList[i] = new java.util.ArrayList<>();
            for (int[] road : roads) {
                adjList[road[0]].add(road[1]);
                adjList[road[1]].add(road[0]);
            }

            // 각 지역에서 최단 거리
            int[] distances = new int[n + 1];
            Arrays.fill(distances, -1);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(destination);
            distances[destination] = 0;

            while (!q.isEmpty()) {
                int tmp = q.poll();

                for (int i : adjList[tmp]) {
                    if (distances[i] == -1) {
                        distances[i] = distances[tmp] + 1;
                        q.offer(i);
                    }
                }
            }

            int[] answer = new int[sources.length];
            for (int i = 0; i < sources.length; i++)
                answer[i] = distances[sources[i]];

            return answer;
        }
    }
}
