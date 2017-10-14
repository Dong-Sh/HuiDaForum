package com.huidaforum.base;

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

        private String key;

        private String value;

        @Override
        public String toString() {
            return "FieldErrorBean{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
