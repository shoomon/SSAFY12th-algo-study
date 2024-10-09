import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int start, end, weight;

    public Node(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
public class   BOJ1197_최소스패닝트리 {
    static int[] parent;
    static PriorityQueue<Node> pq;

    static int n, k, start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b, c));
        }

        System.out.println(solve());
    }

    private static int solve() {
        int sum = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int parentS = find(node.start);
            int parentE = find(node.end);
            if (parentS != parentE) {
                Union(parentS, parentE);
                sum += node.weight;
            }
        }
        return sum;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    private static void Union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }
}
