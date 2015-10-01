package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.Date;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public interface MetaTagFilter {
    void setMetaTags(Map<String, MetaTag> tagMap);
    String getExtension();
    Date getPictureTime();
    Double getPictureLatitude();
    Double getPictureLongitude();
    int getPictureWidth();
    int getPictureHeight();
}
