package kr.rang2ne.triprec.view.model;

import kr.rang2ne.triprec.trip.model.Scene;

import java.util.List;

/**
 * Created by rang on 2015-09-17.
 */
public class TripDto {
    public static class SelectList {
        private Long id;
        private String name;
        private String desc;
        private boolean published;
        private List<SceneDto.SelectList> scenes;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public List<SceneDto.SelectList> getScenes() {
            return scenes;
        }

        public void setScenes(List<SceneDto.SelectList> scenes) {
            this.scenes = scenes;
        }
    }

    public static class Update {
        private Long id;
        private String name;
        private String desc;
        private boolean published;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }
    }

    public static class Create {
        private String name;
        private String desc;
        private boolean published;
        private List<Scene> scenes;

        public List<Scene> getScenes() {
            return scenes;
        }

        public void setScenes(List<Scene> scenes) {
            this.scenes = scenes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }
    }
}
