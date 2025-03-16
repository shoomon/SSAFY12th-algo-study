import java.util.*;
class Solution {
    List<List<Integer>>list;
    int answer;
    int N;
    int[][] L;
    public int solution(int n, int[][] lighthouse) {
        list = new ArrayList<>();
        N = n; L = lighthouse;
        //무향그래프이므로 
        for(int i=0;i<n+1;i++) list.add(new ArrayList<>());
        for(int i=0;i<n-1;i++){
            list.get(lighthouse[i][0]).add(lighthouse[i][1]);
            list.get(lighthouse[i][1]).add(lighthouse[i][0]);
        }
        dfs(1,0);
        return answer;
    }
    int dfs(int cur, int before){
        //리프노드면 키지 않기
        if(list.get(cur).size()==1&&list.get(cur).get(0)==before){
            return 1;
        }
        int tmp = 0;
        for(int next : list.get(cur)){
            if(next==before) continue;
            tmp += dfs(next,cur);
        }
        //키지말것
        if(tmp==0) return 1;
        //켜야하는것
        answer++;
        
        return 0;
    }
}
