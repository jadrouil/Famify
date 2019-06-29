package jerm.famify;

import java.util.ArrayList;
import java.util.List;

public class DumbSongRetriever implements ISongRetriever {
    public List<RealCurator.FamTrack> getUsersTracks(final String accessKey, final int numTracks) {
        return new ArrayList<>();
    }

}
