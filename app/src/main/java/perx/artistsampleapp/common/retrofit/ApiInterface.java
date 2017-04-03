package perx.artistsampleapp.common.retrofit;


import perx.artistsampleapp.model.GetResultsAlbumsPojo;
import perx.artistsampleapp.model.GetResultsArtistsPojo;
import perx.artistsampleapp.model.GetResultsSongsPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shiv on 6/2/17.
 */
public interface ApiInterface {


    /**
     * get artist list as results
     *
     * @return Call<GetResultsArtistsPojo> callback for response
     */
    @GET("artists.json")
    Call<GetResultsArtistsPojo> getArtistData();


    /**
     * get album list as results according to user query
     *
     * @param artist_id
     * @return Call<GetResultsLocationPojo> callback for response
     */

    @GET("albums.json?")
    Call<GetResultsAlbumsPojo> getAlbumbsData(@Query("artist_id") String artist_id);


    /**
     * get songs list as results according to user query
     *
     * @param album_id
     * @return Call<GetResultsLocationPojo> callback for response
     */
    @GET("songs.json?")
    Call<GetResultsSongsPojo> getSongsData(@Query("artist_id") String artist_id, @Query("album_id") String album_id);

}
