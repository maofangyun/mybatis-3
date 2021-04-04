package com.abc.test.plugin;

/**
 * @Classname PageUtil
 * @Description TODO
 * @Author Jack
 * Date 2021/3/24 11:52
 * Version 1.0
 */
public class PageUtil {

    protected static final ThreadLocal<Page> local = new ThreadLocal<Page>();

    public static <T> Page<T> enable(int pageNum,int pageSize) {
        Page<T> page = new Page<T>(pageNum, pageSize);
        setLocalPage(page);
        return page;
    }

    public static <T> void setLocalPage(Page<T> page) {
        local.set(page);
    }

    public static <T> Page<T> getLocalPage() {
        return local.get();
    }
}
