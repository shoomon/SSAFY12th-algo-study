import java.util.*;
import java.io.*;
//날짜 24.10.22
//설계 시간: 답지 참조
//구현 시간: 분
//메모리: 69372 kb
//시간: 600 ms
//끝나는 시간이 빠른 순으로 정렬
//시작시간 <= 끝나는 시간인 강의가 있는 경우 두 강의를 union하여 같은 집합에 배정

public class Main {
    static int N;
    static int[] parent;
    static int[][] ST;
    static PriorityQueue<Integer> pq;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //우선순위 큐 안에 들어있는 값은 해당 강의실에 배정된 강의 중 가장 늦게 끝나는 강의 시간을 의미한다.
        //살펴보고 있는 강의실에 새로운 강의가 배정되면 가장 마지막에 끝나는 강의 시간을 업데이트 하기 위해 pq의 값을 빼고 새로운 값을 삽입한다.
        //-> 빼는 강의 뒤에 새로운 강의 시간표가 붙는다.
        //새로운 강의실에 배정해야 하는 경우 pq의 값을 빼지 않고 값을 삽입한다.
        //-> 현재 강의 뒤에 강의를 배정하지 않고 새로운 강의실을 배정한다.
        //-> 근데 현재 강의와 다른 강의실이지만 같은 강의실 배정되어야 하는 강의가 연속으로 들어오면 각각은 새로운 강의실에 배정되는건가?
        //최종적으로 우선순위 큐에 남아 있는 값들은 각 강의실에 배정된 강의 중 가장 늦게 끝나는 강의들이다.

        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        ST = new int[N][2];
        pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            ST[i][0] = Integer.parseInt(st.nextToken());
            ST[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ST, (o1,o2) -> o1[0] != o2[0] ? o1[0]-o2[0] : o1[1]-o2[1]);

        pq.offer(ST[0][1]);
        for(int i = 1; i < N; i++){
            if(pq.peek() <= ST[i][0]) pq.poll();
            pq.offer(ST[i][1]);
        }
        bw.write(pq.size()+"\n");
        bw.close();
    }
}
