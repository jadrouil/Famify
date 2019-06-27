package jerm.famify;

import com.wrapper.spotify.model_objects.specification.Track;

import java.util.ArrayList;
import java.util.List;

public class DumbCurator implements ICurator {
    public List<Track> selectTracks(int numTracks, String[] users) {
        return new ArrayList<>();
    }

}
