package jerm.famify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyWrappers {

    public static List<String> getUserTracks(String accessToken, int num) {
        final SpotifyApi spotify = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        final GetUsersTopTracksRequest topTracksReq = spotify.getUsersTopTracks().build();

        List<String> ret = new ArrayList<>();
        try {
            final Paging<Track> trackPaging = topTracksReq.execute();
            assert trackPaging.getTotal() >= 5;
            final Track[] tracks = trackPaging.getItems();
            System.out.println("Track:" + tracks[0].toString());

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error getting tracks: " + e.getCause().getMessage());
        }

        return ret;
    }
}
