import java.util.*;

class Solution {
    static List<Integer>[] adjList;
    static boolean[] lights; // 등대 불이 켜져 있는지 확인
    static boolean[] visited; // 등대 방문 여부
    static int answer = 0; // 켜진 등대 개수

    public int solution(int n, int[][] lighthouse) {
        // 인접리스트 생성 : 서로 연결되어 있는 노드 연결하기
        adjList = new ArrayList[n+1];
        lights = new boolean[n+1];
        visited = new boolean[n+1];

        for(int i=1; i<n+1; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int[] light : lighthouse){
            adjList[light[0]].add(light[1]);
            adjList[light[1]].add(light[0]);
        }

        // dfs 탐색
        visited[1] = true;
        dfs(1, -1);

        return answer;
    }

    private void dfs(int curr, int parent){
        // dfs로 자식 노드 탐색
        for(int next : adjList[curr]){
            if(next != parent && !visited[next]){
                visited[next] = true;
                dfs(next, curr); // next : 현재, curr : 부모
            }
        }

        // 부모를 제외한 자식 노드를 모두 탐색
        // 자식 노드 중 하나라도 불이 꺼져 있다면 -> 부모 노드 불 켜기
        boolean isChildLightOff = false;
        for(int next : adjList[curr]){
            // 부모 노드는 탐색 x
            if(next == parent) continue;

            // 자식의 불이 켜져있는지 확인
            if(!lights[next]){
                isChildLightOff = true; // 하나라도 꺼져 있다면, 현재 노드 켜기
                break;
            }
        }

        // 자식 등대가 하나라도 꺼져 있다면, 현재 노드 켜기
        if(isChildLightOff) {
            lights[curr] = true;
            answer++;
        }
    }


}