package jerm.famify;

import java.util.ArrayList;
import java.util.List;

public class Famify {
    private String[] users;
    private PlaylistOp playlistOps;
    private UserTrackLookup userOps;
    private final static int playlistSize = 20;

    public Famify(String[] friends, PlaylistOp playlist, UserTrackLookup userTracks) {
        users = friends;
        playlistOps = playlist;
        userOps = userTracks;
    }

    public void createOrUpdatePlaylist() {
        System.out.println("Selecting Songs From Friends: " + users.toString());
        List<String> selections = new ArrayList<>(playlistSize);

        List<List<String>> crossUserCollection = new ArrayList<>(users.length);
        for (String user: users) {
            // 10 is a default. We don't ask for more for now.
            List<String> playsByUser = userOps.getTracks(user, 10);
            crossUserCollection.add(playsByUser);
        }

        int head = 0;
        int tail = 9;
        boolean useHead = true;
        int userIndex = 0;

        // We assume that the most popular songs are at the front of each list.
        while (selections.size() != playlistSize) {
            String select;

            int selectIndex = useHead ? head : tail;
            select = crossUserCollection.get(userIndex++).get(selectIndex);
            selections.add(select);

            if (userIndex >= users.length) {
                if (useHead) {
                    head++;
                }
                else {
                    tail--;
                }
                useHead = !useHead;
                userIndex = 0;
            }
        }

        playlistOps.create(users[0], selections);
    }

    public interface PlaylistOp {
        void create(String owner, List<String> tracks);
    }

    public interface UserTrackLookup {
        List<String> getTracks(final String accessKey, final int numTracks);
    }

}
