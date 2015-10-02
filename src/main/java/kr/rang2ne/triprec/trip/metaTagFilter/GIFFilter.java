package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.Date;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public class GIFFilter implements MetaTagFilter {
    private Map<String, MetaTag> tagMaps = null;

    @Override
    public void setMetaTags(Map<String, MetaTag> tagMaps) {
        this.tagMaps = tagMaps;
    }

    @Override
    public String getExtension() {
        return "GIF";
    }

    @Override
    public Date getPictureTime() {
        return null;
    }

    @Override
    public Double getPictureLatitude() {
        return null;
    }

    @Override
    public Double getPictureLongitude() {
        return null;
    }

    @Override
    public int getPictureWidth() {
        return getSizeValue("GIF Header-2");
    }

    @Override
    public Map<String, MetaTag> getMetaTags() {
        return this.tagMaps;
    }

    @Override
    public int getPictureHeight() {
        return getSizeValue("GIF Header-3");
    }

    private int getSizeValue(String key) {
        MetaTag metaTag = getMetaTags().get(key);
        if(metaTag != null) {
            try {
                int width = Integer.parseInt(metaTag.getDescription());
                return width;
            } catch (Exception e) {e.printStackTrace();}
        }
        return 0;
    }
}
