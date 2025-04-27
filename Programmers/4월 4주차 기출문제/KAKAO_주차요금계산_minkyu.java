import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 차량별 입출차 시간 기록
        Map<String, List<Integer>> parkingRecords = new HashMap<>();
        int maxTime = 23 * 60 + 59;
        
        // 입출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            String timeStr = parts[0];
            String carNum = parts[1];
            
            // 시간 변환
            String[] timeParts = timeStr.split(":");
            int minutes = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
            
            // 차량별 시간 기록 추가
            parkingRecords.computeIfAbsent(carNum, k -> new ArrayList<>()).add(minutes);
        }
        
        // 차량별 요금 계산
        Map<String, Integer> feesByCar = new HashMap<>();
        for (String carNum : parkingRecords.keySet()) {
            List<Integer> times = parkingRecords.get(carNum);
            int totalTime = 0;
            
            // 총 주차 시간 계산
            for (int i = 0; i < times.size(); i += 2) {
                int inTime = times.get(i);
                int outTime = (i + 1 < times.size()) ? times.get(i + 1) : maxTime;
                totalTime += outTime - inTime;
            }
            
            // 요금 계산
            int fee = fees[1];
            if (totalTime > fees[0]) {
                int extraTime = totalTime - fees[0];
                int extraUnits = (int) Math.ceil((double) extraTime / fees[2]);
                fee += extraUnits * fees[3];
            }
            
            feesByCar.put(carNum, fee);
        }
        
        // 차량 번호순으로 정렬 후 결과 배열 생성
        List<String> sortedCars = new ArrayList<>(feesByCar.keySet());
        Collections.sort(sortedCars);
        
        int[] result = new int[sortedCars.size()];
        for (int i = 0; i < sortedCars.size(); i++) {
            result[i] = feesByCar.get(sortedCars.get(i));
        }
        
        return result;
    }
}