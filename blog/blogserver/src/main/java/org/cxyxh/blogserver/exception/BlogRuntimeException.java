package org.cxyxh.blogserver.exception;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/1 16:59
 * @describetion :
 */
public class BlogRuntimeException extends RuntimeException {


    public BlogRuntimeException() {
    }

    public BlogRuntimeException(String message) {
        super(message);
    }

    public BlogRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogRuntimeException(Throwable cause) {
        super(cause);
    }

    public BlogRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
