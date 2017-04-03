package perx.artistsampleapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shiv on 1/4/17.
 */
public class ArtistPojo {

    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public String id;

    @SerializedName("website")
    public String website;

    @SerializedName("href")
    public String href;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
