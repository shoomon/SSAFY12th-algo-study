import java.util.*;

class Solution {
    public List<Integer> solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        HashMap<Integer, Integer> inRecord = new HashMap<>();
        TreeMap<Integer, Integer> totalTime = new TreeMap<>();
        int maxTime = 1439; 
        
        for(String record : records){
            String[] s = record.split(" ");
            int min = convertTimeToMin(s[0]);
            int car = Integer.parseInt(s[1]);
            
            if("IN".equals(s[2])){
                inRecord.put(car,min);
            }else{
                int time = min-inRecord.get(car);
                int timeSum = totalTime.getOrDefault(car,0)+time;
                inRecord.remove(car);
                
                totalTime.put(car, timeSum);
            }
        }
        
        for(int key : inRecord.keySet()){
            int time = maxTime-inRecord.get(key);
            int timeSum = totalTime.getOrDefault(key,0)+time;
                
            totalTime.put(key, timeSum);
        }
        
        for(int key : totalTime.keySet()){
            int time = totalTime.get(key);
            
             if(time <= fees[0]){
                 answer.add(fees[1]);
             }else{
                 time -= fees[0];
                 int additional = (int)Math.ceil((double)time/fees[2])*fees[3];
                 
                 answer.add(fees[1]+additional);
             }
        }
        
        return answer;
    }
    
    static int convertTimeToMin(String time){
        String[] t = time.split(":");
        int hour = Integer.parseInt(t[0]);
        int min = Integer.parseInt(t[1]);
        
        return hour*60 + min;
    }
}