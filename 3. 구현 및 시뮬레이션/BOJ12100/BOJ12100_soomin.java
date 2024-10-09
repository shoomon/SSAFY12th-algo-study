import java.io.*;
import java.util.*;

public class Main {
    //24.10.01
    //설계 시간: 5분
    //구현 시간: 70분 -> 배열 인덱스 범위 주의
    //메모리: 45652 kb
    //시간: 648 ms
    //4개의 command를 중복 허용 5번 나열
    // -> 4*4*4*4*4 = 1024
    static int N, answer=0;
    static int[][] map, simul;
    static Stack<Integer>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        simul = new int[N][N];
        list = new Stack[N];

        for(int i = 0; i < N; i++){
            list[i] = new Stack<>();
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        simulation(0, new int[5]);

        bw.write(answer+"\n");
        bw.close();

    }
    //커맨드 순서 뽑기
    static void simulation(int depth, int[] seq){
        if(depth == 5){
            initMap();
            command(seq);

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    answer = Math.max(answer, simul[i][j]);
                }
            }
            return;
        }

        for(int i = 1; i < 5; i++){
            seq[depth] = i;
            simulation(depth+1, seq);
        }
    }
//커맨드 실행
    static void command(int[] seq){
//        System.out.println("start----------------------");
        for(int i = 0; i < 5; i++){
            int cur = seq[i];
//            System.out.println(cur);

            if(cur == 1){
                up();
            }else if(cur == 2){
                down();
            }else if(cur == 3){
                left();
            }else if(cur == 4){
                right();
            }
//            debug();
        }
    }

    static void up(){
        //왼쪽열부터, 아래 행부터 숫자 담기
        for(int j = 0; j < N; j++){
            for(int i = N-1; i > -1; i--){
                if(simul[i][j] != 0) list[j].push(simul[i][j]);
            }
        }
        clearMap();
        //stack의 값을 꺼내며 합치기
        for(int j = 0; j < N; j++){
            int r=0;
            while(!list[j].isEmpty()){
                int first = list[j].pop();
                if(!list[j].isEmpty()){
                    if(list[j].peek() == first){
                        list[j].pop();
                        //first 값 2배
                        simul[r++][j] = first<<1;
                    }else{
                        simul[r++][j] = first;
                    }
                }else{
                    simul[r++][j] = first;
                }
            }
        }
    }

    static void down(){
//왼쪽열부터, 위 행부터 숫자 담기
        for(int j = 0; j < N; j++){
            for(int i = 0; i < N; i++){
                if(simul[i][j] != 0) list[j].push(simul[i][j]);
            }
        }
        clearMap();
        //stack의 값을 꺼내며 합치기
        for(int j = 0; j < N; j++){
            int r=N-1;
            while(!list[j].isEmpty()){
                int first = list[j].pop();
                if(!list[j].isEmpty()){
                    if(list[j].peek() == first){
                        list[j].pop();
                        //first 값 2배
                        simul[r--][j] = first<<1;
                    }else{
                        simul[r--][j] = first;
                    }
                }else{
                    simul[r--][j] = first;
                }
            }
        }
    }

    static void left(){
//위 행부터, 오른쪽열부터 숫자 담기
        for(int i = 0; i < N; i++){
            for(int j = N-1; j > -1; j--){
                if(simul[i][j] != 0) list[i].push(simul[i][j]);
            }
        }
        clearMap();
        //stack의 값을 꺼내며 합치기
        for(int i = 0; i < N; i++){
            int c=0;
            while(!list[i].isEmpty()){
                int first = list[i].pop();
                if(!list[i].isEmpty()){
                    if(list[i].peek() == first){
                        list[i].pop();
                        //first 값 2배
                        simul[i][c++] = first<<1;
                    }else{
                        simul[i][c++] = first;
                    }
                }else{
                    simul[i][c++] = first;
                }
            }
        }
    }

    static void right(){
//위 행부터, 왼쪽열부터 숫자 담기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(simul[i][j] != 0) list[i].push(simul[i][j]);
            }
        }
        clearMap();
        //stack의 값을 꺼내며 합치기
        for(int i = 0; i < N; i++){
            int c=N-1;
            while(!list[i].isEmpty()){
                int first = list[i].pop();
                if(!list[i].isEmpty()){
                    if(list[i].peek() == first){
                        list[i].pop();
                        //first 값 2배
                        simul[i][c--] = first<<1;
                    }else{
                        simul[i][c--] = first;
                    }
                }else{
                    simul[i][c--] = first;
                }
            }
        }
    }

    static void initMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                simul[i][j] = map[i][j];
            }
        }
    }

    static void clearMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                simul[i][j] = 0;
            }
        }
    }

    static void debug(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(simul[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("=====================");
    }
}
