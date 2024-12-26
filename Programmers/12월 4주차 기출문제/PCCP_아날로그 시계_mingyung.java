
import java.util.*;
import java.io.*;

class PCCP_3_mingyung {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        // 초당 움직이는 각도
        double h = (double) 360 / (12*60*60);
//        double m = (double) 360 / (60*60); // 오류나서 그냥 계산한 값으로 입력함
        double m = (double) 0.1;
        double s = (double) 360 / 60;
        
        // 처음 각도
        double curH = (double) (h1*3600+m1*60+s1) * h;
        double curM = (double) (m1*60+s1) * m;
        double curS = (double) s1 * s;

        if (curH==curS || curM==curS) answer++;
        
        // 마지막 각도 같은지 체크
        if ((double) ((h2%12)*3600+m2*60+s2) * h == (double) s2 * s ||
        		(double) (m2*60+s2) * m == (double) s2 * s) answer++;
        
        
        // 이동할 시간
        int time = (h2*3600 + m2*60 + s2) - (h1*3600 + m1*60 + s1);
               
        // 시계 회전
        while (time-- > 0) {
            double nextH = (curH+h);
            double nextM = (curM+m);
            double nextS = (curS+s);
            
            // 현재 시침이 현재 초침보다 앞에 있다가, 다음 시침이 다음 초침보다 뒤에 있다면 겹쳤던 것
            if (Double.compare(curH, curS) > 0 && Double.compare(nextH, nextS) <= 0) {
            	answer++;
            }
            // 분침 체크
            if (Double.compare(curM, curS) > 0 && Double.compare(nextM, nextS) <=0) {
            	answer++;
            }
            // 시침과 분침이 겹쳤었다면 두번 더해진 것 -처리
            if (Double.compare(curH, curM) > 0 && Double.compare(curH, curS) > 0
            		&& Double.compare(nextH, nextM) <= 0 && Double.compare(nextH, nextS) <= 0) {
            	answer--;
            }
            
//            if (curH > curS && curH <= nextS) {
//            	answer++;
//            	// 디버깅용 출력
//                System.out.printf("time: %d -> curH: %.2f, curM: %.2f, curS: %.2f\n", time, curH, curM, curS);
//            	System.out.println("H: "+answer);
//            }
//            if (curM > curS && curM <= nextS) {
//            	answer++;
//            	// 디버깅용 출력
//                System.out.printf("time: %d -> curH: %.2f, curM: %.2f, curS: %.2f\n", time, curH, curM, curS);
//            	System.out.println("M: "+answer);
//            }
            
            curH = nextH % 360;
            curM = nextM % 360;
            curS = nextS % 360;
            
            // 360.0을 정확히 0.0으로 보정
            if (Math.abs(curH - 360.0) < 1e-9) {
                curH = 0.0;
            }
//          if (Math.abs(curM - 360.0) < 1e-9) {
//  	       curM = 0.0;
//	           System.out.println("curM 보정됨: " + curM);
//	        }
//		    if (Math.abs(curS - 360.0) < 1e-9) {
//      	   curS = 0.0;
//      	}
      
//      	if (Math.abs(curH - curS) < 1e-9 && Math.abs(curM - curS) < 1e-9) {
//          	answer--;
//          	// 디버깅용 출력
//          	System.out.printf("time: %d -> curH: %.2f, curM: %.2f, curS: %.2f\n", time, curH, curM, curS);
//         		System.out.println("-: " + answer);
//      	}
        }
        
        return answer;
    }
}