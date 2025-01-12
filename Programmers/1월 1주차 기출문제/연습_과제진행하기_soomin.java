//25.01.12
import java.util.*;
class Solution {
    static class Task implements Comparable<Task>{
        String name;
        int start, playtime;
        public Task(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Task o){
            return this.start - o.start;
        }
    }
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Stack<Task> subTask = new Stack<>();
        PriorityQueue<Task> mainTask = new PriorityQueue<>();

        for(int i = 0; i < plans.length; i++){
            int h = Integer.parseInt(plans[i][1].substring(0,2));
            int m = Integer.parseInt(plans[i][1].substring(3,5));

            int start = h*60 + m;
            int playtime = Integer.parseInt(plans[i][2]);
            mainTask.offer(new Task(plans[i][0],start,playtime));
        }

        while(!mainTask.isEmpty()){
            Task cur = mainTask.poll();
            Task next;
            int time = cur.start;

            if(!mainTask.isEmpty()){
                next = mainTask.peek();
                //task를 끝낼 수 있는 경우
                if(time + cur.playtime <= next.start){
                    time += cur.playtime;
                    answer.add(cur.name);
                    //남은 시간은 멈춘 과제 수행
                    while(!subTask.isEmpty()){
                        cur = subTask.pop();
                        if(time+cur.playtime <= next.start){ //다 끝낼 수 있음
                            time += cur.playtime;
                            answer.add(cur.name);
                        }else{ //다 못 끝내면 다시 push
                            cur.playtime -= (next.start-time);
                            time = next.start;
                            subTask.push(new Task(cur.name,cur.start,cur.playtime));
                            break;
                        }
                    }
                }else {
                    cur.playtime -= (next.start-time);
                    time = next.start;
                    subTask.push(new Task(cur.name,cur.start,cur.playtime));
                }
            }else{
                answer.add(cur.name);
                while(!subTask.isEmpty()){
                    cur = subTask.pop();
                    answer.add(cur.name);
                }
            }
        }

        return answer;
    }
}