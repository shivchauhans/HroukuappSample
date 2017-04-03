package perx.artistsampleapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import perx.artistsampleapp.R;
import perx.artistsampleapp.model.AlbumsPojo;


/**
 * Created by shiv on 29/3/17.
 */
public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.ViewHolder> {
    private Context context;
    ArrayList<AlbumsPojo> artistAlbumsListingPojos;

    public AlbumsListAdapter(Context context, ArrayList<AlbumsPojo> artistAlbumsListingPojos) {
        this.context = context;
        this.artistAlbumsListingPojos = artistAlbumsListingPojos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         final int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.albums_list_item, parent, false);
        // create ViewHolder

        final ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    //Binds the data to views
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTvAlbumTitle.setTag(position);
        viewHolder.mTvAlbumTitle.setText("Album Title : " + artistAlbumsListingPojos.get(position).getTitle());
        viewHolder.mTvAlbumYear.setText("Album Release Year :" + artistAlbumsListingPojos.get(position).getYear());

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvAlbumTitle, mTvAlbumYear, mTvAlbumNumber;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            mTvAlbumTitle = (TextView) itemLayoutView.findViewById(R.id.tv_album_title);
            mTvAlbumYear = (TextView) itemLayoutView.findViewById(R.id.tv_album_year);

        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return artistAlbumsListingPojos.size();
    }
}