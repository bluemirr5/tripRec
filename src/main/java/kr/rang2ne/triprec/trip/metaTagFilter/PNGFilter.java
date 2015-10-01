package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.Date;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public class PNGFilter implements MetaTagFilter {
    private Map<String, MetaTag> tagMaps = null;

    @Override
    public String getExtension() {
        return "PNG";
    }

    @Override
    public void setMetaTags(Map<String, MetaTag> tagMaps) {
        this.tagMaps = tagMaps;
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
        return getValue("PNG-IHDR-"+1);
    }

    @Override
    public int getPictureHeight() {
        return getValue("PNG-IHDR-"+2);
    }

    private int getValue(String key) {
        MetaTag metaTag = tagMaps.get(key);
        if(metaTag != null) {
            try {
                int width = Integer.parseInt(metaTag.getDescription());
                return width;
            } catch (Exception e) {e.printStackTrace();}
        }
        return 0;
    }
}
