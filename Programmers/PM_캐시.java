import java.util.*;
import java.util.Map.Entry;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int N = cities.length;
        if(cacheSize == 0) return N*5;

        Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        };
        int time = 0;
        Map<String, Integer> hm = new HashMap<String, Integer>();

        for(int i=0; i<N; i++) {
            String city = cities[i].toUpperCase();
            boolean hasKey = hm.containsKey(city);
            if(!hasKey && hm.size() >= cacheSize) {
                Entry<String, Integer> minEntry = Collections.min(hm.entrySet(), comparator);
                hm.remove(minEntry.getKey());
            }
            time += hasKey ? 1 : 5;
            hm.put(city, i+1);
        }
        return time;
    }
}
