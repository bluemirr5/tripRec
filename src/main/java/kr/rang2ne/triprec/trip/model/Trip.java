package kr.rang2ne.triprec.trip.model;

import kr.rang2ne.triprec.account.model.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rang on 2015-09-11.
 *
 */
@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Date regTime;
    private Date modTime;
    private boolean published;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "trip")
    private List<Scene> scenes = new ArrayList<Scene>();
}
