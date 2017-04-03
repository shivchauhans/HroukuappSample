package perx.artistsampleapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import perx.artistsampleapp.R;
import perx.artistsampleapp.model.ArtistPojo;
import perx.artistsampleapp.model.SongsPojo;


/**
 * Created by shiv on 29/3/17.
 */
public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.ViewHolder> {
    private Context context;
    ArrayList<SongsPojo> songsPojoArrayList;

    public SongsListAdapter(Context context, ArrayList<SongsPojo> songsPojoArrayList) {
        this.context = context;
        this.songsPojoArrayList = songsPojoArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         final int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_list_item, parent, false);
        // create ViewHolder

        final ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    //Binds the data to views
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTvSongTitle.setTag(position);
        viewHolder.mTvSongTitle.setText(songsPojoArrayList.get(position).getTitle());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvSongTitle;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mTvSongTitle = (TextView) itemLayoutView.findViewById(R.id.tv_name);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return songsPojoArrayList.size();
    }
}