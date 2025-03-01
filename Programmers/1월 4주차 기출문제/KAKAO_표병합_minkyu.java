import java.util.*;
import java.io.*;

public class MergeCell {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] commands = {"MERGE 1 1 2 2",
                "UPDATE 1 1 A",
                "UNMERGE 1 1",
                "PRINT 1 1",
                "PRINT 2 2"};
        List<String> ans = sol.solution(commands);
        System.out.println(ans);
    }

    static class Solution {
        String[] valueArr = new String[2500];
        int[] p = new int[2500];
        public List<String> solution(String[] commands) {
            List<String> answer = new ArrayList<>();
            String[][] table = new String[50][50];
            // 대표자 배열 초기화
            for (int i = 0; i < 2500; i++) p[i] = i;
            // 명령어 실행
            for (String command : commands){
                String[] commandDetails = command.split(" ");
                switch(commandDetails[0]){
                    case "UPDATE":
                        // 숫자가 들어온 경우와 아닌 경우
                        if (commandDetails.length == 4){
                            int r = Integer.parseInt(commandDetails[1]) - 1;
                            int c = Integer.parseInt(commandDetails[2]) - 1;
                            String value = commandDetails[3];
                            valueArr[findSet(getX(r,c))] = value;
                        }else{
                            String originValue = commandDetails[1];
                            String changeValue = commandDetails[2];
                            for (int i = 0; i < 2500; i++)
                                if (valueArr[i] != null && valueArr[i].equals(originValue))
                                    valueArr[i] = changeValue;
                        }
                        break;
                    case "MERGE":
                        int r1 = Integer.parseInt(commandDetails[1]) - 1;
                        int c1 = Integer.parseInt(commandDetails[2]) - 1;
                        int r2 = Integer.parseInt(commandDetails[3]) - 1;
                        int c2 = Integer.parseInt(commandDetails[4]) - 1;
                        int cx = findSet(getX(r1,c1));
                        int cy = findSet(getX(r2,c2));
                        if (cx == cy) break;
                        String xVal = valueArr[cx];
                        String yVal = valueArr[cy];
                        if (xVal == null && yVal != null) valueArr[cx] = yVal;
                        valueArr[cy] = null;
                        // 해당 그룹에 속하는 모든 값을 cx로 향하도록 변경해야 한다.
                        for (int i = 0; i < p.length; i++){
                            if (p[i] == cy) p[i] = cx;
                        }
                        break;
                    case "UNMERGE":
                        int r = Integer.parseInt(commandDetails[1]) - 1;
                        int c = Integer.parseInt(commandDetails[2]) - 1;
                        int x = getX(r,c);
                        int target = findSet(x);
                        String foundValue = valueArr[target];
                        // 본인을 대표자로 다시 되돌림
                        for (int i = 0; i < 2500; i++){
                            // 찾았을 때도 같은 경우 target으로 되돌림
                            if (findSet(p[i]) == target){
                                p[i] = i;
                                valueArr[i] = null;
                            }
                        }
                        valueArr[x] = foundValue;
                        break;
                    case "PRINT":
                        int curR = Integer.parseInt(commandDetails[1]) - 1;
                        int curC = Integer.parseInt(commandDetails[2]) - 1;
                        String curVal = valueArr[findSet(getX(curR, curC))];
                        answer.add(curVal == null ? "EMPTY" : curVal);
                        break;
                }
            }
            return answer;
        }

        public int findSet(int x){
            if (p[x] != x){
                p[x] = findSet(p[x]);
            }
            return p[x];
        }

        public void union(int x, int y){
            if (x != y) p[y] = x;
        }

        public int[] getRowCol(int x){
            return new int[]{x / 50, x % 50};
        }
        public int getX(int r, int c){
            return r * 50 + c;
        }
    }
}
