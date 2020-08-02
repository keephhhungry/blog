package org.cxyxh.blogshow.exception;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/1 16:59
 * @describetion :
 */
public class BlogRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BlogRuntimeException(String message) {
        super(message);
    }

    public BlogRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
