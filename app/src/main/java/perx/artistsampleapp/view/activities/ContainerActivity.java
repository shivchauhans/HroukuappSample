package perx.artistsampleapp.view.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import perx.artistsampleapp.R;
import perx.artistsampleapp.view.fragments.ArtistListingFragment;

/**
 * Created by shiv on 1/4/17.
 */
public class ContainerActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        mContext = this;
        loadFragment();
    }

    /**
     * load fragment by passing mandatory arguments as bundle
     */

    private void loadFragment() {
        ArtistListingFragment searchLocationFragment = new ArtistListingFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean("addAnother", false);
        searchLocationFragment.setArguments(arguments);
        onClickFragment(searchLocationFragment, "ArtistListFragment");
    }

    /**
     * Loading fragment to the frame
     *
     * @param fragmentToLoad Fragment to transact
     */

    public void onClickFragment(Fragment fragmentToLoad, String tag) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragmentToLoad);
        fragmentTransaction.addToBackStack(tag);
        if (!isDestroyed())
            fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}