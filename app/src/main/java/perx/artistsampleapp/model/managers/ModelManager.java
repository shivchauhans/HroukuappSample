package perx.artistsampleapp.model.managers;

/**
 * Created by shiv on 22/1/17.
 */
public class ModelManager {

    private static ModelManager ourInstance = new ModelManager();

    public static ModelManager getInstance() {
        return ourInstance;
    }
    private CacheManager cacheManager;
    private ArtistManager artistManager;

    private ModelManager() {
        cacheManager = new CacheManager();
        artistManager= new ArtistManager();
    }
    /**
     * @return returns instance of artist manager
     */

    public ArtistManager getArtistManager() {
        return artistManager;
    }

    /**
     * @return returns  instance of Cache manager
     */

    public CacheManager getCacheManager() {
        if (cacheManager == null)
            cacheManager = new CacheManager();
        return cacheManager;
    }


}
