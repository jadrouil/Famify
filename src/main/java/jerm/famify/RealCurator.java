package jerm.famify;

import java.util.ArrayList;
import java.util.List;

public class RealCurator implements ICurator{

    private final ISongRetriever retriever;

    public RealCurator(ISongRetriever lookerup) {
        retriever = lookerup;
    }

    public List<String> selectTracks(int numTracks, String[] users) {
        return new ArrayList<>();
    }

    public static class FamTrack {
        public final String id;
        public final int plays;

        FamTrack(final String s_id, final int s_plays) {
            id = s_id;
            plays = s_plays;
        }
    }

}
