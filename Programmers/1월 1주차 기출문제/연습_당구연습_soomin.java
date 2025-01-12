//25.01.12
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] result = new int[balls.length];
        for (int i = 0 ; i < balls.length; i++){
            double x = startX-balls[i][0]; // X 거리
            double y = startY-balls[i][1]; // Y 거리
            
            double l = Math.pow((startX + balls[i][0]),2) + Math.pow(y,2); // 공을 왼쪽 벽에 대칭
            double r = Math.pow((m - startX) + (m - balls[i][0]),2) + Math.pow(y,2); // 공을 오른쪽 벽에 대칭
            double d = Math.pow((startY + balls[i][1]),2) + Math.pow(x,2); // 공을 아래 벽에 대칭
            double t = Math.pow((n - startY) + (n - balls[i][1]),2) + Math.pow(x,2); // 공을 위의 벽에 대칭
            
            result[i] = (int)Math.min(Math.min(l,r),Math.min(d,t));
            if (x == 0){
                if(y > 0){
                    result[i] = (int)Math.min(Math.min(l,r),t); //아래 대칭 불가
                }
                else{
                    result[i] = (int)Math.min(Math.min(l,r),d); //위쪽 대칭 불가
                }
            }
            else if (y == 0){
                if(x > 0){
                    result[i] = (int)Math.min(Math.min(d,t),r); //왼쪽 대칭 불가
                }
                else{
                    result[i] = (int)Math.min(Math.min(d,t),l); //오른쪽 대칭 불가
                }
            }
            else{
                result[i] = (int)Math.min(Math.min(l,r),Math.min(d,t)); //모두 비교
            }
        }
        
        return result;
    }
}