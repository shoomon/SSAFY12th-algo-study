class Solution {
    private int maxDungeons = 0;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, 0, dungeons, visited);
        return maxDungeons;
    }
    
    private void dfs(int fatigue, int count, int[][] dungeons, boolean[] visited) {
        // 현재까지 탐험한 던전 수가 최대값인지 확인
        maxDungeons = Math.max(maxDungeons, count);
        
        // 모든 던전에 대해 탐험 시도
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && fatigue >= dungeons[i][0]) { 
                visited[i] = true;
                dfs(fatigue - dungeons[i][1], count + 1, dungeons, visited);
                visited[i] = false;
            }
        }
    }
}