class Solution {
    boolean[] visited;
    int count = 0;
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        visited = new boolean[dungeons.length];
        DFS(0, k, dungeons);
        answer = count;
        return answer;
    }
    
    public void DFS(int depth, int HP, int[][] dungeons){
        for(int i = 0; i<dungeons.length; i++){
            if(visited[i] || dungeons[i][0] > HP) continue;
            
            visited[i] = true;
            DFS(depth +1, HP - dungeons[i][1], dungeons);
            visited[i] = false;
        }
        count = Math.max(count, depth);
    }
}
