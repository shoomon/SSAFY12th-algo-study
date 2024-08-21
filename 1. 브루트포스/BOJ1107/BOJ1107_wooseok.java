package BOJ1107;

import java.io.*;
import java.util.*;

public class BOJ1107_wooseok {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int channel = Integer.parseInt(br.readLine());
        int brokenBtn = Integer.parseInt(br.readLine());
        int[] brokenBtnArr = new int[10];

        if(brokenBtn > 0){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < brokenBtn; i++) {
                int number = Integer.parseInt(st.nextToken());      //고장난 버튼 저장
                brokenBtnArr[number]--;
            }
        }
        if(channel - 100 == 0) {        // 이동 채널이 100인 경우 이동할 필요가 없다.
            System.out.println(0);
            return;
        }

        //+- 눌려서 이동하는 거리
        int answer = Math.abs(channel - 100); // 초기값으로 100에서 목표 채널까지의 거리를 설정
        StringBuffer sb = new StringBuffer(); // 버튼을 누를 때 생성되는 숫자를 저장할 StringBuffer를 생성

        for(int i = 0; i < 500_000; i++) { // 0부터 999,999까지 반복
            sb.append(i); // StringBuffer에 i 값 추가
            int length = sb.length(); // 현재 StringBuffer의 길이를 저장
            boolean chk = false; // 현재 숫자에서 고장난 버튼이 있는지 확인하기 위한 플래그

            // 현재 숫자의 각 자리 숫자에 대해 고장난 버튼 여부를 확인합니다.
            for(int j = 0; j < length; j++) {
                if(brokenBtnArr[sb.charAt(j) - '0'] == -1) { // 만약 고장난 버튼이면 >> line 22
                    chk = true; // 플래그를 true로 설정하고
                    break; // 반복문을 종료합니다.
                }
            }

            if(!chk) { // 고장난 버튼이 없는 경우
                //+-이동과 버튼 눌렸을 때의 합을 계산하고, answer를 갱신하기
                answer = Math.min(Math.abs(channel - i) + length, answer);
            }

            sb.setLength(0); // StringBuffer를 초기화하여 다음 숫자를 저장할 준비를 합니다.
        }

        System.out.println(answer);

    }

}

