package codingTest;

public class pccp_아날로그시계_wooseok {
    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        // 시작시간(단위 : 초), 종료시간(단위 : 초)
        int start_time = h1*3600+m1*60+s1;
        int end_time = h2*3600+m2*60+s2;

        int answer = 0;
        // 시작시간이 12시 0분 0초인 경우(0시,12시 2번 있음)
        if(start_time%360==0 || start_time%360==12) answer++;

        // 종료시간 될때까지 1초씩 증가
        while(start_time<end_time){
            // 시침 : 12시간에 360도 > 1시간(60분)에 30도 > 1분(60초)에 1/2도 > 1초에 1/120도 움직임
            // 분침 : 60분에 360도 > 1분(60초)에 6도 > 1초에 1/10도 움직임
            // 초침 : 60초에 360도 > 1초에 6도 움직임

            // 초로 계산한 현재시간을 시침,분침,초침 단위로 각도 계산
            double h_angle = (start_time/(double)120)%360;
            double m_angle = (start_time/(double)10)%360;
            double s_angle = (start_time*6)%360;

            // 1초 지난후, 시침,분침,초침 단위로 각도 계산
            double h_angle_next = ((start_time+1)/(double)120)%360;
            double m_angle_next = ((start_time+1)/(double)10)%360;
            double s_angle_next = ((start_time+1)*6)%360;

            // 각도가 0도면 360도로 계산
            if(h_angle_next==0) h_angle_next=360;
            if(m_angle_next==0) m_angle_next=360;
            if(s_angle_next==0) s_angle_next=360;

            // 초침의 각도가 시침보다 작았는데, 시침을 넘어서는 경우
            if(s_angle<h_angle && s_angle_next>=h_angle_next) answer++;
            // 초침의 각도가 분침보다 작았는데, 분침을 넘어서는 경우
            if(s_angle<m_angle && s_angle_next>=m_angle_next) answer++;
            // 시침과 분침이 같아서 계산이 2번된 경우 -1
            if(h_angle_next==m_angle_next) answer--;

            // 1초 증가
            start_time+=1;
        }

        return answer;
    }

    public static void main(String[] args) {
        // 테스트 케이스
        int[][] testCases = {
                {0, 5, 30, 0, 7, 0, 2},
                {12, 0, 0, 12, 0, 30, 1},
                {0, 6, 1, 0, 6, 6, 0},
                {11, 59, 30, 12, 0, 0, 1},
                {11, 58, 59, 11, 59, 0, 1},
                {1, 5, 5, 1, 5, 6, 2},
                {0, 0, 0, 23, 59, 59, 2852}
        };

        // 결과 출력
        for (int[] testCase : testCases) {
            int h1 = testCase[0];
            int m1 = testCase[1];
            int s1 = testCase[2];
            int h2 = testCase[3];
            int m2 = testCase[4];
            int s2 = testCase[5];
            int expected = testCase[6];

            int result = solution(h1, m1, s1, h2, m2, s2);
            System.out.println("Input: (" + h1 + ", " + m1 + ", " + s1 + ", " + h2 + ", " + m2 + ", " + s2 + ")");
            System.out.println("Expected: " + expected + ", Result: " + result);
            System.out.println();
        }
    }
}
