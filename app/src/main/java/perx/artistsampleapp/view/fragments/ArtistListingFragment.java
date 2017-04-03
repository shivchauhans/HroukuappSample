package perx.artistsampleapp.view.fragments;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import perx.artistsampleapp.model.managers.ModelManager;
import perx.artistsampleapp.view.activities.ContainerActivity;
import perx.artistsampleapp.view.adapters.ArtistListAdapter;

public class ArtistListingFragment extends Fragment {

    private RecyclerView mRvArtistist;
    private RelativeLayout mLayParent;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_artistlisting, container, false);
        init(view);
        return view;
    }

    /**
     * This method initialize views attached with this layout
     *
     * @param view A variable of View type represents the parant view
     */
    private void init(View view) {
        mRvArtistist = (RecyclerView) view.findViewById(R.id.rv_artistslist);
        mLayParent = (RelativeLayout) view.findViewById(R.id.lay_parent);
        if (checkPermission())
            callApiToFetchArtists();
    }

    /**
     * This method calls the getArtists functions which interact with the server to fetch list of artists
     */
    private void callApiToFetchArtists() {
        if (Utils.isNetworkAvailable(getActivity())) {
            Utils.showSnackBar(mLayParent, getString(R.string.wait_message));
            ModelManager.getInstance().getArtistManager().getArtists();
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
        final ArrayList<ArtistPojo> artistListingPojoArrayList = ModelManager.getInstance().getCacheManager().getArtistListingPojoArrayList();
        mRvArtistist.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (artistListingPojoArrayList.size() > 0) {
            mRvArtistist.setAdapter(new ArtistListAdapter(getActivity(), artistListingPojoArrayList));
        } else {
            Utils.showSnackBar(mLayParent, getString(R.string.no_records));
            mRvArtistist.setAdapter(new ArtistListAdapter(getActivity(), new ArrayList()));
        }
        //add item click listner and replace the fragment by passing is,name and webiste to set artist details and fetch albums of resppective artist
        ItemClickSupport.addTo(mRvArtistist).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ArtistDetailsFragment artistDetailsFragment = new ArtistDetailsFragment();
                Bundle bundle = new Bundle();
                artistDetailsFragment.setArguments(bundle);
                bundle.putString("id", artistListingPojoArrayList.get(position).getId());
                bundle.putString("name", artistListingPojoArrayList.get(position).getName());
                bundle.putString("website", artistListingPojoArrayList.get(position).getWebsite());
                ((ContainerActivity) getActivity()).onClickFragment(artistDetailsFragment, "ArtistDetailsFragment");
            }
        });
    }


    /**
     * @return true if already have permission in marshmallow
     */
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_NETWORK_STATE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermission();
            return false;
        }
    }

    /**
     * If network state is not enabled, it requests for
     * the network state permission to be enabled
     */
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_NETWORK_STATE)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_NETWORK_STATE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_NETWORK_STATE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Utils.showSnackBar(mLayParent, getString(R.string.permission_granted));
                    callApiToFetchArtists();
                } else {
                    Utils.showSnackBar(mLayParent, getString(R.string.permission_denied));
                    checkPermission();
                }
                break;
        }
    }
}
