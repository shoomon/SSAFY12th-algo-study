//n^2 불가능

class Solution {

    static int find(int[] numbers, int a, int end){
        if(answer[a] == -1) return -1;
        if(numbers[answer[a]] > end) return answer[a];
        return find(numbers,answer[a], end);
    }
    
    static int[] answer;
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        answer = new int[N];
        
        for(int i = 0; i < N; i++) answer[i] = -1;
        
        for(int i = N-2; i > -1; i--){
            if(numbers[i] > numbers[i+1]){
                answer[i] = find(numbers,i+1,numbers[i]);
            }else if(numbers[i] < numbers[i+1]){
                answer[i] = i+1;
            }else{
                answer[i] = answer[i+1];
            }
        }
        
        for(int i = 0; i < N; i++) answer[i] = answer[i] == -1 ? -1 : numbers[answer[i]];
        return answer;
    }
}