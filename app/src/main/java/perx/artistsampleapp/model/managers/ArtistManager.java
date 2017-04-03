package perx.artistsampleapp.model.managers;

import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import perx.artistsampleapp.common.Constants;
import perx.artistsampleapp.common.retrofit.ApiClient;
import perx.artistsampleapp.common.retrofit.ApiInterface;
import perx.artistsampleapp.model.AlbumsPojo;
import perx.artistsampleapp.model.ArtistPojo;
import perx.artistsampleapp.model.Event;
import perx.artistsampleapp.model.GetResultsAlbumsPojo;
import perx.artistsampleapp.model.GetResultsArtistsPojo;
import perx.artistsampleapp.model.GetResultsSongsPojo;
import perx.artistsampleapp.model.SongsPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shiv on 9/2/17.
 */
public class ArtistManager {

    /**
     * API interaction for fetching the list of artists
     */

    public void getArtists() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GetResultsArtistsPojo> call = apiService.getArtistData();
        call.enqueue(new Callback<GetResultsArtistsPojo>() {
            @Override
            public void onResponse(Call<GetResultsArtistsPojo> call, Response<GetResultsArtistsPojo> response) {

                ArrayList<ArtistPojo> artistListingPojos = new ArrayList<ArtistPojo>();

                artistListingPojos = response.body().getArtistListingArrayList();

                Log.e("size", artistListingPojos.size() + "");

                ModelManager.getInstance().getCacheManager().setArtistListingPojoArrayList(artistListingPojos);

                if (ModelManager.getInstance().getCacheManager().getArtistListingPojoArrayList().size() > 0) {
                    EventBus.getDefault().post(new Event(Constants.SUCCESS, true));
                } else {
                    EventBus.getDefault().post(new Event(Constants.SUCCESS, false));
                }
            }

            @Override
            public void onFailure(Call<GetResultsArtistsPojo> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                EventBus.getDefault().post(new Event(Constants.FAILURE, false));

            }
        });
    }


    /**
     * API interaction for fetching the list of albums as per artist
     *
     * @param id A variable of type String holds the artistId
     */

    public void getArtistAlbumbs(String id) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GetResultsAlbumsPojo> call = apiService.getAlbumbsData(id);
        call.enqueue(new Callback<GetResultsAlbumsPojo>() {
            @Override
            public void onResponse(Call<GetResultsAlbumsPojo> call, Response<GetResultsAlbumsPojo> response) {

                ArrayList<AlbumsPojo> artistListingPojos = new ArrayList<AlbumsPojo>();

                artistListingPojos = response.body().getArtistAlbumbsListingPojoArrayList();

                Log.e("size albumbs", artistListingPojos.size() + "");

                ModelManager.getInstance().getCacheManager().setArtistAlbumbsListingPojoArrayList(artistListingPojos);

                if (ModelManager.getInstance().getCacheManager().getArtistListingPojoArrayList().size() > 0) {
                    EventBus.getDefault().post(new Event(Constants.SUCCESS, true));
                } else {
                    EventBus.getDefault().post(new Event(Constants.SUCCESS, false));
                }
            }

            @Override
            public void onFailure(Call<GetResultsAlbumsPojo> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                EventBus.getDefault().post(new Event(Constants.FAILURE, false));

            }
        });
    }


    /**
     * API interaction for fetching the list of songs as per artistId and albumId
     *
     * @param artist_id A variable of type String holds the artistId
     * @param album_id  A variable of type String holds the albumId
     */
    public void getAlbumbSongs(String artist_id, String album_id) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GetResultsSongsPojo> call = apiService.getSongsData(artist_id, album_id);
        call.enqueue(new Callback<GetResultsSongsPojo>() {
            @Override
            public void onResponse(Call<GetResultsSongsPojo> call, Response<GetResultsSongsPojo> response) {

                ArrayList<SongsPojo> artistListingPojos = new ArrayList();

                artistListingPojos = response.body().getAlbumbSongsListingPojoArrayList();

                Log.e("size songs", artistListingPojos.size() + "");

                ModelManager.getInstance().getCacheManager().setSongsPojoArrayList(artistListingPojos);

                if (ModelManager.getInstance().getCacheManager().getArtistListingPojoArrayList().size() > 0) {
                    EventBus.getDefault().post(new Event(Constants.SUCCESS, true));
                } else {
                    EventBus.getDefault().post(new Event(Constants.SUCCESS, false));
                }
            }

            @Override
            public void onFailure(Call<GetResultsSongsPojo> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                EventBus.getDefault().post(new Event(Constants.FAILURE, false));

            }
        });
    }


}
