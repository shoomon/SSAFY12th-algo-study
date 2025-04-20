import java.util.*;

class Solution {
    static List<Integer>[] adj;
    static int answer;

    public int solution(int[] info, int[][] edges) {
        answer = 0;
        adj = new List[info.length];

        for (int i = 0; i < info.length; i++) adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }

        List<Integer> next = new ArrayList<>();
        next.add(0);

        simulation(0, 0, next, info);

        return answer;
    }

    static void simulation(int sheep, int wolf, List<Integer> available, int[] info) {
        for (int i = 0; i < available.size(); i++) {
            int cur = available.get(i);

            int newSheep = sheep + (info[cur] == 0 ? 1 : 0);
            int newWolf = wolf + (info[cur] == 1 ? 1 : 0);

            if (newWolf >= newSheep) continue;

            answer = Math.max(answer, newSheep);

            List<Integer> next = new ArrayList<>(available);
            next.remove(i);
            next.addAll(adj[cur]);

            simulation(newSheep, newWolf, next, info);
        }
    }
}
