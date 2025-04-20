import java.util.*;

public class SheepWolf {

    public static class Solution {
        public int solution(int[] info, int[][] edges) {
            int n = info.length;

            List<List<Integer>> tree = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                tree.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                tree.get(edge[0]).add(edge[1]);
            }

            List<Integer> nextNodes = new ArrayList<>();
            nextNodes.add(0);
            return dfs(0, 0, 0, nextNodes, tree, info, new HashSet<>());
        }

        private int dfs(int node, int sheep, int wolves, List<Integer> nextNodes,
                        List<List<Integer>> tree, int[] info, Set<Integer> visited) {
            int newSheep = sheep + (info[node] == 0 ? 1 : 0);
            int newWolves = wolves + (info[node] == 1 ? 1 : 0);

            if (newWolves >= newSheep) return 0;

            visited.add(node);

            List<Integer> updatedNextNodes = new ArrayList<>(nextNodes);
            updatedNextNodes.remove(Integer.valueOf(node));
            for (int child : tree.get(node)) {
                if (!visited.contains(child)) {
                    updatedNextNodes.add(child);
                }
            }

            int maxSheep = newSheep;

            for (int next : updatedNextNodes) {
                if (!visited.contains(next)) {
                    maxSheep = Math.max(maxSheep,
                            dfs(next, newSheep, newWolves, updatedNextNodes, tree, info, new HashSet<>(visited)));
                }
            }

            return maxSheep;
        }
    }
}
