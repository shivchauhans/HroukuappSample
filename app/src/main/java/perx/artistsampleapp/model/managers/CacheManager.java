package perx.artistsampleapp.model.managers;


import java.util.ArrayList;

import perx.artistsampleapp.model.AlbumsPojo;
import perx.artistsampleapp.model.ArtistPojo;
import perx.artistsampleapp.model.SongsPojo;

/**
 * Created by shiv on 22/1/17.
 */
public class CacheManager {

    private ArrayList<ArtistPojo> artistListingPojoArrayList;
    private ArrayList<AlbumsPojo> artistAlbumbsListingPojoArrayList;
    private ArrayList<SongsPojo> songsPojoArrayList;

    /**
     * This method sets the artist list inside collection
     *
     * @param artistListingPojoArrayList A collection of type ArtistPojo
     */
    public void setArtistListingPojoArrayList(ArrayList<ArtistPojo> artistListingPojoArrayList) {
        this.artistListingPojoArrayList = artistListingPojoArrayList;
    }

    /**
     * This method returns the arraylist which holds the list of albumbs
     *
     * @return artistAlbumbsListingPojoArrayList
     */
    public ArrayList<AlbumsPojo> getArtistAlbumbsListingPojoArrayList() {
        return artistAlbumbsListingPojoArrayList;
    }

    /**
     * This method sets the artist albums list inside collection
     *
     * @param artistAlbumbsListingPojoArrayList A collection of type AlbumsPojo
     */
    public void setArtistAlbumbsListingPojoArrayList(ArrayList<AlbumsPojo> artistAlbumbsListingPojoArrayList) {
        this.artistAlbumbsListingPojoArrayList = artistAlbumbsListingPojoArrayList;
    }

    /**
     * This method returns the arraylist which holds the list of songs
     *
     * @return songsPojoArrayList
     */
    public ArrayList<SongsPojo> getSongsPojoArrayList() {
        return songsPojoArrayList;
    }

    /**
     * This method sets the album's songs list inside collection
     *
     * @param songsPojoArrayList A collection of type SongsPojo
     */
    public void setSongsPojoArrayList(ArrayList<SongsPojo> songsPojoArrayList) {
        this.songsPojoArrayList = songsPojoArrayList;
    }

    /**
     * This method returns the arraylist which holds the list of artists
     *
     * @return artistListingPojoArrayList
     */
    public ArrayList<ArtistPojo> getArtistListingPojoArrayList() {
        return artistListingPojoArrayList;
    }

}
