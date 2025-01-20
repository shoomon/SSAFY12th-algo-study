//25.01.19
//설계: 분
//구현: 분
//그리디
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<int[]> reservations = new PriorityQueue<>((o1,o2)->{return o1[0] == o2[0] ? o1[1]-o2[1] : o1[0]-o2[0];});
        PriorityQueue<Integer> rooms = new PriorityQueue<>((o1,o2) -> o2-o1);
        
        for(String[] book : book_time){
            String[] startT = book[0].split(":");
            String[] endT = book[1].split(":");
            
            int start = 60*Integer.parseInt(startT[0]) + Integer.parseInt(startT[1]);
            int end = 60*Integer.parseInt(endT[0]) + Integer.parseInt(endT[1]);
            // if(start > end) end += 1440;
            reservations.offer(new int[] {start, end});
        }
        
        while(!reservations.isEmpty()){
            int[] cur = reservations.poll();
            if(!rooms.isEmpty()){
                int size = rooms.size();
                boolean flag = false;
                ArrayList<Integer> buffer = new ArrayList<>();
                for(int i = 0; i < size; i++){
                    int roomEnd = rooms.poll();
                    if(roomEnd <= cur[0]){
                        buffer.add(cur[1]+10);
                        flag = true;
                        break;
                    }else{
                        buffer.add(roomEnd);
                    }
                }
                if(!flag) buffer.add(cur[1]+10);
                
                for(int i : buffer) rooms.offer(i);
            }else{
                rooms.offer(cur[1]+10);
            }
            // System.out.print(cur[0]+" "+cur[1]);
            // for(int i : rooms) System.out.print(" r:"+i+"/");
            // System.out.println();
        }
        // for(int i : rooms) System.out.print(i+" ");
        return rooms.size();
    }
}