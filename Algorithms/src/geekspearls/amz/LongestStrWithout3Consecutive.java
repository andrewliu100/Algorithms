package geekspearls.amz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class LongestStrWithout3Consecutive {

    class Entry{
        int val;
        char letter;
        public Entry(char c, int val){
            this.val = val;
            this.letter = c;
        }
    }

    /**
     * O(KlogN) K is the max value of chars, N is the number of chars
     */
    public String generateString(Map<Character, Integer> map) {
        PriorityQueue<Entry> maxHeap = new PriorityQueue<>((a, b) -> b.val - a.val);

        maxHeap.addAll(map.entrySet().stream().map(entry -> new Entry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));

        StringBuilder sb = new StringBuilder();

        StringBuilder res = new StringBuilder();
        while(!maxHeap.isEmpty()){
            Entry first = maxHeap.poll();
            // System.out.println(res.toString());
            if(res.length() != 0 && res.charAt(res.length() - 1) == first.letter){
                if(maxHeap.isEmpty()){
                    return res.toString();
                } else{
                    Entry second = maxHeap.poll();
                    res.append(second.letter);
                    second.val--;
                    if(second.val != 0){
                        maxHeap.offer(second);
                    }
                    maxHeap.offer(first);
                }
            } else{
                int limit = Math.min(2, first.val);
                for(int i = 0; i < limit; i++){
                    res.append(first.letter);
                    first.val--;
                }
                if(first.val != 0){
                    maxHeap.offer(first);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LongestStrWithout3Consecutive l = new LongestStrWithout3Consecutive();
        Map<Character, Integer> map = new HashMap<>();

        map.put('A', 1);
        map.put('B', 1);
        map.put('C', 6);
        System.out.println("[A = 1, B = 1, C = 6]");
        System.out.println(l.generateString(map));
        System.out.println();

        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        System.out.println("[A = 1, B = 2, C = 3]");
        System.out.println(l.generateString(map));
        System.out.println();


        map.put('A', 3);
        map.put('B', 3);
        map.put('C', 3);
        System.out.println("[A = 3, B = 3, C = 3]");
        System.out.println(l.generateString(map));
        System.out.println();

        map.put('A', 1);
        map.put('B', 1);
        map.put('C', 9);
        System.out.println("[A = 1, B = 1, C = 9]");
        System.out.println(l.generateString(map));
        System.out.println();

        map.put('A', 10);
        map.put('B', 3);
        map.put('C', 2);
        System.out.println("[A = 10, B = 3, C = 2]");
        System.out.println(l.generateString(map));
        System.out.println();
    }
}
