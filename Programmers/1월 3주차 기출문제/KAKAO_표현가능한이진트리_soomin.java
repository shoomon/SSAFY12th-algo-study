import java.util.*;

class Solution {
    public List<Integer> solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();
        
        for(long num : numbers){
            String binary = Long.toBinaryString(num);
                
            int size = 1;
            while(size-1 < binary.length()) size = size<<1;
            size--;
            
            char[] tree = new char[size];
            Arrays.fill(tree, '0');
            
            int diff = size-binary.length();
            // System.out.println(num+" "+size+" "+diff);
            
            for(int i = 0; i < binary.length(); i++) tree[diff+i] = binary.charAt(i);
            // for(int i = diff-1; i > -1; i--) tree[i] = '0';
            
            if(checkSubTree(0,size-1,tree)){
                answer.add(1);
            }else{
                answer.add(0);
            }
        }
        
        return answer;
    }
    
    static boolean checkSubTree(int start, int end, char[] tree){
        if(start > end) return true;
        int root = (start+end)/2;
            
        if(tree[root] == '0'){
            for(int i = start; i < root; i++){
                if(tree[i] == '1') return false;
            }
            
            for(int i = root+1; i < end+1; i++){
                if(tree[i] == '1') return false;
            }
        }
        
        return checkSubTree(start, root-1, tree) && checkSubTree(root+1, end, tree);
    }
    
    // static String toBinary(long num){
    //     Stack<Long> stack = new Stack<>();
    //     long answer = 0;
    //     while(num > 1){
    //         stack.push(num%2);
    //         num /= 2;
    //     }
    //     if(num == 1) stack.push(num);
        
    //     while(!stack.isEmpty()){
    //         answer = answer*10+stack.pop();
    //     }
    //     // System.out.println(answer);
    //     return answer+"";
    // }
}