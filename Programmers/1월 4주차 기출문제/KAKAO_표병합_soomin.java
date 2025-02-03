//테케만 맞음. 런타임 에러 발생.
import java.util.*;

class Solution {    
    static String[][] table;
    static int[] parents;
    
    public List<String> solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        table = new String[51][51];
        parents = new int[2501];
        
        for(int i = 0; i < 51; i++) Arrays.fill(table[i],""); //배열을 초기화하지 않으면 null값 들어감
        for(int i = 0; i < 2501; i++) {
            parents[i] = -1;
        }
        
        for(String cmd : commands){
            String[] st = cmd.split(" ");
            
            if("UPDATE".equals(st[0])){
                if(st.length == 4){
                    update(Integer.parseInt(st[1]), Integer.parseInt(st[2]), st[3]);
                }else {
                    replace(st[1], st[2]);
                }
            }else if("MERGE".equals(st[0])){
                merge(Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]), Integer.parseInt(st[4]));
            }else if("UNMERGE".equals(st[0])){
                unmerge(Integer.parseInt(st[1]), Integer.parseInt(st[2]));
            }else if("PRINT".equals(st[0])){
                answer.add(print(Integer.parseInt(st[1]), Integer.parseInt(st[2])));
            }
        }
        
        // for(int i = 1; i < 6; i++){
        //     for(int j = 1; j < 6; j++) System.out.print(table[i][j]+" /");
        //     System.out.println();
        // }
        
        // System.out.println(parents[51]+" "+parents[52]+" "+parents[53]+" "+parents[54]);
        
        return answer;
    }
    //O(N^2)
    static void update(int r, int c, String val){
        if("-1".equals(table[r][c])){
            int root = find(r*50+c);
            table[root/50][root%50] = val;
        }else{
            table[r][c] = val;
        }        
    }
    //O(N^2)
    static void replace(String val1, String val2){
        for(int i = 1; i < 51; i++){
            for(int j = 1; j < 51; j++){
                if(val1.equals(table[i][j])) table[i][j] = val2;
            }
        }
    }
    
    static void merge(int r1, int c1, int r2, int c2){
        if(r1 == r2 && c1 == c2) return;
        
        int root1 = find(r1*50+c1);
        int root2 = find(r2*50+c2);
        
        table[root1/50][root1%50] = "".equals(table[root1/50][root1%50]) ? table[root2/50][root2%50] : table[root1/50][root1%50];
        union(r1*50+c1, r2*50+c2);
        
        table[root2/50][root2%50] = "-1";
    }
    //O(N^2)
    static void unmerge(int r, int c){
        int root = find(r*50+c);
        String val = table[root/50][root%50];
        Queue<Integer> q = new ArrayDeque<>();
        
        parents[root] = -1;
        table[root/50][root%50] = "";
        
        for(int i = 1; i < 2501; i++){
            find(i);
            if(parents[i] == root) {
                q.offer(i); //parents값 바로 수정 X
                table[i/50][i%50] = "";
            }
        }
        while(!q.isEmpty()) parents[q.poll()] = -1;
        table[r][c] = val;
    }
    //O(1)
    static String print(int r, int c){
        // System.out.println(table[r][c]+" "+ (table[r][c] instanceof String));
        if("".equals(table[r][c])) return "EMPTY";
        
        if("-1".equals(table[r][c])) {
            int root = find(r*50+c);
            return "".equals(table[root/50][root%50]) ? "EMPTY" : table[root/50][root%50];
        }
        
        return table[r][c];
    }
    
    static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b){
        int rootA =  find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
    
}