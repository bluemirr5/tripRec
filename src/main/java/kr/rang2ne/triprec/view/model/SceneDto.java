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
        private String desc;
        private Date sceneTime;
        private double latitude;
        private double longitude;
        private String locationTag;
        private String pictureUrl;
        private String thumbUrl;
    }

    @Data
    public static class SaveFile {
        private String picturePath;
        private List<MetaTag> metaTags = new ArrayList<>();
    }
}
