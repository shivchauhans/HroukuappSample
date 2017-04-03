package perx.artistsampleapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shiv on 8/2/17.
 */
public class GetResultsArtistsPojo {

    @SerializedName("artists")
    ArrayList<ArtistPojo> artistListingArray;

    public ArrayList<ArtistPojo> getArtistListingArrayList() {
        return artistListingArray;
    }
}
