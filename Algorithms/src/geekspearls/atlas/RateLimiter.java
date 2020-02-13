package geekspearls.atlas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {

    private int requestLimit;
    private long timeLimit;

    public RateLimiter(int requestLimit, long timeLimit) {
        this.requestLimit = requestLimit;
        this.timeLimit = timeLimit;
    }

    Map<String, Deque<Long>> hitCounter = new ConcurrentHashMap<>();

    public boolean isAllowed(String requestId) {
        if (!hitCounter.containsKey(requestId)) {
            hitCounter.put(requestId, new ArrayDeque<>());
        }
        Deque<Long> hits = hitCounter.get(requestId);
        Long now = System.currentTimeMillis();
        if (hits.isEmpty()) {
            hits.addLast(now);
        } else {
            // evict old hits earlier than time limit
            while (!hits.isEmpty() && now - hits.getFirst() > timeLimit) {
                hits.removeFirst();
            }
            if (hits.size() >= requestLimit) {
                // request not allowed
                System.out.println(requestId + " is not allowed. Request hit counts is " + hits.size());
                return false;
            } else {
                // request allowed
                // add the current request timestamp
                System.out.println(requestId + " is allowed. Request hit counts is " + hits.size());
                hits.addLast(now);
            }
        }
        return true;
    }
}
