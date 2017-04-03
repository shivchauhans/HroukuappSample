package perx.artistsampleapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shiv on 1/4/17.
 */
public class AlbumsPojo {

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @SerializedName("id")

    public String id;

    @SerializedName("year")
    public String year;

}
