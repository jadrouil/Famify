package jerm.famify;

import java.util.List;

public class Famify {
    private String[] users;
    private ICurator curator;
    private IPlaylistController playlist;

    public Famify(String[] friends, ICurator cur, IPlaylistController con) {
        users = friends;
        curator = cur;
        playlist = con;
    }

    public void createOrUpdatePlaylist() {
        System.out.println("Selecting Songs From Friends: " + users.toString());
        final List<String> tracks = curator.selectTracks(20, users);
        if (!playlist.playlistExists()) {
            playlist.createPlaylist();
        }
        playlist.setPlaylist(tracks);
    }
}
