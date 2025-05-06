import java.util.*;
class Solution {
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    public int[] solution(int[] fees, String[] records) {
        func(records); 
        List<String> list = func2();
        return func3(list, fees);
    }
    public static void func(String [] records){
        for(String target : records){
            String [] split = target.split(" ");
            int time = calculate(split[0]); // 시간 계산 
            String carNum = split[1]; // 차량 번호 
            if(map.containsKey(carNum)){
                map.get(carNum).add(time);
            }
            else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(time);
                map.put(carNum, temp);
            }
        }
    }
    public static int calculate(String time){
        String [] split = time.split(":");
        int cur = 0;
        cur += Integer.parseInt(split[0]) * 60;  // 시
        cur += Integer.parseInt(split[1]);       // 분 
        return cur;
    }
    
    public static List<String> func2(){
        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list); // 차량 번호가 작은 자동차 순서
        return list;
    }
    
    public static int [] func3(List<String> list, int [] fees){
        // fees 0: 기본시간 1: 기본요금 2: 단위시간 3: 단위요금 
        int insert = (23 * 60) + 59;
        int [] answer = new int [list.size()];
        int cnt = 0;
        for(String key : list){
            List<Integer> temp = map.get(key);
            if(temp.size() % 2 == 1) temp.add(insert);
            int fee = 0;
            int time = 0;
            for(int i = 0; i < temp.size(); i+=2){
                time += temp.get(i+1) - temp.get(i);
            }
            time -= fees[0]; // 기본시간
            fee  += fees[1]; // 기본요금     
            if(time > 0){
                fee += (time / fees[2]) * fees[3];
                if(time % fees[2] != 0){
                    fee += fees[3];
                }
            }
            answer[cnt++] = fee;
        }
        return answer;
    }
}
