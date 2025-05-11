
import java.util.*;
import java.io.*;

public class 위클리챌린지_교점에별만들기_mingyung {
	public class Point {
        long x;
        long y;
        
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
       
    static long minx = Long.MAX_VALUE, miny = Long.MAX_VALUE;
    static long maxx = Long.MIN_VALUE, maxy = Long.MIN_VALUE;
    
    public String[] solution(int[][] line) {
        HashSet<Point> pSet = new HashSet<>();
        
        // x = (bf-ed)/(ad-bc)
        // y = (ec-af)/(ad-bc)
        long x, y;
        for (int i=0; i<line.length-1; i++) {
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];
            for (int j=i+1; j<line.length; j++) {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];
                
                long adbc = a*d - b*c;
                if (adbc == 0) continue; // 직선 평행
                
                long bfed = b*f - e*d;
                if (bfed % adbc != 0) continue; // x가 정수가 아님
                
                long ecaf = e*c - a*f;
                if (ecaf % adbc != 0) continue; // y가 정수가 아님
                
                x = bfed / adbc;
                y = ecaf / adbc;
                pSet.add(new Point(x, y));
                
                minx = Math.min(minx, x);
                miny = Math.min(miny, y);
                maxx = Math.max(maxx, x);
                maxy = Math.max(maxy, y);
            }
        }
        
        long height = maxy - miny + 1;
        long width = maxx - minx + 1;
        String[] answer = new String[(int) height];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<width; i++) {
            sb.append(".");
        }
        
        Arrays.fill(answer, sb.toString());
        
        long nx, ny;
        for (Point p : pSet) {
            ny = maxy - p.y;
            nx = p.x - minx;
            answer[(int) ny] = answer[(int) ny].substring(0, (int) nx) + "*" + answer[(int) ny].substring((int) nx + 1);
        }
        
        return answer;
    }
}
