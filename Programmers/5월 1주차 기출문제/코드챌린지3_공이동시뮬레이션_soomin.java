class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1, startY=y, startX=x, boundY=y, boundX=x;
        
        for(int i = queries.length-1; i > -1; i--){
            int[] c = queries[i];
            
            if(c[0] == 0){
                if(startY != 0) startY += c[1];
                if(startY > m-1) return 0;
                boundY = Math.min(m-1, boundY+c[1]);
            }else if(c[0] == 1){
                if(boundY != m-1) boundY -= c[1];
                if(boundY < 0) return 0;
                startY = Math.max(0, startY-c[1]);
            }else if(c[0] == 2){
                if(startX != 0) startX += c[1];
                if(startX > n-1) return 0;
                boundX = Math.min(n-1, boundX+c[1]);
            }else if(c[0] == 3){
                if(boundX != n-1) boundX -= c[1];
                if(boundX < 0) return 0;
                startX = Math.max(0, startX-c[1]);
            }
        }
        
        // System.out.println(boundY+" "+boundX);
        
        return (boundY-startY+1)*(boundX-startX+1);
    }
}