package codingTest;

public class KAKAO_미로탈출명령어_wooseok {
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    Character[] posChar = {'d','l','r','u'};
    boolean stop = false;
    String answer = "impossible";
    int n , m,sx,sy,ex,ey;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        sx = x-1;
        sy = y-1;
        ex = r-1;
        ey = c-1;

        if(sx == ex && sy == ey)return "";
        if(!canArrival(sx,sy,k)) return answer;
        helper(sx,sy,k-1,"");
        return answer;
    }
    public boolean canArrival(int cx, int cy, int k){
        int d = getD(cx,cy,ex,ey);
        if(d > k || (k-d) % 2 == 1 )return false;
        else return true;
    }
    public int getD(int ax, int ay, int bx, int by){
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }

    public void helper(int x, int y ,int cnt, String cur){
        if(stop || cnt < 0)return;

        for(int k = 0 ; k < 4 ;k++){
            if(stop)return;
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 0 || ny < 0 || nx > n-1
                    || ny > m-1 || !canArrival(nx,ny,cnt) )continue;
            if(cnt > 0)
                helper(nx, ny, cnt-1, cur+posChar[k]);
            if(nx == ex && ny == ey && cnt ==0){
                stop = true;
                answer = cur+posChar[k];
                return;
            }
        }
    }
}
