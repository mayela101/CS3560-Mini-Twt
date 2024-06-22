import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Feed class represents a collection of tweets and provides
 * methods to add tweets and retrieve them in reverse chronological order.
 */
public class Feed {
    private final Map<Integer, Tweet> tweetMap;


    
    public class Tweet 
    {

    }

    /**
     * Constructs a new {@code Feed} object with an empty tweet map.
     */
    public Feed() {
        tweetMap = new HashMap<>();
    }

    /**
     * Adds a tweet to the feed. The tweet is associated with a unique identifier.
     *
     * @param tweet the tweet to add
     */
    public void addToFeed(Tweet tweet) {
        int tweetId = 0; // This should be replaced with a proper unique ID generator
        tweetMap.put(tweetId, tweet);
    }

    /**
     * Retrieves a list of tweets in reverse chronological order.
     *
     * @return a list of tweets sorted by their identifiers in descending order
     */
    public List<Tweet> getRevChronoTweetList() {
        List<Tweet> tweetList = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Tweet>> maxHeap = new PriorityQueue<>((a, b) -> b.getKey() - a.getKey());

        for (Map.Entry<Integer, Tweet> entry : tweetMap.entrySet()) {
            maxHeap.offer(entry);
        }

        while (!maxHeap.isEmpty()) {
            tweetList.add(maxHeap.poll().getValue());
        }

        return tweetList;
    }
}