package jerm.famify;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RealCuratorTest {
    private ISongRetriever mockRetriever;

    private ICurator undertest;

    @Before
    public void setup() {
        mockRetriever = mock(ISongRetriever.class);
        undertest = new RealCurator(mockRetriever);
    }

    private List<RealCurator.FamTrack> makeUnsortedSongList(int size, String name) {
        List<RealCurator.FamTrack> tracks = new ArrayList<>();
        for (int i = 0; i<size; ++i) {
            tracks.add(new RealCurator.FamTrack(name + i, i));
        }

        return tracks;
    }

    @Test
    public void CuratorGetsHalfPopularSongsAndHalfDeepTracks() {
        final List<RealCurator.FamTrack> tracks = makeUnsortedSongList(10, "song");

        // 10 is the default number of tracks we ask for.
        when(mockRetriever.getUsersTracks("papa-bear", 10))
                .thenReturn(tracks);

        final List<String> picks = undertest.selectTracks(4, new String[]{"papa-bear"});

        verify(mockRetriever).getUsersTracks("papa-bear", 10);
        assertThat(picks, is(Arrays.asList("song9", "song0", "song8", "song1")));
    }

    @Test
    public void CuratorRoundRobinsSelections() {
        final List<RealCurator.FamTrack> tracksA = makeUnsortedSongList(10, "a");
        final List<RealCurator.FamTrack> tracksB = makeUnsortedSongList(10, "b");

        when(mockRetriever.getUsersTracks("a", 10))
                .thenReturn(tracksA);
        when(mockRetriever.getUsersTracks("b", 10))
                .thenReturn(tracksB);

        final List<String> picks = undertest.selectTracks(5, new String[]{"a", "b"});

        verify(mockRetriever).getUsersTracks("a", 10);
        verify(mockRetriever).getUsersTracks("b", 10);
        assertThat(picks, is(Arrays.asList("a9","b9", "a0", "b0", "a8")));
    }
}
