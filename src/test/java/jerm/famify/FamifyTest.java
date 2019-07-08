package jerm.famify;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class FamifyTest {

    private Famify.PlaylistOp mockPlaylist;
    private Famify.UserTrackLookup mockLookup;
    private static final String[] userKeys = {"papa-bear", "Idea-guy", "bchen", "karebear"};
    private List<String> tracks;
    private Famify undertest;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    private List<String> makeSortedSongList(int size, String name) {
        List<String> tracks = new ArrayList<>();
        for (int i = size -1 ; i>=0; --i) {
            tracks.add(name + i);
        }

        return tracks;
    }

    @Test
    public void FamifyGets20MixedPopularityTracksAndCreatesPlaylist() {
        mockLookup = mock(Famify.UserTrackLookup.class);
        mockPlaylist = mock(Famify.PlaylistOp.class);

        when(mockLookup.getTracks("papa-bear", 10))
                .thenReturn(makeSortedSongList(10, "pb"));
        when(mockLookup.getTracks("Idea-guy", 10))
                .thenReturn(makeSortedSongList(10, "ig"));
        when(mockLookup.getTracks("bchen", 10))
                .thenReturn(makeSortedSongList(10, "bc"));
        when(mockLookup.getTracks("karebear", 10))
                .thenReturn(makeSortedSongList(10, "kb"));

        undertest = new Famify(userKeys, mockPlaylist, mockLookup);

        undertest.createOrUpdatePlaylist();

        verify(mockLookup).getTracks("papa-bear", 10);
        verify(mockLookup).getTracks("Idea-guy", 10);
        verify(mockLookup).getTracks("bchen", 10);
        verify(mockLookup).getTracks("karebear", 10);
        verify(mockPlaylist).create("papa-bear", new ArrayList<>(
                Arrays.asList(
                        "pb9",
                        "ig9",
                        "bc9",
                        "kb9",
                        "pb0",
                        "ig0",
                        "bc0",
                        "kb0",
                        "pb8",
                        "ig8",
                        "bc8",
                        "kb8",
                        "pb1",
                        "ig1",
                        "bc1",
                        "kb1",
                        "pb7",
                        "ig7",
                        "bc7",
                        "kb7"
                )
        ));
    }

}
