import java.util.*;
import java.io.*;

//24.10.28
//다틱스트라
//다익스트라 다시 공부해야 할 듯
public class Main {
    static class Node{
        int end, cost;
        public Node(int end, int cost){
            this.end = end;
            this.cost = cost;
        }
    }
    static int N,M,start,end;
    static int[] minDist, prev;
    static List<Node>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        minDist = new int[N+1];
        adj = new ArrayList[N+1];
        prev = new int[N+1];

        Arrays.fill(prev, -1);

        for(int i = 0; i< N+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[start].add(new Node(end, cost));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Arrays.fill(minDist, Integer.MAX_VALUE);
        dajikstra();

        //        for(int i : minDist) System.out.print(i+" ");
//        System.out.println();

//        for(int i : prev) System.out.print(i+" ");
//        System.out.println();

        }

        static void dajikstra(){
            if(start == end){
                System.out.println(0+"\n"+1+"\n"+start);
                return;
            }
            boolean[] visited = new boolean[N+1];

            visited[start] = true;
            minDist[start] = 0;
            prev[start] = 0;
            for(Node n : adj[start]){
                minDist[n.end] = Math.min(minDist[n.end],n.cost);
                prev[n.end] = start;
            }

            for(int i = 0; i < N; i++) {
                int min = -1, dist = Integer.MAX_VALUE;
                for (int j = 1; j < N + 1; j++) {
                    if (visited[j]) continue;
                    if (minDist[j] < dist) {
                        dist = minDist[j];
                        min = j;
                    }
                }
//            System.out.println(min+" "+dist);
                if (min == -1) break;
                visited[min] = true;

                for (Node n : adj[min]) {
//                System.out.println(n.end+" "+n.cost+" "+" "+minDist[min]+" "+min);
                    if (!visited[n.end] && minDist[n.end] > minDist[min] + n.cost) {
                        minDist[n.end] = minDist[min] + n.cost;
                        prev[n.end] = min;
                    }
                }
                if (min == end) {
                    Stack<Integer> stack = new Stack<>();
                    int cur = end;
                    for (; cur != start; cur = prev[cur]) {
                        stack.push(cur);
                    }
                    System.out.println(minDist[end]+"\n"+(stack.size()+1));
                    System.out.print(start+" ");
                    while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
                    break;
                }
            }

        }

}
//2
//2
//1 1 0
//1 2 1
//1 2
