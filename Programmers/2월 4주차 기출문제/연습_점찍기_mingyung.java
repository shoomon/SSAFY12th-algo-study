
import java.util.*;
import java.io.*;

public class 연습_점찍기_mingyung {
	public long solution(int k, int d) {
        long answer = 0;
        
        // x에 해당하는 y 개수 찾기
        for(long x = 0; x <= d; x += k){
            long maxY = (long) Math.sqrt((long)d*d - (long)x*x);
            // 0 포함시키기 위해서 + 1 처리해줌
            answer += maxY / k + 1;
        }
        
        return answer;
    }
}
