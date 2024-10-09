import java.util.*;
import java.io.*;
//날짜 24.10.05
//설계 시간: 15 분
//구현 시간: 10 분
//메모리: 92284 kb
//시간: 428 ms
//각 시험장마다 총 감독관 1명이 있어야 한다.
//입력 크기 주의: 최대 감독관 수는 100만*100만으로, int 범위인 21억을 넘어감.
public class Main {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()), B, C;
        long answer=0;
        int[] num = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            if(num[i] == 0) continue;
            num[i] = Math.max(0, num[i]-B);
            answer++;
            answer += (int)Math.ceil((double)num[i]/C);
        }

        bw.write(answer+"\n");
        bw.close();
    }
}
