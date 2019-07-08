package jerm.famify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main(String[] args ) {

        Famify.PlaylistOp playlist = (owner, tracks) -> {
            // No-op for now.
        };

        Famify.UserTrackLookup user = (userKey, num) -> {
            List<String> tracks = new ArrayList<>(Arrays.asList());
            return tracks;
        };

        Famify f = new Famify(
                args,
                playlist,
                user
        );
        f.createOrUpdatePlaylist();
    }
}
