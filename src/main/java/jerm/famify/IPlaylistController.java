package jerm.famify;

import com.wrapper.spotify.model_objects.specification.Track;

import java.util.List;

public interface IPlaylistController {

    boolean playlistExists();

    void createPlaylist();

    void setPlaylist(List<String> songs);

}
