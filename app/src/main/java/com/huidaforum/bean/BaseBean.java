package com.huidaforum.bean;

/**
 * Created by lenovo on 2017/9/21.
 */

public class BaseBean<T> {


    /**
     * cookieValue : null
     * success : true
     * data : null
     * errMsg : null
     * fieldError : null
     * succMsg : {"TOKEN":"96ba092649764a07bc56b16dfa431b17"}
     */

    private String cookieValue;
    private boolean success;
    private T data;
    private String errMsg;
    private FieldErrorBean fieldError;

    @Override
    public String toString() {
        return "BaseBean{" +
                "cookieValue='" + cookieValue + '\'' +
                ", success=" + success +
                ", data=" + data +
                ", errMsg='" + errMsg + '\'' +
                ", fieldError=" + fieldError +
                '}';
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public FieldErrorBean getFieldError() {
        return fieldError;
    }

    public void setFieldError(FieldErrorBean fieldError) {
        this.fieldError = fieldError;
    }

    public class FieldErrorBean {
        /**
         * ke1 :  phoneValidCode
         * ”key2” : ”短信验证码已失效!”
         */

        private String ke1;

        private String ke2;

        public String getKe1() {
            return ke1;
        }

        public void setKe1(String ke1) {
            this.ke1 = ke1;
        }

        public String getKe2() {
            return ke2;
        }

        public void setKe2(String ke2) {
            this.ke2 = ke2;
        }

        @Override
        public String toString() {
            return "FieldErrorBean{" +
                    "ke1='" + ke1 + '\'' +
                    ", ke2='" + ke2 + '\'' +
                    '}';
        }
    }
}
