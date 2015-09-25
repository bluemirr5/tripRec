package kr.rang2ne.triprec.trip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by rang on 2015-09-25.
 */
@Data
@Entity
public class MetaTag {
    @Id @GeneratedValue
    private Long id;
    private String directoryName;
    private Integer tagType;
    private String tagName;
    private String description;
}
