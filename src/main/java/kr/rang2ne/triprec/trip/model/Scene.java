package kr.rang2ne.triprec.trip.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by rang on 2015-09-11.
 */
@Entity
@Data
public class Scene {
    @Id
    @GeneratedValue
    private Long id;
    private int orderNum;
    private String desc;
    private Date sceneTime;
    private double latitude;
    private double longitude;
    private String locationTag;
    private String pictureUrl;
    private String thumbUrl;

    @ManyToOne
    private Trip trip;
}
