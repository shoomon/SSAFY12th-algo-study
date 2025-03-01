public class FindMaze {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String ans = sol.solution(3,4,2,3,3,1,5);
        System.out.println(ans);
    }

    static class Solution {
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            int diff = Math.abs(x-r) + Math.abs(y-c);
            int move = k;
            // 1번남는 경우나, 실제 거리보다 이동 가능 횟수가 적으면 불가능
            if (move - diff < 0 || (move - diff) % 2 == 1) return "impossible";
            StringBuilder sb = new StringBuilder();
            int newDiff=0;
            while (move > 0)
            {
                if ((move - diff) == 0) {
                    --diff;
                    if (r - x > 0) {
                        sb.append("d");
                        ++x;
                    }
                    else if (y - c > 0) {
                        sb.append("l");
                        --y;
                    }
                    else if (c - y > 0) {
                        sb.append("r");
                        ++y;
                    }
                    else {
                        sb.append("u");
                        --x;
                    }
                }
                else {
                    if (x+1<=n && move-1>=(newDiff=Math.abs(x+1-r)+Math.abs(y-c))) {
                        sb.append("d");
                        ++x;
                    }
                    else if (y-1>=1 && move-1>=(newDiff=Math.abs(x-r)+Math.abs(y-1-c))) {
                        sb.append("l");
                        --y;
                    }
                    else if (y+1<=m && move-1>=(newDiff=Math.abs(x-r)+Math.abs(y+1-c))) {
                        sb.append("r");
                        ++y;
                    }
                    else if (x-1>=1 && move-1>=(newDiff=Math.abs(x-1-r)+Math.abs(y-c))) {
                        sb.append("u");
                        --x;
                    }
                    diff = newDiff;
                }
                move--;
            }
            return sb.toString();
        }
    }
}
