package kr.rang2ne.triprec.common;

/**
 * Created by rang on 2015-09-15.
 */
public class ApiResponse {
    private int resultCode;
    private String resultMessage;
    private Object resultContent;

    public static ApiResponse makeSuccess(Object resultContent) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResultCode(200);
        apiResponse.setResultMessage("OK");
        apiResponse.setResultContent(resultContent);
        return apiResponse;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResultContent() {
        return resultContent;
    }

    public void setResultContent(Object resultContent) {
        this.resultContent = resultContent;
    }
}
