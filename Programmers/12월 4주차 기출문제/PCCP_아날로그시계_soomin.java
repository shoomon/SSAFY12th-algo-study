//24.12.25
//설계: 15분
//구현: 분
//시뮬레이션 최대 반복 횟수 -> 86400(24시간*60*60초)
//1초 -> 6도, 0.1도, 1/120도=0.0083333333333333...
//부동소수점비교
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        double time = h1*3600+m1*60+s1, end = h2*3600+m2*60+s2;
        double sec=(time*6)%360,min=(time*0.1)%360,hour=(time/120)%360,ns=0,nm=0,nh=0;
        
        if(sec == min || sec == hour) answer++;
        
        while(time < end){
            sec = (time*6)%360;
            min = (time*0.1)%360;
            hour = (time/120)%360;
            
            time++;
            ns = (time*6)%360 == 0 ? 360 : (time*6)%360;
            nm = (time*0.1)%360 == 0 ? 360 : (time*0.1)%360;
            nh = (time/120)%360 == 0 ? 360 : (time/120)%360;
            
            if(sec < hour && nh <= ns) answer++;
            if(sec < min && nm <= ns) answer++;
            if(ns == nm && ns == nh) answer--;
        }
        
        return answer;
    }
}