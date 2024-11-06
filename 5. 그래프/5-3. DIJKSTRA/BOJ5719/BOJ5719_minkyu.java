import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
거의 최단 경로

최단 경로를 제외하고 가장 빠른 경로의 최단 거리를 구하라

메모리 : 63228 KB
시간 : 604 ms
*/

public class Main {
    // 경로 가중치 저장
    public static int boards[][] = new int[501][501];
    // weights[i] => i 까지 가는 최소 가중치
    public static int weights[] = new int[501];
    // nodes[i] => i 까지 최소 가중치로 왔을 때 직전 노드들
    public static Set<Integer> nodes[] = new Set[501];
    // 삭제 시 해당 노드 이미 지웠는지 확인
    public static boolean check[] = new boolean[501];

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        while(N != 0 && M != 0){

            st = new StringTokenizer(bf.readLine());

            int S = Integer.parseInt(st.nextToken());

            int D = Integer.parseInt(st.nextToken());
            
            init();

            for(int i=0;i<M;i++){
                st = new StringTokenizer(bf.readLine());

                int U = Integer.parseInt(st.nextToken());

                int V = Integer.parseInt(st.nextToken());

                int P = Integer.parseInt(st.nextToken());

                boards[U][V] = P;
            }
            // 다익스트라 돌려서 최단 경로 찾기
            Dijkstra(S);
            // 최단 경로 지우기
            deletePath(D);
            // 가중치 저장한 부분 지우기
            weightsInit();
            // 거의 최단 경로 찾기
            Dijkstra(S);

            System.out.println(weights[D] == Integer.MAX_VALUE ? -1 : weights[D]);

            st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());

            M = Integer.parseInt(st.nextToken());
        }

    }

    public static void deletePath(int d){
        if(check[d]){
            return;
        }
        
        check[d] = true;
        for(int i : nodes[d]){
            boards[i][d]=Integer.MAX_VALUE;
            deletePath(i);
        }
    }

    public static void Dijkstra(int S){

        boolean visited[] = new boolean[501];

        weights[S] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(S,0));

        while(!pq.isEmpty()){

            Node nowNode = pq.poll();

            if(visited[nowNode.index]){
                continue;
            }
            visited[nowNode.index] = true;

            for(int i=0;i<501;i++){
                if(visited[i] || boards[nowNode.index][i] == Integer.MAX_VALUE){
                    continue;
                }
                if(nowNode.value + boards[nowNode.index][i] > weights[i]) {
                    continue;
                }
                // i번째 노드까지 최단 경로가 새로 갱신되는 경우 기존 직전 노드 초기화
                if(nowNode.value + boards[nowNode.index][i] < weights[i]){
                    nodes[i].clear();
                }
                // i번째 노드까지 최단 경로로 왔을 때 직전 노드 저장
                nodes[i].add(nowNode.index);

                weights[i] = nowNode.value + boards[nowNode.index][i];
                pq.add(new Node(i,weights[i]));
            }

        }
    }

    static class Node implements Comparable<Node>{
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

    }

    public static void init(){
        for(int i=0;i<501;i++){
            for(int j=0;j<501;j++){
                boards[i][j]=Integer.MAX_VALUE;
            }
            check[i]=false;
        }

        weightsInit();

    }

    public static void weightsInit(){
        for(int i=0;i<501;i++){
            nodes[i] = new HashSet<>();
            weights[i] = Integer.MAX_VALUE;
        }
    }
}