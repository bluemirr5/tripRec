package kr.rang2ne.triprec.view.model;

import lombok.Data;

import java.util.Date;

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
}
