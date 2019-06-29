package jerm.famify;

import java.util.*;

public class RealCurator implements ICurator{

    public static class FamTrack  implements Comparable<FamTrack> {
        public final String id;
        public final int plays;

        FamTrack(final String s_id, final int s_plays) {
            id = s_id;
            plays = s_plays;
        }

        @Override
        public int compareTo(FamTrack o) {
            return plays < o.plays ?
                    -1
                    : plays > o.plays ? 1 : 0;
        }
    }


    private final ISongRetriever retriever;

    public RealCurator(ISongRetriever lookerup) {
        retriever = lookerup;
    }

    public List<String> selectTracks(int numTracks, String[] users) {
        List<String> selections = new ArrayList<>(numTracks);

        List<List<FamTrack>> crossUserCollection = new ArrayList<>(users.length);
        for (String user: users) {
            // 10 is a default. We don't ask for more for now.
            List<FamTrack> playsByUser = retriever.getUsersTracks(user, 10);
            Collections.sort(playsByUser, Collections.<FamTrack>reverseOrder());
            crossUserCollection.add(playsByUser);
        }

        int head = 0;
        int tail = 9;
        boolean useHead = true;
        int userIndex = 0;

        while (selections.size() != numTracks) {
            FamTrack select;

            int selectIndex = useHead ? head : tail;
            select = crossUserCollection.get(userIndex++).get(selectIndex);
            selections.add(select.id);

            if (userIndex >= users.length) {
                if (useHead) {
                    head++;
                }
                else {
                    tail--;
                }
                useHead = !useHead;
                userIndex = 0;
            }

        }
        return selections;
    }
}
