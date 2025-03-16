import java.util.*;

class Solution {
    static List<Integer> graph;
    static List<Double> computed;
    static int n;
    
    public List<Double> solution(int k, int[][] ranges) {
        List<Double> answer = new ArrayList<>();
        graph = new ArrayList<>();
        computed = new ArrayList<>();
        n=0;
        
        graph.add(k);
        
        while(k != 1){
            if(k%2 == 0){
                k /= 2;
            }else{
                k *= 3;
                k += 1;
            }
            graph.add(k);
        }
        n = graph.size()-1;
        
        computed.add(0.0);
        for(int i = 0; i < n; i++){
            computed.add(computed.get(i)+integral(i,i+1));
        }
        
        for(int[] i : ranges){
            int end = n+i[1];
            if(check(i[0],end)){
                answer.add(computed.get(end)-computed.get(i[0]));
            }else{
                answer.add(-1D);
            }
        }
        
        return answer;
    }
    
    static double integral(int a, int b){
        int y1 = Math.min(graph.get(a), graph.get(b));
        int y2 = Math.max(graph.get(a), graph.get(b));
        int xLen = b-a;
        
        return (double)xLen*y1 + (double)xLen*((double)y2-(double)y1)/2;
    }
    
    static boolean check(int a, int b){
        if(-1 < a && a <= b && -1 < b && b < n+1) return true;
        return false;
    }
}