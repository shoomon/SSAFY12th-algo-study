class Solution {
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        //던전 최대 8
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, dungeons, visited, 0);
        return answer;
    }
    
    private void dfs(int k, int[][] dungeons, boolean[] visited, int depth) {
        answer = Math.max(answer, depth);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) { // 최소 필요 피로도 이상이면
                visited[i] = true;
                //해당 던전의 피로도 뺀 만큼 재귀
                dfs(k - dungeons[i][1], dungeons, visited, depth + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }
}
