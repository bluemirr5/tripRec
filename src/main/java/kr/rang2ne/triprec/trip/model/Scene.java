package kr.rang2ne.triprec.trip.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rang on 2015-09-11.
 */
@Entity
@Data
public class Scene {
    @Id @GeneratedValue
    private Long id;
    private int orderNum;
    private String desc;
    private String locationTag;

    private Date pictureTime;
    private double pictureLatitude;
    private double pictureLongitude;
    private int pictureWidth;
    private int pictureHeight;
    private String picturePath;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SCENE_ID")
    private List<MetaTag> metaTags = new ArrayList<>();

    @ManyToOne
    private Trip trip;
}
