import java.util.*;

class Solution {
    static ArrayList<int[]> points;
    static int minY, minX, maxY, maxX;
    
    public String[] solution(int[][] line) {
        String[] answer;
        points = new ArrayList<>();
        minY=Integer.MAX_VALUE;
        minX=Integer.MAX_VALUE;
        maxY=Integer.MIN_VALUE;
        maxX=Integer.MIN_VALUE;
        
        for(int i = 0; i < line.length-1; i++){
            for(int j = i+1; j < line.length; j++) findCrossPoint(line[i], line[j]);
        }
        
        int diffY = maxY-minY+1;
        int diffX = maxX-minX+1;
        answer = new String[diffY];
        System.out.println(maxY+" "+maxX+" "+minY+" "+minX+" "+diffY+" "+diffX);
        
        char[][] tmp = new char[diffY][diffX];
        
        for(char[] c : tmp) Arrays.fill(c, '.');
        
        for(int[] p : points){
            tmp[p[0]-minY][p[1]-minX] = '*';
        }
        
        for(int i = 0; i < diffY; i++) answer[i] = new String(tmp[diffY-i-1]);
        
        return answer;
    }
    
    static void findCrossPoint(int[] l1, int[] l2){
        double y = (double)((long)l1[0]*l2[2]-l1[2]*l2[0]);
        double divY = (double)((long)l1[1]*l2[0]-l1[0]*l2[1]);
        double x = (double)((long)l1[1]*l2[2]-l1[2]*l2[1]);
        double divX = (double)((long)l1[0]*l2[1]-l1[1]*l2[0]);
        
        if(divY == 0 || divX == 0) return;
        
        y /= divY;
        x /= divX;
        
        double floorY = Math.floor(y);
        double floorX = Math.floor(x);
        
        if(floorY < y || floorX < x) return;
        
        int intY = (int)y;
        int intX = (int)x;
        
        points.add(new int[] {intY,intX});
        minX = Math.min(minX, intX);
        minY = Math.min(minY, intY);
        maxY = Math.max(maxY, intY);
        maxX = Math.max(maxX, intX);
        return;
    }
}