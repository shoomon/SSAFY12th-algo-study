import java.util.*;
import java.io.*;
//날짜 24.09.24
//설계 시간: 1분
//구현 시간: 30분
//메모리: 49288 kb
//시간: 552 ms
public class Main {
    //최소 비용, 연결 그래프 -> MST
    //비용이 양수이므로 크루스칼, 프림 모두 가능
    //노드 수가 1000개이고 희소그래프이므로 인접리스트 사용
    static class Edge implements Comparable<Edge>{
        int s,e,w;
        public Edge(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e){
            return this.w-e.w;
        }
    }
    static int N, M;
    static int[] parents;

    static void make(){
        parents = new int[N+1];
        for(int i = 0; i < N+1; i++) parents[i] = -1;
    }

    static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Edge[] edge;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edge = new Edge[M];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge[i] = new Edge(s,e,w);
        }

        Arrays.sort(edge);
        make();

        int cnt=0,cost=0;
        for(Edge e : edge){
            if(union(e.s, e.e)){
                cnt++;
                cost += e.w;
                if(cnt == N-1) break;
            }
        }
        bw.write(cost+"\n");

        bw.close();
    }
}
