//24.12.18

//Union-Find -> 그래프 구분 가능, but 생성 정점 찾기?
//일반 정점 -> 나가는 선이 1개, 들어오는 선이 있을 수도 없을 수도 있다.
//            나가는 선이 2개, 들어오는 선도 2개.
//생성된 정점 -> 나가는 선이 2개 이상, 들어오는 선 없음.
    
//도넛 -> V=E
//막대 -> V-1=E
//8자 -> V+1=E

//테케 35: 정점 번호는 연속되지 않을 수 있다.
import java.util.*;

class Solution {
    static void makeSet(){
        for(int i = 0; i < 1000001; i++) parents[i] = -1;
    }
    
    static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
    
    static void findCenter(){
        //for(int i = 0; i < size; i++){
        for(int i = 0; i < 1000001; i++){
            if(outCnt[i] > 1 && inCnt[i] == 0){
                centerVtx = i;
                return;
            }
        }
    }
    
    //static int size;
    static int centerVtx;
    static int[] parents, inCnt, outCnt;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        ArrayList<Integer>[] graphs = new ArrayList[1000001];
        inCnt = new int[1000001];
        outCnt = new int[1000001];
        parents = new int[1000001];
        //size = -1;
        makeSet();
        
        for(int i = 0; i < 1000001; i++) graphs[i] = new ArrayList<>();
        
        for(int i = 0; i < edges.length; i++){
            //size = Math.max(size, Math.max(edges[i][0],edges[i][1]));
            outCnt[edges[i][0]]++;
            inCnt[edges[i][1]]++;
        }
        
        findCenter();
        answer[0] = centerVtx;
        
        for(int i = 0; i < edges.length; i++){
            if(edges[i][0] == centerVtx) continue;
            union(edges[i][0],edges[i][1]);
        }
        
        //for(int i = 1; i <= size; i++){
        for(int i = 0; i < 1000001; i++){
            if(i == centerVtx) continue;
            int root = find(i);
            graphs[root].add(i);
        }
        
        for(ArrayList<Integer> list : graphs){
            if(list.size() > 0){
                int edgeCnt=0;
                for(int i : list){
                    edgeCnt += outCnt[i];
                }
                
                if(edgeCnt == list.size()){
                    answer[1]++;
                //}else if(edgeCnt == list.size()-1){
                //    answer[2]++;
                }else if(edgeCnt == list.size()+1){
                    answer[3]++;
                }
                answer[2] = outCnt[centerVtx]-answer[1]-answer[3];
            }
        }
        
        return answer;
    }
}