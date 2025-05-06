class Solution {
    static int answer;
    
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        int num = dungeons.length;
        int[] sequence = new int[num];
        
        for(int i = 0; i < num; i++) sequence[i] = i;
        
        permutation(0,sequence,k,dungeons);
        
        return answer;
    }
    
    static void permutation(int depth, int[] sequence, int k, int[][] dungeons){
        if(depth == sequence.length){
            answer = Math.max(answer, simulation(sequence, k, dungeons));
            return;
        }
        
        for(int i = depth; i < sequence.length; i++){
            swap(depth, i, sequence);
            permutation(depth+1, sequence, k, dungeons);
            swap(depth, i, sequence);
        }
    }
    
    static void swap(int i, int j, int[] arr){
        if (i == j) return; //동일 인덱스인 경우 swap되지 않고 0이 되어버림
        
        arr[i] += arr[j];
        arr[j] = arr[i]-arr[j];
        arr[i] -= arr[j];
    }
    
    static int simulation(int[] sequence, int k, int[][] dungeons){
        int clear = 0;
        
        for(int cur : sequence){
            if(k < dungeons[cur][0]) break;
            
            k -= dungeons[cur][1];
            clear++;
        }
        return clear;
    }
}