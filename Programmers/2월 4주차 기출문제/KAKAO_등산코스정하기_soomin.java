//25.03.02
//너무 어려워요
import java.util.*;

class Solution {
    static List<Info>[] adj;
    static int[] intensity;
    static int[] nodeType; // 출입구(-1) 및 봉우리(-2) 표시

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adj = new List[n + 1];
        intensity = new int[n + 1];
        nodeType = new int[n + 1];

        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 출입구 및 봉우리 표시
        for (int gate : gates) nodeType[gate] = -1;
        for (int summit : summits) nodeType[summit] = -2;

        // 그래프 구성 (출입구 → 봉우리 방향으로만 연결)
        for (int[] path : paths) {
            int s = path[0], e = path[1], w = path[2];

            // 출입구에서 다른 곳으로만 연결
            if (nodeType[s] == -1 || nodeType[e] == -2) {
                adj[s].add(new Info(e, w));
            } else if (nodeType[e] == -1 || nodeType[s] == -2) {
                adj[e].add(new Info(s, w));
            } else {
                adj[s].add(new Info(e, w));
                adj[e].add(new Info(s, w));
            }
        }

        // 다익스트라 실행
        dijkstra(n, gates);

        // 가장 작은 intensity를 가지는 봉우리 찾기
        Arrays.sort(summits);
        int minSummit = -1, minIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = intensity[summit];
            }
        }

        return new int[]{minSummit, minIntensity};
    }

    static void dijkstra(int n, int[] gates) {
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.wei));

        // 출입구에서 출발
        for (int gate : gates) {
            pq.offer(new Info(gate, 0));
            intensity[gate] = 0;
        }

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            // 현재 경로가 이미 최소 intensity보다 크다면 탐색 중단
            if (intensity[cur.idx] < cur.wei) continue;

            for (Info next : adj[cur.idx]) {
                if (intensity[next.idx] > Math.max(intensity[cur.idx], next.wei)) {
                    intensity[next.idx] = Math.max(intensity[cur.idx], next.wei);
                    pq.offer(new Info(next.idx, intensity[next.idx]));
                }
            }
        }
    }

    static class Info {
        int idx, wei;

        public Info(int idx, int wei) {
            this.idx = idx;
            this.wei = wei;
        }
    }
}

