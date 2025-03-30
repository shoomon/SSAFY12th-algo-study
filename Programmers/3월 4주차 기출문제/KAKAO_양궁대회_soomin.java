class Solution {
    static int diff;
    static int[] answer;
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        diff = 0;
        
        simulation(0, new boolean[10], info, n);
        
        return diff == 0 ? new int[] {-1} : answer;
    }
    
    static void simulation(int depth, boolean[] isSelected, int[] info, int n){
        if(depth == 10){
            int[] arr = new int[11];
            int remain = n, apeach=0, lion=0;
            
            for(int i = 0; i < 10; i++){
                if(isSelected[i]){
                    if(info[i] >= remain) return;
                    lion += 10-i;
                    arr[i] = info[i]+1;
                    remain -= info[i]+1;
                }else if(info[i] > 0){
                    apeach += 10-i;
                }
            }
            
            arr[10] = remain; //10번은 0점이라 점수에 영향 없음
            
            if (lion > apeach && (lion - apeach) > diff) {
                diff = lion - apeach;
                answer = arr.clone();
            } else if (lion > apeach && (lion - apeach) == diff) {
                for (int i = 10; i >= 0; i--) {
                    if (arr[i] > answer[i]) {
                        answer = arr.clone();
                        break;
                    } else if (arr[i] < answer[i]) break;
                }
            }
            
            return;
        }
        
        isSelected[depth] = true;
        simulation(depth+1, isSelected, info, n);
        
        isSelected[depth] = false;
        simulation(depth+1, isSelected, info, n);
    }
}