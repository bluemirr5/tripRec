package kr.rang2ne.triprec.account.model;

/**
 * Created by rang on 2015-09-12.
 */
public class LoginDto {
    public static final int LOGIN_OK = 0;
    public static final int LOGIN_FAIL_ID = 1;
    public static final int LOGIN_FAIL_PASSWORD = 2;

    private int resultCode;
    private String resultMessage;
    private Member member;

    public LoginDto() {}

    public LoginDto(int resultCode, String resultMessage, Member member) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.member = member;
    }

    public LoginDto(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
