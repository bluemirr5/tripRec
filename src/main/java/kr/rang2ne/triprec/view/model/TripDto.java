package kr.rang2ne.triprec.view.model;

import kr.rang2ne.triprec.trip.model.Scene;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by rang on 2015-09-17.
 */
public class TripDto {
    @Data
    public static class SelectList {
        private Long id;
        private String name;
        private String desc;
        private boolean published;
        private List<SceneDto.SelectList> scenes;
    }

    @Data
    public static class Update {
        @NotBlank private Long id;
        @NotBlank private String name;
        private String desc;
        @NotEmpty private boolean published;
    }

    @Data
    public static class Create {
        @NotBlank private String name;
        private String desc;
        @NotEmpty private boolean published;
        private List<Scene> scenes;
    }
}
