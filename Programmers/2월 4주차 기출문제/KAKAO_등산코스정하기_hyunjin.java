package Programmers.PRO_2월_4주차;

import java.util.*;

public class KAKAO_등산코스정하기_hyunjin {

    static class Node implements Comparable<Node> {
        int V; // 방문할 지점
        int intensity; // 걸리는 시간

        public Node(int V, int intensity){
            this.V = V;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Node o){
            return this.intensity - o.intensity;
        }

    }

    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] adjList;
    static int[] dist;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        // 1. 인접 리스트 생성
        adjList = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            adjList[i] = new ArrayList<>();
        }

        // 2. 정점 및 간선 정보 저장
        for(int [] path : paths){
            int A = path[0]; // 출발 노드
            int B = path[1]; // 도착 노드
            int W = path[2]; // 가중치

            // 양방향
            adjList[A].add(new Node(B, W));
            adjList[B].add(new Node(A, W));
        }

        // 3. 출입구와 산봉우리 Set에 저장
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();

        for(int gate : gates){
            gateSet.add(gate);
        }

        for(int summit : summits){
            summitSet.add(summit);
        }

        // 4. 다익스트라 초기화
        dist = new int[n+1]; // 최소 거리 배열
        Arrays.fill(dist, INF);

        // 5. 모든 출입구에서 다익스트라 진행
        dijkstra(gates, summitSet);

        //System.out.println(Arrays.toString(dist));

        // 6. 최소 intensity를 가지는 가장 작은 산봉우리 찾기
        Arrays.sort(summits);
        int minIntensity = INF;
        int minSummit = -1;

        for(int summit : summits){
            if(dist[summit] < minIntensity){
                minIntensity = dist[summit];
                minSummit = summit;
            }
        }
        answer = new int[] {minSummit, minIntensity};

        return answer;
    }

    static void dijkstra(int[] gates, Set<Integer> summitSet){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[dist.length];

        // 모든 출입구에서 시작
        for(int gate : gates){
            dist[gate] = 0;
            pq.add(new Node(gate, 0));
        }

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int now = curr.V; // 현재 봉우리
            int intensity = curr.intensity; // 현재 지점까지 시간

            if(visited[now]) continue; // 이미 방문했다면 pass
            visited[now] = true;

            // 봉우리가 산 정상이라면 중단
            if(summitSet.contains(now)) continue;

            for(Node next : adjList[now]){
                int nextIntensity = Math.max(intensity, next.intensity);

                // 최소 intensity 갱신
                if(nextIntensity < dist[next.V]){
                    dist[next.V]= nextIntensity;
                    pq.add(new Node(next.V, nextIntensity));
                }
            }
        }
    }
}
