package kr.limc.limcblog.Util.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlogException extends RuntimeException{
    public BlogException(String message) {
        super(message);
    }
}
