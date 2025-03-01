package codingTest;

import java.util.*;

public class KAKAO_표병합_wooseok {
    static String [][] map = new String [50][50];
    static int [] parent = new int [2500];
    static final String EMPTY = "EMPTY";
    static ArrayList<String> answers = new ArrayList<>();

    public static String[] solution(String[] commands) { // union - find 풀이
        // 초기화 진행
        for(int i = 0; i < 2500; i++){
            parent[i] = i;
        }
        // map 초기화
        for(int i = 0; i < 50; i++){
            Arrays.fill(map[i], EMPTY);
        }
        // commands 로직
        for(String next : commands){
            String [] split = next.split(" ");
            if(split[0].equals("UPDATE")){
                if(split.length == 3){ // "UPDATE value1 value2"
                    for(int i = 0; i < 50; i++){
                        for(int j = 0; j < 50; j++){
                            if(map[i][j].equals(split[1])) map[i][j] = split[2];
                        }
                    }
                }
                else{ // "UPDATE r c value"
                    int r = Integer.parseInt(split[1]) - 1;
                    int c = Integer.parseInt(split[2]) - 1;
                    String value = split[3];
                    int root = find(r * 50 + c);
                    for(int i = 0; i < 2500; i++){
                        if(find(i) == root){
                            int x = i / 50;
                            int y = i % 50;
                            map[x][y] = value;
                        }
                    }
                }
            }
            else if(split[0].equals("MERGE")){ // "MERGE r1 c1 r2 c2"
                int r1 = Integer.parseInt(split[1]) - 1;
                int c1 = Integer.parseInt(split[2]) - 1;
                int r2 = Integer.parseInt(split[3]) - 1;
                int c2 = Integer.parseInt(split[4]) - 1;

                int root1 = find(r1 * 50 + c1);
                int root2 = find(r2 * 50 + c2);

                if (root1 != root2) {
                    union(root1, root2);
                    int root = find(root1);

                    String value = map[r1][c1].equals(EMPTY) ? map[r2][c2] : map[r1][c1];

                    for(int i = 0; i < 2500; i++){
                        if(find(i) == root){
                            int x = i / 50;
                            int y = i % 50;
                            map[x][y] = value;
                        }
                    }
                }
            }
            else if(split[0].equals("UNMERGE")){ // "UNMERGE r c"
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                String value = map[r][c];
                int root = find(r * 50 + c);

                ArrayList<Integer> toUnmerge = new ArrayList<>();
                for(int i = 0; i < 2500; i++){
                    if(find(i) == root){
                        toUnmerge.add(i);
                    }
                }

                for(int i : toUnmerge){
                    parent[i] = i; // 병합 해제
                    int x = i / 50;
                    int y = i % 50;
                    map[x][y] = EMPTY;
                }
                map[r][c] = value;
            }
            else if(split[0].equals("PRINT")){ // "PRINT r c"
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                answers.add(map[r][c]);
            }
        }
        // 정답 리턴
        return answers.toArray(new String[0]);
    }

    public static int find(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y){
            if(x > y) parent[x] = y;
            else parent[y] = x;
        }
    }

    public static void main(String[] args) {
        String[] commands = {
                "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle",
                "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik",
                "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"
        };
        System.out.println(Arrays.toString(solution(commands)));
    }
}
