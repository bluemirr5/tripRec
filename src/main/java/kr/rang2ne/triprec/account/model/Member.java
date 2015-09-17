package kr.rang2ne.triprec.account.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rang on 2015-09-11.
 */
@Entity
@Data
public class Member {
    @Id
    private String id;
    private String name;
    private Date birth;
    private String sex;
    private String nickName;
    private String password;
    private Date joinTime;
}
