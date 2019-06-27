package jerm.famify;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.List;


public interface ICurator {
    List<String> selectTracks(int numTracks, String[] users);
}
