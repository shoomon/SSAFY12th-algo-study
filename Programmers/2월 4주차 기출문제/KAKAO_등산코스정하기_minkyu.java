import java.util.*;
import java.io.*;

public class SelectHikingCourse {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1,3};
        int[] summits = {5};
        int[] ans = sol.solution(6,paths,gates,summits);
        System.out.println(ans);
    }

    public static class Solution {
        static class Edge {
            int to, weight;
            Edge(int to, int weight) {
                this.to = to;
                this.weight = weight;
            }
        }

        static class State implements Comparable<State> {
            int node, intensity;
            State(int node, int intensity) {
                this.node = node;
                this.intensity = intensity;
            }

            @Override
            public int compareTo(State other) {
                return Integer.compare(this.intensity, other.intensity);
            }
        }

        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            // 그래프 생성
            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] path : paths) {
                int i = path[0], j = path[1], w = path[2];
                graph.get(i).add(new Edge(j, w));
                graph.get(j).add(new Edge(i, w));
            }

            // gates와 summits를 Set으로 변환
            Set<Integer> gateSet = new HashSet<>();
            for (int gate : gates) gateSet.add(gate);
            Set<Integer> summitSet = new HashSet<>();
            for (int summit : summits) summitSet.add(summit);

            // 다익스트라로 최소 intensity 계산
            int[] intensities = new int[n + 1];
            Arrays.fill(intensities, Integer.MAX_VALUE);
            PriorityQueue<State> pq = new PriorityQueue<>();
            int[] visitedFromGate = new int[n + 1]; // 출입구에서 방문한 지점 추적

            // 모든 출입구를 시작점으로 초기화
            for (int gate : gates) {
                intensities[gate] = 0;
                pq.offer(new State(gate, 0));
                visitedFromGate[gate] = gate; // 해당 출입구에서 시작
            }

            while (!pq.isEmpty()) {
                State curr = pq.poll();
                int node = curr.node;
                int currIntensity = curr.intensity;

                if (intensities[node] < currIntensity) continue;

                // 산봉우리에 도달하면 탐색 중단 (더 탐색할 필요 없음)
                if (summitSet.contains(node)) continue;

                for (Edge edge : graph.get(node)) {
                    int next = edge.to;
                    int weight = edge.weight;

                    // 중간에 다른 출입구 방문 금지 (시작 출입구 제외)
                    if (gateSet.contains(next) && visitedFromGate[node] != next) continue;

                    int newIntensity = Math.max(currIntensity, weight);
                    if (newIntensity < intensities[next]) {
                        intensities[next] = newIntensity;
                        visitedFromGate[next] = visitedFromGate[node]; // 출입구 정보 전파
                        pq.offer(new State(next, newIntensity));
                    }
                }
            }

            // 최소 intensity와 산봉우리 찾기
            int minIntensity = Integer.MAX_VALUE;
            int bestSummit = Integer.MAX_VALUE;

            for (int summit : summits) {
                if (intensities[summit] != Integer.MAX_VALUE) {
                    if (intensities[summit] < minIntensity) {
                        minIntensity = intensities[summit];
                        bestSummit = summit;
                    } else if (intensities[summit] == minIntensity && summit < bestSummit) {
                        bestSummit = summit;
                    }
                }
            }

            return (minIntensity == Integer.MAX_VALUE) ? new int[]{-1, -1} : new int[]{bestSummit, minIntensity};
        }
    }
}
