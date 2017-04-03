package perx.artistsampleapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shiv on 8/2/17.
 */
public class GetResultsAlbumsPojo {

    @SerializedName("albums")
    ArrayList<AlbumsPojo> artistAlbumbsListingPojoArrayList;

    public ArrayList<AlbumsPojo> getArtistAlbumbsListingPojoArrayList() {
        return artistAlbumbsListingPojoArrayList;
    }
}
