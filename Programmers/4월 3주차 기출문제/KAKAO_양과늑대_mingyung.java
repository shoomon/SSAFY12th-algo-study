
import java.util.*;
import java.io.*;

public class KAKAO_양과늑대_mingyung {
	static int ans = 0;
    static ArrayList<Integer> [] nodes = new ArrayList [17];
    static int [] INFO;
    public int solution(int[] info, int[][] edges) {
        INFO = info;
        for(int i = 0; i < 17; i++){
            nodes[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            int parent = edges[i][0];
            int child = edges[i][1];
            nodes[parent].add(child);
        }
        ArrayList<Integer> non_visited = new ArrayList<Integer>();
        non_visited.add(0);
        recur(0, non_visited , 0, 0);
        return ans;
    }

    public void recur(int now, ArrayList<Integer> non_visited, int lamps, int wolfs){
        if(INFO[now] == 0) lamps++;
        else wolfs++;
        if(lamps <= wolfs) return;
        // (0) 양 최대값 저장
        ans = Math.max(lamps, ans);
        // (1) 자식 노드 미방문 정점에 넣기
        non_visited.addAll(nodes[now]);
        // (2) 미방문 정점에서 자기 자신을 빼기 
        non_visited.remove(Integer.valueOf(now));
        // (3) non_visited에 해당하는 정점 하나씩 방문
        for(int i = 0; i < non_visited.size(); i++){
            int next = non_visited.get(i);
            ArrayList<Integer> next_nodes = new ArrayList<>(non_visited);
            recur(next,next_nodes, lamps, wolfs);
        }
    }
}
