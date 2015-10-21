package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.Date;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public interface MetaTagFilter {
    void setMetaTags(Map<String, MetaTag> tagMap);
    Map<String, MetaTag> getMetaTags();
    String getExtension();
    Date getPictureTime();
    Double getPictureLatitude();
    Double getPictureLongitude();
    int getPictureWidth();
    int getPictureHeight();

    default int getSizeValue(String key) {
        MetaTag metaTag = getMetaTags().get(key);
        if(metaTag != null) {
            try {
                int width = Integer.parseInt(metaTag.getDescription());
                return width;
            } catch (Exception e) {e.printStackTrace();}
        }
        return 0;
    }

    default double getGPSValue(String key) {
        MetaTag metaTag = getMetaTags().get(key);
        if(metaTag != null) {
            String gpsStrValue = metaTag.getDescription();
            String[] seperatedGpsStrArray = gpsStrValue.split(" ");
            if(seperatedGpsStrArray.length == 3) {
                String degreeStr = seperatedGpsStrArray[0].replace("°", "");
                String minStr = seperatedGpsStrArray[1].replace("'", "");
                String secStr = seperatedGpsStrArray[2].replace("\"", "");
                try {
                    double degree = Double.parseDouble(degreeStr);
                    double min = Double.parseDouble(minStr);
                    double sec = Double.parseDouble(secStr);
                    return degree+(min/60)+(sec/3600);
                } catch (Exception e) {e.printStackTrace();}
            }
        }
        return 0;
    }
}
