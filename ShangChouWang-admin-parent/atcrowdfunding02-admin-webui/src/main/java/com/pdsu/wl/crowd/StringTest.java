package com.pdsu.wl.crowd;

import com.pdsu.wl.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @author wl
 * @Date 2021/7/21 19:58
 */
public class StringTest {

    @Test
    public void testMD5() {
        String source = "123456";
        final String s = CrowdUtil.MD5(source);
        System.out.println(s);
    }
}
