package jerm.famify;

import java.util.List;

public interface ISongRetriever {
    List<RealCurator.FamTrack> getUsersTracks(final String accessKey, final int numTracks);
}
