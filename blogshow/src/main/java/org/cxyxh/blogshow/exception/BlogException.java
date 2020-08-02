package org.cxyxh.blogshow.exception;

/**
 * @ahthor : cxyxh
 * @date : 2020/8/1 17:23
 * @describetion :
 */
public class BlogException extends Exception{

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
