package ByteBuf;

import java.util.List;

/**
 * @author haiqiang
 * @date 2018/12/5 9:15
 */
public class JsonUtil {
    public static <T> byte[] getByte(Class<T> obj) throws IllegalAccessException, InstantiationException {
        byte[] bytes=null;
        Object o=obj.newInstance();
        return bytes;
    }
}
