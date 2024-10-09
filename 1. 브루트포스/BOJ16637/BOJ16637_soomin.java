import java.util.*;
public class Main {
    static int N, answer=Integer.MIN_VALUE;
    static char[] in;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine(); //개행문자 처리
        in = sc.nextLine().toCharArray();

        getAnswer(2, in[0]-'0'); //다음 피연산자 위치, 현재까지 계산된 값
        System.out.println(answer);
    }

    static void getAnswer(int idx, int cur){ //다음 피연산자 위치, 현재까지 계산된 결과
        if(idx >= N) {
            answer = Math.max(answer, cur);
            return;
        }

        //괄호를 치지 않은 경우
        int case1 = calculate(cur, in[idx]-'0', in[idx-1]); //현재까지 결과와 다음 피연산자 연산
        getAnswer(idx+2, case1); //다음 피연산자 위치, 현재까지 계산 결과

        //오른쪽에 괄호를 치는 경우
        if(idx+2 < N){ //괄호를 칠 수 있는지 확인
            int opr2 = calculate(in[idx]-'0', in[idx+2]-'0', in[idx+1]); //오른쪽 먼저 계산
            int res = calculate(cur, opr2, in[idx-1]); //현재까지 결과와 오른쪽 계산 결과 연산
            getAnswer(idx+4, res);
        }
    }

    static int calculate(int a, int b, char c){
        if(c == '+'){
            return a+b;
        }else if(c == '-'){
            return a-b;
        }else if(c == '*'){
            return a*b;
        }
        return -1;
    }

}
