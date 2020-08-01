package org.cxyxh.blogserver.exception;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/1 16:59
 * @describetion :
 */
public class BlogException extends Exception {

    public BlogException() {
    }

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogException(Throwable cause) {
        super(cause);
    }

    public BlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
