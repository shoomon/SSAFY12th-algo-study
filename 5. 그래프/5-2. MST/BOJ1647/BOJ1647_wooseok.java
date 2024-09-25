import java.io.*;
import java.util.*;

public class BOJ1647_도시분할계획 {
    // 길 정보 저장 클래스
    static class Node implements Comparable<Node> {
        int A, B, value;
        // A, B : 연결된 집 정보, value : 유지비
        public Node(int A, int B, int value) {
            this.A = A;
            this.B = B;
            this.value = value;
        }
        // 유지비 기준 오름차순
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    static int[] parent; // Union-Find에 사용할 배열
    static ArrayList<Node> road = new ArrayList<>(); // 길 정보 저장 리스트

    public static void main(String[] args) throws IOException {
        // 입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수
        parent = new int[N + 1];

        // parent 배열 각 집으로 초기화
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        // 길 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            road.add(new Node(A, B, C));
        }

        // 유지비 기준 길 오름차순 정렬
        Collections.sort(road);
        int sum = 0; // 최단 경로에 유지비 합
        int max = -1; // 최단 경로에 최소 유지비 길

        // 크루스칼 알고리즘으로 탐색
        for (Node n : road) {
            // 연결 가능한지.
            if (find(n.A) != find(n.B)) {
                union(n.A, n.B); // Union-Find : Union 합치기
                sum += n.value; // 유지비 더하기
                max = Math.max(max, n.value); // 유지비 최대값인지 확인
            }
        }

        sum -= max; // 마을을 2개로 분리하기 위해서 최대 길 제거
        bw.write(String.valueOf(sum)); // 최소 유지비 BufferedWriter 저장
        bw.flush();
        bw.close();
        br.close();
    }

    // Union-Find : Find 함수
    static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    // Union-Find : Union 함수
    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        // parent[] 작은 값 기준으로 저장되도록 설정
        if (pa <= pb)
            parent[pb] = pa; // pb의 부모를 pa로 설정
        else
            parent[pa] = pb; // pa의 부모를 pb로 설정
    }
}
