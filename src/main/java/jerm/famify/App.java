package jerm.famify;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Famify f = new Famify(
                args,
                new DumbCurator(),
                new DumbPlaylistController()
        );
        f.createOrUpdatePlaylist();
    }
}
