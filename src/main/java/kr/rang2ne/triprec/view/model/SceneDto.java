package kr.rang2ne.triprec.view.model;

import kr.rang2ne.triprec.trip.model.MetaTag;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rang on 2015-09-17.
 */
public class SceneDto {
    @Data
    public static class SelectList {
        private Long id;
        private int orderNum;
        private String description;
        private Date pictureTime;
        private double pictureLatitude;
        private double pictureLongitude;
        private String locationTag;
        private String pictureUrl;
    }

    @Data
    public static class SaveFile {
        private Date pictureTime;
        private double pictureLatitude;
        private double pictureLongitude;
        private int pictureWidth;
        private int pictureHeight;
        private String picturePath;
        private List<MetaTag> metaTags = new ArrayList<>();
    }
}
