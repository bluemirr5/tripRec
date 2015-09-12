package kr.rang2ne.triprec.trip.model;

import kr.rang2ne.triprec.account.model.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rang on 2015-09-11.
 *
 */
@Entity
public class Trip {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String desc;
    private Date regTime;
    private Date modTime;
    private boolean published;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "trip")
    private List<Scene> scenes = new ArrayList<Scene>();

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }

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

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
