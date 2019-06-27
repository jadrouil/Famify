package jerm.famify;

import java.util.List;

public class Famify {
    private String leaderToken;
    private List<String> friendsTokens;

    public Famify(String head, List<String> friends) {
        leaderToken = head;
        friendsTokens = friends;
    }

    public void createOrUpdatePlaylist() {
        System.out.println("Creating Playlist with Token:  " + leaderToken);
        System.out.println("Selecting Songs From: " + leaderToken + " & Friends: " + friendsTokens.toString());
    }
}
