import java.io.*;
import java.util.*;

public class BOJ1922_네트워크연결 {

    static class Edge implements Comparable<Edge> {
        int A, B, W;

        public Edge(int A, int B, int W) {
            this.A = A;
            this.B = B;
            this.W = W;
        }

        @Override
        public int compareTo(Edge o) {
            return this.W - o.W;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 정점 수
        int m = Integer.parseInt(br.readLine()); // 간선 수

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, W);
        }

        // 크루스칼 알고리즘 1단계: 간선들을 가중치 순으로 정렬
        Arrays.sort(edges);

        // 크루스칼 알고리즘 2단계: 사이클 없이 V-1개의 간선을 선택
        parent = new int[n + 1]; // 노드 수 만큼의 배열 생성

        // 모든 노드를 자신을 대표자로 설정 (makeSet)
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int totalWeight = 0; // 최소 비용을 저장할 변수
        int edgeCount = 0; // 선택된 간선의 개수

        for (Edge edge : edges) {
            int px = findSet(edge.A);
            int py = findSet(edge.B);

            // 사이클이 발생하지 않으면 Union 수행
            if (px != py) {
                Union(px, py);
                totalWeight += edge.W;
                edgeCount++;
            }

            // V-1개의 간선을 선택하면 종료
            if (edgeCount == n - 1) {
                break;
            }
        }

        bw.write(totalWeight + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    static int findSet(int x) {
        if (x != parent[x]) {
            parent[x] = findSet(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static void Union(int x, int y) {
        parent[findSet(y)] = findSet(x);
    }
}
