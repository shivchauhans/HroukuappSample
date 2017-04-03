package perx.artistsampleapp.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import perx.artistsampleapp.R;
import perx.artistsampleapp.common.Constants;
import perx.artistsampleapp.common.ItemClickSupport;
import perx.artistsampleapp.common.Utils;
import perx.artistsampleapp.model.ArtistPojo;
import perx.artistsampleapp.model.Event;
import perx.artistsampleapp.model.SongsPojo;
import perx.artistsampleapp.model.managers.ModelManager;
import perx.artistsampleapp.view.activities.ContainerActivity;
import perx.artistsampleapp.view.adapters.ArtistListAdapter;
import perx.artistsampleapp.view.adapters.SongsListAdapter;

public class AlbumSongsListingFragment extends Fragment {

    private RecyclerView mRvSongsList;
    private RelativeLayout mLayParent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_songslisting, container, false);
        init(view);
        return view;
    }

    /**
     * This method initialize views attached with this layout
     *
     * @param view A variable of View type represents the parant view
     */
    private void init(View view) {
        mRvSongsList = (RecyclerView) view.findViewById(R.id.rv_songslist);
        mLayParent = (RelativeLayout) view.findViewById(R.id.lay_parent);
        getDataAndUpdateUi();
    }

    /**
     * This method get the data as bundle and update the respective UI
     */
    private void getDataAndUpdateUi() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String artist_id = bundle.getString("artist_id");
            String album_id = bundle.getString("album_id");

            callApiToFetchSongs(artist_id, album_id);
        }
    }

    /**
     * This method calls the getAlbumbSongs method which interact with the server to fetch list of songs
     *
     * @param artist_id A variable of type String holds the id of artist
     * @param album_id  A variable of type String holds the id of album
     */
    private void callApiToFetchSongs(String artist_id, String album_id) {
        if (Utils.isNetworkAvailable(getActivity())) {
            Utils.showSnackBar(mLayParent, getString(R.string.wait_message));
            ModelManager.getInstance().getArtistManager().getAlbumbSongs(artist_id, album_id);
        } else {
            Utils.showSnackBar(mLayParent, getString(R.string.no_internet));
        }
    }

    /**
     * registers event bus so that it will notified after API call to make UI update
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }


    /**
     * unregister event bus
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }


    /**
     * This method called when Eventbus is triggered after API has been called
     *
     * @param event event object containing information for which it is intended to
     */

    @Subscribe
    public void onEvent(final Event event) {
        switch (event.getKey()) {
            case Constants.SUCCESS:
                //set adapter after results is fetched from api
                setAdapterLocationsAndOnItemClick();
                break;
            case Constants.FAILURE:
                // show a prmpt message to user in case no records have been found
                Utils.showSnackBar(mLayParent, getString(R.string.no_records));
                break;
        }
    }

    /**
     * set adapter on recycler view by adding linear layout  manager , and sets the orientation vertical, so that list will appear vertical
     */
    private void setAdapterLocationsAndOnItemClick() {
        final ArrayList<SongsPojo> artistListingPojoArrayList = ModelManager.getInstance().getCacheManager().getSongsPojoArrayList();
        mRvSongsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (artistListingPojoArrayList.size() > 0) {
            mRvSongsList.setAdapter(new SongsListAdapter(getActivity(), artistListingPojoArrayList));
        } else {
            Utils.showSnackBar(mLayParent, getString(R.string.no_records));
            mRvSongsList.setAdapter(new ArtistListAdapter(getActivity(), new ArrayList()));
        }
    }
}
