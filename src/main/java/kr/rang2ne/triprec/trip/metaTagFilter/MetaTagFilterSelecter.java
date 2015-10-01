package kr.rang2ne.triprec.trip.metaTagFilter;

import kr.rang2ne.triprec.trip.model.MetaTag;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rang on 2015-10-01.
 */
public class MetaTagFilterSelecter {
    private static final Map<String, Class> filterMap;
    static
    {
        filterMap = new HashMap<String, Class>();
        filterMap.put("JPG", JPEGFliter.class);
        filterMap.put("GIF", GIFFilter.class);
        filterMap.put("PNG", PNGFilter.class);
    }

    public static MetaTagFilter getMetaTagFilter(String extension, Map<String, MetaTag> metaTags) throws IllegalAccessException, InstantiationException {
        Class filterClass = filterMap.get(extension.toUpperCase());
        MetaTagFilter filter = (MetaTagFilter)filterClass.newInstance();
        filter.setMetaTags(metaTags);
        return filter;
    }
}
