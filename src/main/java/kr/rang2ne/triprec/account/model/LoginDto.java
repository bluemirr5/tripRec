package kr.rang2ne.triprec.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rang on 2015-09-12.
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class LoginDto {
    public static final int LOGIN_OK = 0;
    public static final int LOGIN_FAIL_ID = 1;
    public static final int LOGIN_FAIL_PASSWORD = 2;

    private int resultCode;
    private String resultMessage;
    private Member member;
}
