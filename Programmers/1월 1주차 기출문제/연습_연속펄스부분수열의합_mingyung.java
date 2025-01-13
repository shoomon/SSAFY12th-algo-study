
public class 연습_연속펄스부분수열의합_mingyung {
	public long solution(int[] sequence) {
        long answer = 0;
        
        // 앞에서부터 차근차근 더해보기
        long sum1 = 0;
        long sum2 = 0;
        // 뒤를 뺄지 안뺄지 구하는 코드
        boolean pm = false;
        
        for (int i=0; i<sequence.length; i++) {
            // {-1, 1, -1, 1}, {1, -1, 1, -1} 두 가지 경우
            sum1 = pm ? sum1 + sequence[i] : sum1 - sequence[i];
            sum2 = !pm ? sum2 + sequence[i] : sum2 - sequence[i];

            // 다음엔 바꿔서 곱할 수 있도록 변수 설정
            pm = !pm;
            
            // 만약 sum이 더 커지면 갱신
            if (answer < sum1) answer = sum1;
            if (answer < sum2) answer = sum2;
            // 0보다 작아지면 쓸모없으니 0으로 초기화 후 진행
            if (sum1 < 0) sum1 = 0;
            if (sum2 < 0) sum2 = 0;
        }
        
        return answer;
    }
}
