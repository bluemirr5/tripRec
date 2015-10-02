package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.Date;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public class JPEGFilter implements MetaTagFilter {
    private Map<String, MetaTag> tagMaps = null;

    @Override
    public String getExtension() {
        return "JPG";
    }

    @Override
    public void setMetaTags(Map<String, MetaTag> tagMaps) {
        this.tagMaps = tagMaps;
    }

    @Override
    public Map<String, MetaTag> getMetaTags() {
        return this.tagMaps;
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
        return getSizeValue("JPEG-"+3);
    }

    @Override
    public int getPictureHeight() {
        return getSizeValue("JPEG-"+1);
    }

    @Override
    public int getSizeValue(String key) {
        MetaTag metaTag = getMetaTags().get(key);
        if(metaTag != null) {
            String sizeWithPixels = metaTag.getDescription();
            if(sizeWithPixels != null && !"".equals(sizeWithPixels)) {
                return parseInt(sizeWithPixels);
            }
        }
        return 0;
    }

    private int parseInt(String data){
        String[] splitedStr = data.split(" ");
        if(splitedStr.length >= 1) {
            String numStr = splitedStr[0].replace(" ", "");
            try {
                int width = Integer.parseInt(numStr);
                return width;
            } catch (Exception e){e.printStackTrace();}
        }
        return 0;
    }
}
