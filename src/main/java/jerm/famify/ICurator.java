package jerm.famify;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.List;


public interface ICurator {
    List<Track> selectTracks(int numTracks, String[] users);
}
