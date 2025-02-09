public class MagicElevator {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int ans = sol.solution(500);
        System.out.println(ans);
    }

    static class Solution {
        public int solution(int storey) {
            String num = String.valueOf(storey);
            int size = num.length();

            int cnt = 0;
            int pos = 1;
            // 일의 자리부터 탐색
            for(int i = size-1; i > 0; i--){
                int curNum = Integer.parseInt(String.valueOf(num.charAt(i)));
                int nextNum = Integer.parseInt(String.valueOf(num.charAt(i-1)));
                if(curNum<5){
                    cnt+=curNum;
                    storey-=(curNum*pos);
                }else if(curNum==5){
                    if(nextNum<5){
                        cnt+=curNum;
                        storey-=(curNum*pos);
                    }else{
                        cnt+=(10-curNum);
                        storey+=((10-curNum)*pos);
                    }
                }else{
                    cnt+=(10-curNum);
                    storey+=((10-curNum)*pos);
                }
                num = String.valueOf(storey);
                pos*=10;
            }
            int last = storey/pos;
            if(last>5) cnt+=(10-last)+1;
            else cnt+=last;

            // 버튼 횟수 리턴
            return cnt;
        }
    }
}
