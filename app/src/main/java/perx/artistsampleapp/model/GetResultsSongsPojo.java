package perx.artistsampleapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shiv on 8/2/17.
 */
public class GetResultsSongsPojo {

    @SerializedName("songs")
    ArrayList<SongsPojo> songsPojoArrayList;

    public ArrayList<SongsPojo> getAlbumbSongsListingPojoArrayList() {
        return songsPojoArrayList;
    }
}
