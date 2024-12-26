package codingTest;
import java.util.*;
public class KAKAO_도넛과막대그래프_wooseok {
    public static void main(String[] args) {
        int[][] edges = {
                {1, 2}, {2, 3}, {3, 4}, {4, 2}, {1, 5}, {5, 6}
        };

        int[] result = solution(edges);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(int[][] edges) {
        int [] answer = new int [4];
        int max_v = 0;
        for(int i = 0; i < edges.length; i++){
            max_v = Math.max(max_v, Math.max(edges[i][0],  edges[i][1]));
        }
        ArrayList<Integer>[] graph = new ArrayList[max_v+1];

        for(int i = 0; i <= max_v; i++){
            graph[i] = new ArrayList<>();
        }
        int [] indegree = new int [max_v+1];
        for(int i = 0; i < edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
            indegree[edges[i][1]]++;
        }

        int v = 0;
        int max = 0;
        for(int i = 1; i<= max_v; i++){
            if(indegree[i] != 0) continue;
            // 간선이 가장 많으면서 들어오는 간선이 없는 정점 찾기
            // 해당 정점이 추가된 정점
            if(graph[i].size() > max){
                v = i;
                max = graph[i].size();
            }
        }
        answer[0] = v; // 추가된 정점 answer 기록


        // ArrayDeque는 시작과 끝의 삽입 삭제가 많은 경우
        // 시간복잡도 상에서 링크드 리스트 보다 유리하다.
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        // 그래프의 개수는 추가된 정점의 간선의 개수와 동일하다.
        boolean [] visited = new boolean[max_v+1];
        for(int i = 0; i < graph[v].size(); i++){
            // start는 각 그래프의 리프 노드
            // 모든 간선을 돌면서 정점의 개수와 간선의 개수를 기록하자
            int start = graph[v].get(i);
            int n = 1; // 정점의 개수
            int c = 0; // 간선의 개수
            visited[start] = true;
            ad.addLast(start);
            while(!ad.isEmpty()){
                int cur = ad.pollFirst();
                int size = graph[cur].size();
                c += size; // 간선의 개수 추가
                for(int j = 0; j < size; j++){
                    int next = graph[cur].get(j);
                    if(visited[next]) continue;
                    visited[next] = true;
                    n++; // 정점의 개수 추가
                    ad.addLast(next);
                }
            }
            if(n == c) answer[1]++; // 도넛 모양
            else if(n - 1 == c) answer[2]++; // 막대 모양
            else answer[3]++; // 8자 모양
        }
        return answer;
    }
}
