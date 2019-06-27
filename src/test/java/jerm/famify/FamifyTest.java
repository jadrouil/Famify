package jerm.famify;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class FamifyTest {

    private ICurator mockCurator;
    private IPlaylistController mockPlaylist;
    private static final String[] userKeys = {"papa-bear", "Idea-guy", "bchen", "karebear"};
    private List<String> tracks;
    private Famify undertest;

    @Before
    public void setup() {
        mockCurator = mock(ICurator.class);
        mockPlaylist = mock(IPlaylistController.class);
        tracks = new ArrayList<>();
        tracks.add("Track-1");
        tracks.add("Track-2");

        undertest = new Famify(userKeys, mockCurator, mockPlaylist);
        // For now we will hard code 20 tracks, but I suppose that could be parameterized.
        when(mockCurator.selectTracks(20, userKeys)).thenReturn(tracks);

    }

    @After
    public void teardown() {

    }

    @Test
    public void FamifyGetsCurated20TracksAndUpdatesPlaylistWhenItExists() {
        when(mockPlaylist.playlistExists()).thenReturn(true);

        undertest.createOrUpdatePlaylist();

        verify(mockCurator).selectTracks(20, userKeys);
        verify(mockPlaylist).playlistExists();
        verify(mockPlaylist).setPlaylist(tracks);
    }

    @Test
    public void FamifyGetsCurated20TracksAndCreatesPlaylistIfItDoesntExist() {
        when(mockPlaylist.playlistExists()).thenReturn(false);

        undertest.createOrUpdatePlaylist();

        verify(mockCurator).selectTracks(20, userKeys);
        verify(mockPlaylist).playlistExists();
        verify(mockPlaylist).createPlaylist();
        verify(mockPlaylist).setPlaylist(tracks);
    }
}
