package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.Date;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public class JPEGFliter implements MetaTagFilter {
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
        MetaTag metaTag = tagMaps.get("JPEG-"+3);
        if(metaTag != null) {
            String widthWithPixels = metaTag.getDescription();
            if(widthWithPixels != null && !"".equals(widthWithPixels)) {
                return parseInt(widthWithPixels);
            }
        }
        return 0;
    }

    @Override
    public int getPictureHeight() {
        MetaTag metaTag = tagMaps.get("JPEG-"+1);
        if(metaTag != null) {
            String heightWithPixels = metaTag.getDescription();
            if(heightWithPixels != null && !"".equals(heightWithPixels)) {
                return parseInt(heightWithPixels);
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
