package com.apple;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SongCacheImpl implements SongCache {
    Map<String, Integer> songMap;

    public SongCacheImpl() {
        songMap = new ConcurrentHashMap<>();
    }

    /**
     * Record number of plays for a song.
     */
    public void recordSongPlays(String songId, int numPlays) {
        if (!songMap.containsKey(songId)) {
            songMap.put(songId, numPlays);
        } else {
            songMap.put(songId, songMap.get(songId) + numPlays);
        }
    }

    /**
     * Fetch the number of plays for a song.
     *
     * @return the number of plays, or -1 if the
     * song ID is unknown.
     */
    public int getPlaysForSong(String songId) {
        if (songMap.containsKey(songId)) {
            return songMap.get(songId);
        } else {
            return -1;
        }
    }

    /**
     * Return the top N songs played, in descending
     * order of number of plays.
     */
    public List<String> getTopNSongsPlayed(int n) {
        return songMap
                .entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(o -> o.getKey())
                .limit(n)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);
        cache.recordSongPlays("ID-4", 9);
        cache.recordSongPlays("ID-3", 15);

        assert(cache.getPlaysForSong("ID-1") == 4);
        assert(cache.getPlaysForSong("ID-3") == 20);
        assert(cache.getPlaysForSong("ID-9") == -1);

        System.out.println(cache.getTopNSongsPlayed(2));
        assert(cache.getTopNSongsPlayed(0).isEmpty());
    }
}

