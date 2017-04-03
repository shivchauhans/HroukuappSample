package perx.artistsampleapp.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import perx.artistsampleapp.R;
import perx.artistsampleapp.common.Constants;
import perx.artistsampleapp.common.ItemClickSupport;
import perx.artistsampleapp.common.Utils;
import perx.artistsampleapp.model.AlbumsPojo;
import perx.artistsampleapp.model.Event;
import perx.artistsampleapp.model.managers.ModelManager;
import perx.artistsampleapp.view.activities.ContainerActivity;
import perx.artistsampleapp.view.adapters.AlbumsListAdapter;
import perx.artistsampleapp.view.adapters.ArtistListAdapter;

public class ArtistDetailsFragment extends Fragment {
    private RecyclerView mRvArtistAlbumbsList;
    private TextView mTvName, mTvWebsiteName;
    private RelativeLayout mLayParent;
    String artist_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_artistdetails, container, false);
        init(view);
        return view;
    }

    /**
     * This method initialize views attached with this layout
     *
     * @param view A variable of View type represents the parant view
     */
    private void init(View view) {
        mRvArtistAlbumbsList = (RecyclerView) view.findViewById(R.id.rv_albumbs);
        mTvName = (TextView) view.findViewById(R.id.tv_artist_name);
        mTvWebsiteName = (TextView) view.findViewById(R.id.tv_artist_website);
        mLayParent = (RelativeLayout) view.findViewById(R.id.lay_parent);

        getDataAndUpdateUi();
    }

    /**
     * This method get the data as bundle and update the respective UI
     */
    private void getDataAndUpdateUi() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            artist_id = bundle.getString("id");
            String name = bundle.getString("name");
            String websitename = bundle.getString("website");
            mTvName.setText(name);
            mTvWebsiteName.setText(websitename);
            callApiToFetchArtistAlbums(artist_id);

        }


    }

    /**
     * This method calls the getArtists functions which interact with the server to fetch list of albums
     *
     * @param artist_id A variable of type String holds the id of artist
     */
    private void callApiToFetchArtistAlbums(String artist_id) {
        if (Utils.isNetworkAvailable(getActivity())) {
            Utils.showSnackBar(mLayParent, getString(R.string.wait_message));
            ModelManager.getInstance().getArtistManager().getArtistAlbumbs(artist_id);
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
                //set adapter after results is fetched from api /update ui
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
        final ArrayList<AlbumsPojo> artistAlbumbsListingPojoArrayList = ModelManager.getInstance().getCacheManager().getArtistAlbumbsListingPojoArrayList();
        mRvArtistAlbumbsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (artistAlbumbsListingPojoArrayList.size() > 0) {
            mRvArtistAlbumbsList.setAdapter(new AlbumsListAdapter(getActivity(), artistAlbumbsListingPojoArrayList));
        } else {
            Utils.showSnackBar(mLayParent, getString(R.string.no_records));
            mRvArtistAlbumbsList.setAdapter(new ArtistListAdapter(getActivity(), new ArrayList()));
        }
        //add item click listner and replace the fragment by passing is,artist_id and album_id to   fetch songs  of respective artist and album
        ItemClickSupport.addTo(mRvArtistAlbumbsList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                AlbumSongsListingFragment albumSongsListingFragment = new AlbumSongsListingFragment();
                Bundle bundle = new Bundle();
                bundle.putString("artist_id", artist_id);
                bundle.putString("album_id", artistAlbumbsListingPojoArrayList.get(position).getId());
                albumSongsListingFragment.setArguments(bundle);
                ((ContainerActivity) getActivity()).onClickFragment(albumSongsListingFragment, "SongListFragment");
            }
        });
    }
}
