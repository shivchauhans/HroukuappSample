package perx.artistsampleapp.model;

/**
 * Created by shiv on 8/2/17.
 */

public class Event {
    private int key;
    private boolean data;

    public Event(int key, boolean isData) {
        this.key = key;
        this.data=data;
    }

    public int getKey() {
        return key;
    }

    public boolean isData() {
        return data;
    }
}
