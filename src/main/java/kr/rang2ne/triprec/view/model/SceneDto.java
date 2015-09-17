package kr.rang2ne.triprec.view.model;

import java.util.Date;

/**
 * Created by rang on 2015-09-17.
 */
public class SceneDto {

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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Date getSceneTime() {
            return sceneTime;
        }

        public void setSceneTime(Date sceneTime) {
            this.sceneTime = sceneTime;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getLocationTag() {
            return locationTag;
        }

        public void setLocationTag(String locationTag) {
            this.locationTag = locationTag;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }
    }
}
