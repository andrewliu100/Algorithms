package geekspearls.atlas;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RateLimiterTest {

    private RateLimiter rateLimiter;

    @BeforeMethod
    public void init() {
        rateLimiter = new RateLimiter(100, 1000);
    }

    @Test
    public void testAllowedFirstRequest() {

        // when
        boolean isAllowed = rateLimiter.isAllowed("request1");

        // then
        Assert.assertTrue(isAllowed);
    }

    @Test
    public void testAllowedLastRequestInTimeLimit() {
        // given
        for (int i = 0; i < 99; i++) {
            rateLimiter.isAllowed("request1");
        }

        // when
        boolean isAllowed = rateLimiter.isAllowed("request1");

        // then
        Assert.assertTrue(isAllowed);
    }

    @Test
    public void testNotAllowedRequestInTimeLimit() {
        // given
        for (int i = 0; i < 100; i++) {
            rateLimiter.isAllowed("request1");
        }

        // when
        boolean isAllowed = rateLimiter.isAllowed("request1");

        // then
        Assert.assertFalse(isAllowed);
    }

    @Test
    public void testAllowedRequestOutsideTimeLimit() throws InterruptedException {
        // given
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> rateLimiter.isAllowed("request1"));
            threadList.add(thread);
            thread.start();
        }

        // when
        for (Thread thread : threadList) {
            thread.join();
        }
        Thread.sleep(1000);
        boolean isAllowed = rateLimiter.isAllowed("request1");

        // then
        Assert.assertTrue(isAllowed);
    }
}
