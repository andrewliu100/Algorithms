package geekspearls.amz.OA;

import sun.security.x509.GeneralName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has
 * listened to as values.
 *
 * Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within
 * that genre as values. !The song can only belong to only one genre.!
 *
 * The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the
 * user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre
 * if he/she has listened to the same number of songs per each of the genres.
 *
 * Example 1:
 *
 * Input:
 * userSongs = {
 *    "David": ["song1", "song2", "song3", "song4", "song8"],
 *    "Emma":  ["song5", "song6", "song7"]
 * },
 * songGenres = {
 *    "Rock":    ["song1", "song3"],
 *    "Dubstep": ["song7"],
 *    "Techno":  ["song2", "song4"],
 *    "Pop":     ["song5", "song6"],
 *    "Jazz":    ["song8", "song9"]
 * }
 *
 * Output: {
 *    "David": ["Rock", "Techno"],
 *    "Emma":  ["Pop"]
 * }
 *
 * Explanation:
 * David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
 * Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
 * Example 2:
 *
 * Input:
 * userSongs = {
 *    "David": ["song1", "song2"],
 *    "Emma":  ["song3", "song4"]
 * },
 * songGenres = {}
 *
 * Output: {
 *    "David": [],
 *    "Emma":  []
 * }
 */
public class FavoriteGenres {

    /**
     * S = total number of songs, G = total number of genres, U = total number of users
     * Time: O(U * (S + G))
     * Space: O(S + G + U*G)
     */
    public static Map<String, List<String>> favoriteGenres(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres) {
        // O(G + S)
        Map<String, String> songToGenre = prepareSongToGenreMap(songGenres);
        // count listened genres
        Map<String, List<String>> favouriteGenres = new HashMap<>();

        // O(U * (S + G))
        for (Map.Entry<String, List<String>> entry : userSongs.entrySet()) {
            String user = entry.getKey();
            List<String> songs = entry.getValue();
            Map<String, Integer> genreCount = new HashMap<>();
            for (String song : songs) {
                String genre = songToGenre.get(song);
                if (genre != null) {
                    int count = genreCount.getOrDefault(genre, 0);
                    genreCount.put(genre, ++count);
                }
            }
            List<String> maxCountGenres = extractMaxCountGenres(genreCount);
            favouriteGenres.put(user, maxCountGenres);
        }
        return favouriteGenres;
    }

    // O(G*S)
    private static Map<String, String> prepareSongToGenreMap(Map<String, List<String>> songGenres) {
        // prepare the song to genre map, because one song only belongs to one genre
        Map<String, String> songToGenre = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : songGenres.entrySet()) {
            String genre = entry.getKey();
            List<String> songs = entry.getValue();
            for (String song : songs) {
                songToGenre.put(song, genre);
            }
        }
        return songToGenre;
    }

    private static List<String> extractMaxCountGenres(Map<String, Integer> genreCounts) {
        int max = -1;
        for (Map.Entry<String, Integer> genreCount : genreCounts.entrySet()) {
            if (genreCount.getValue() > max) {
                max = genreCount.getValue();
            }
        }
        int maxCount = max;
        return genreCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        Map<String, List<String>> userMap = new HashMap<>();
//        List<String> list1 = Arrays.asList("song1", "song2", "song3", "song4", "song8");
//        List<String> list2 = Arrays.asList("song5", "song6", "song7");
//        userMap.put("David", list1);
//        userMap.put("Emma", list2);
//
//        Map<String, List<String>> genreMap = new HashMap<>();
//        List<String> list3 = Arrays.asList("song1", "song3");
//        List<String> list4 = Arrays.asList("song7");
//        List<String> list5 = Arrays.asList("song2", "song4");
//        List<String> list6 = Arrays.asList("song5", "song6");
//        List<String> list7 = Arrays.asList("song8", "song9");
//        genreMap.put("Rock", list3);
//        genreMap.put("Dubstep", list4);
//        genreMap.put("Techno", list5);
//        genreMap.put("Pop", list6);
//        genreMap.put("Jazz", list7);

        Map<String, List<String>> userMap = new HashMap<>();
		List<String> list1 = Arrays.asList("song1", "song2");
		List<String> list2 = Arrays.asList("song3", "song4");
		userMap.put("David", list1);
		userMap.put("Emma", list2);

		Map<String, List<String>> genreMap = new HashMap<>();

        System.out.println(favoriteGenres(userMap, genreMap));
    }
}
