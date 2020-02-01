package com.lym;

import com.alibaba.fastjson.JSONObject;
import com.lym.entity.Captcha;
import com.lym.entity.User;
import com.lym.mapper.UserMapper;
import com.lym.utils.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

/**
 * @Date 2020/1/19
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class Main {

    final static Logger logger = LoggerFactory.getLogger(Main.class);
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void testCache() {
        Cache helloWorldCache = ehCacheCacheManager.getCache("HelloWorldCache");
        helloWorldCache.put("hello", "abc");
    }

    @Test
    public void testGetCache() {
        Cache helloWorldCache = ehCacheCacheManager.getCache("HelloWorldCache");
        System.out.println(helloWorldCache.get("hello").get());
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setAddress("gdgz");
        user.setRelname("linyimin");
        user.setAvatar("http://www.xxx.com/test.jpg");
        user.setJob("java");
        user.setName("hello world");
        user.setPassword("md5");
        user.setPhone("18316471919");
        user.setEmail("18316471919@139.com");
        user.setSex((byte) 1);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void testMd5() {
        System.out.println(DigestUtils.md5DigestAsHex("1234".getBytes()));
    }

    @Test
    public void testCglib() {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(EnhanceUtil.class);
//        enhancer.setCallback(new TargetInterceptor());
//        EnhanceUtil enhanceUtil = (EnhanceUtil) enhancer.create();
//        enhanceUtil.test("hello world");
    }

    @Test
    public void testJson() {
        Captcha o = JSONObject.parseObject("{\n" +
                "\t\"id\":1234,\n" +
                "\t\"code\":\"hello\"\n" +
                "}", Captcha.class);

        System.out.println(o.getCaptchaCode());
    }

    @Test
    public void testMail() {
        mailUtil.sendEmail("18316471919@139.com", "test", "hello world");
    }

    @Test
    public void testCache2() {
        Cache helloWorldCache = cacheManager.getCache("HelloWorldCache");
        helloWorldCache.put("18316471919", "hello wrold 1234");
        Cache.ValueWrapper valueWrapper = helloWorldCache.get("18316471919");
        System.out.println(valueWrapper.get());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        valueWrapper = helloWorldCache.get("18316471919");
        System.out.println(valueWrapper.get());

    }
}
