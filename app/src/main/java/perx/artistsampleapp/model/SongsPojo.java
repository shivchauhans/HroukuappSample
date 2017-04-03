package perx.artistsampleapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shiv on 1/4/17.
 */
public class SongsPojo {

    @SerializedName("title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @SerializedName("id")

    public String id;

}
