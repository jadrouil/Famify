package jerm.famify;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Famify f = new Famify(args[0], Arrays.asList(Arrays.copyOfRange(args, 1, args.length)));
        f.createOrUpdatePlaylist();
    }
}
