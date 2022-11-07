package service.baseSerivice;

import java.io.IOException;

@FunctionalInterface
public interface BaseService<T> {
    void  add(T o) throws IOException;

}
