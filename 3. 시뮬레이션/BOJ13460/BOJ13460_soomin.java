import java.awt.*;
import java.io.*;
import java.util.*;
//날짜 24.10.01
//설계 시간: 1분
//구현 시간: 2시간 30분 -> return 빼먹지 말기, 공 굴리는 조건식
//메모리: 19152 kb
//시간: 704 ms
//
public class Main {
    static int N,M, answer;
    static char[][] map, simul;
    static boolean complete;
    static Point red, blue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        simul = new char[N][M];
        answer = Integer.MAX_VALUE;
        blue = new Point();
        red = new Point();

        for(int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 'R'){
                    red.y = i;
                    red.x = j;
                }else if(map[i][j] == 'B'){
                    blue.y = i;
                    blue.x = j;
                }
            }
        }
        simulation(0, new int[10]);
//        initMap();
//        complete = false;
//        left();
//        System.out.println(red.y+" "+red.x+" "+blue.y+" "+blue.x+" "+complete);
//        down();
//        System.out.println(red.y+" "+red.x+" "+blue.y+" "+blue.x+" "+complete);
//        right();
//        System.out.println(red.y+" "+red.x+" "+blue.y+" "+blue.x+" "+complete);
//        down();
//        System.out.println(red.y+" "+red.x+" "+blue.y+" "+blue.x+" "+complete);
//        left();
//        System.out.println(red.y+" "+red.x+" "+blue.y+" "+blue.x+" "+complete);
        if(answer == Integer.MAX_VALUE) answer = -1;
        bw.write(answer+"\n");
        bw.close();
    }

    static void initMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                simul[i][j] = map[i][j];
                if(map[i][j] == 'R'){
                    red.y = i;
                    red.x = j;
                    simul[i][j] = '.';
                }else if(map[i][j] == 'B'){
                    blue.y = i;
                    blue.x = j;
                    simul[i][j] = '.';
                }
            }
        }
    }
//시행 순서 정하기
    static void simulation(int depth, int[] seq){
        if(depth == 10){
//            System.out.println("=================");
            initMap();
            complete = false;
            for(int i = 0 ; i < 10; i++){
                if(seq[i] == 1){
                    if(!up()) return;
                }else if(seq[i] == 2){
                    if(!down()) return;
                }else if(seq[i] == 3){
                    if(!left()) return;
                }else if(seq[i] == 4){
                    if(!right()) return;
                }
//                System.out.println("cur comm: "+seq[i]);
//                System.out.println(blue.y+" "+blue.x);
//                System.out.println(red.y+" "+red.x);
                if(complete){
//                    for(int j : seq) System.out.print(j+" ");
//                    System.out.println();
                    answer = Math.min(answer, i+1);
                    return;
                }
            }
            return;
        }

        for(int i = 1; i < 5; i++){
            seq[depth] = i;
            simulation(depth+1, seq);
        }
    }

    static boolean up(){
        //공 굴리기
        if(blue.y < red.y){
            while(simul[blue.y-1][blue.x] == '.'){
                blue.y--;
            }
            while(simul[red.y-1][red.x] == '.' && !(red.y-1 == blue.y && red.x == blue.x)){
                red.y--;
            }
        }else{
            while(simul[red.y-1][red.x] == '.'){
                red.y--;
            }
            while(simul[blue.y-1][blue.x] == '.' && !(red.y == blue.y-1 && red.x == blue.x)){
                blue.y--;
            }
        }

        //파란공이 빠지면 return false
        if(simul[blue.y-1][blue.x] == 'O') return false;
        if(simul[red.y-1][red.x] == 'O'){
            if(red.y+1 == blue.y && red.x == blue.x) return false;
            complete = true;
            return true;
        }
        return true;
    }

    static boolean down(){
        //공 굴리기
        if(blue.y > red.y){
            while(simul[blue.y+1][blue.x] == '.'){
                blue.y++;
            }
            while(simul[red.y+1][red.x] == '.' && !(red.y+1 == blue.y && red.x == blue.x)){
                red.y++;
            }
        }else{
            while(simul[red.y+1][red.x] == '.'){
                red.y++;
            }
            while(simul[blue.y+1][blue.x] == '.' && !(red.y == blue.y+1 && red.x == blue.x)){
                blue.y++;
            }
        }

        if(simul[blue.y+1][blue.x] == 'O') return false;
        if(simul[red.y+1][red.x] == 'O'){
            if(red.y-1 == blue.y && red.x == blue.x) return false;
            complete = true;
            return true;
        }
        return true;
    }

    static boolean left(){
        //공 굴리기
        if(blue.x < red.x){
            while(simul[blue.y][blue.x-1] == '.'){
                blue.x--;
            }
            while(simul[red.y][red.x-1] == '.' && !(red.y == blue.y && red.x-1 == blue.x)){
                red.x--;
            }
        }else{
            while(simul[red.y][red.x-1] == '.'){
                red.x--;
            }
            while(simul[blue.y][blue.x-1] == '.' && !(red.y == blue.y && red.x == blue.x-1)){
                blue.x--;
            }
        }


        if(simul[blue.y][blue.x-1] == 'O') return false;
        if(simul[red.y][red.x-1] == 'O'){
            if(red.y == blue.y && red.x+1 == blue.x) return false;
            complete = true;
            return true;
        }
        return true;
    }

    static boolean right(){
        //공 굴리기
        if(blue.x > red.x){
            while(simul[blue.y][blue.x+1] == '.'){
                blue.x++;
            }
            while(simul[red.y][red.x+1] == '.' && !(red.y == blue.y && red.x+1 == blue.x)){
                red.x++;
            }
        }else{
            while(simul[red.y][red.x+1] == '.'){
                red.x++;
            }
            while(simul[blue.y][blue.x+1] == '.' && !(red.y == blue.y && red.x == blue.x+1)){
                blue.x++;
            }
        }

//        System.out.println(red.y+" "+red.x);
        if(simul[blue.y][blue.x+1] == 'O') return false;
        if(simul[red.y][red.x+1] == 'O'){
            if(red.y == blue.y && red.x-1 == blue.x) return false;
            complete = true;
            return true;
        }
        return true;
    }
}
