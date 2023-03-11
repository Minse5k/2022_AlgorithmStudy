import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int len = enemy.length;
        int sum = 0;
        
        for(int i=0; i<len; i++) {
            pq.add(enemy[i]);
            if(pq.size() > k) {
                sum+=pq.poll();
            }
            
            if(sum > n) {
                return i;
            }
        }
        
        return len;
    }
}
