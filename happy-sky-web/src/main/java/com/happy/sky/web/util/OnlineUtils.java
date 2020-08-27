package com.happy.sky.web.util;

import com.happy.sky.common.utils.JSONHandler;
import com.happy.sky.config.redis.pool.JedisUtils;
import com.happy.sky.entity.UserEntity;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * 在线列表工具类
 * Redis缓存中存放两组key：
 * 1.SID_PREFIX开头，存放登陆用户的SessionId与ClientUser的Json数据
 * 2.UID_PREFIX开头，存放登录用户的UID与SessionId对于的数据
 * <p>
 * 3.VID_PREFIX开头，存放位于指定页面用户的数据（与Ajax一起使用，用于实现指定页面同时浏览人数的限制功能）
 *
 * @author BuilderQiu
 * @ClassName: OnlineUtils
 * @Description: 在线列表操作工具类
 * @date 2014-1-9 上午09:25:43
 * <p>
     * if(uid != null){//已登录
     * if(!OnlineUtils.isOnline(uid, session.getId())){
     * session.invalidate();
     * return ai.invoke();
     * }else{
     * OnlineUtils.login(session.getId(), (ClientUser)session.getAttribute("clientUser"));
     * //刷新缓存
     * }
     * }
 */
public class OnlineUtils {

    //KEY值根据SessionID生成
    private static final String SID_PREFIX = "online:sid:";
    private static final String UID_PREFIX = "online:uid:";
    private static final String VID_PREFIX = "online:vid:";
    private static final int OVERDATETIME = 30 * 60;
    private static final int BROADCAST_OVERDATETIME = 70; //ax每60秒发起一次，超过BROADCAST_OVERDATETIME时间长度未发起表示已经离开该页面

    public static void login(String sid, UserEntity user) {
        Jedis jedis = JedisUtils.getResource();
        jedis.setex(SID_PREFIX + sid, OVERDATETIME, userToString(user));
        jedis.setex(UID_PREFIX + user.getId(), OVERDATETIME, sid);
        JedisUtils.close();
    }

    public static void broadcast(String uid, String identify) {
        if (uid == null || "".equals(uid)) //异常数据，正常情况下登陆用户才会发起该请求
            return;
        Jedis jedis = JedisUtils.getResource();;
        jedis.setex(VID_PREFIX + identify + ":" + uid, BROADCAST_OVERDATETIME, uid);
        JedisUtils.close();
    }

    private static String userToString(UserEntity user) {
        JsonConfig config = new JsonConfig();
        JsonValueProcessor processor = new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss");
        config.registerJsonValueProcessor(Date.class, processor);
        JSONObject obj = JSONObject.fromObject(user, config);
        return obj.toString();
    }

    /**
     * @param @param sessionId
     * @return void
     * @throws
     * @Title: logout
     * @Description: 退出
     */
    public static void logout(String sid, String uid) {
        Jedis jedis = JedisUtils.getResource();;
        jedis.del(SID_PREFIX + sid);
        jedis.del(UID_PREFIX + uid);
        JedisUtils.close();
    }

    /**
     * @param @param UserId  使指定用户下线
     * @return void
     * @throws
     * @Title: logout
     * @Description: 退出
     */
    public static void logout(String uid) {
        Jedis jedis = JedisUtils.getResource();;
        //删除sid
        jedis.del(SID_PREFIX + jedis.get(UID_PREFIX + uid));
        //删除uid
        jedis.del(UID_PREFIX + uid);
        JedisUtils.close();
    }

    public static String getClientUserBySessionId(String sid) {
        Jedis jedis = JedisUtils.getResource();;
        String user = jedis.get(SID_PREFIX + sid);
        JedisUtils.close();
        return user;
    }

    public static String getClientUserByUid(String uid) {
        Jedis jedis = JedisUtils.getResource();;
        String user = jedis.get(SID_PREFIX + jedis.get(UID_PREFIX + uid));
        JedisUtils.close();
        return user;
    }

    /**
     * @return List
     * @throws
     * @Title: online
     * @Description: 所有的key
     */
    public static List online() {
        Jedis jedis = JedisUtils.getResource();;
        Set online = jedis.keys(SID_PREFIX + "*");
        JedisUtils.close();
        return new ArrayList(online);
    }

    /**
     * @return List
     * @throws
     * @Title: online
     * @Description: 分页显示在线列表
     */
    public static List onlineByPage(int page, int pageSize) throws Exception {
        Jedis jedis = JedisUtils.getResource();;
        Set onlineSet = jedis.keys(SID_PREFIX + "*");
        List onlines = new ArrayList(onlineSet);
        if (onlines.size() == 0) {
            return null;
        }
        Pipeline pip = jedis.pipelined();
        for (Object key : onlines) {
            pip.get(getKey(key));
        }
        List result = pip.syncAndReturnAll();
        JedisUtils.close();
        List<UserEntity> listUser = new ArrayList<UserEntity>();
        for (int i = 0; i < result.size(); i++) {
            Object obj = result.get(i);
            listUser.add(JSONHandler.getGson().fromJson(JSONHandler.objectToJson(obj),UserEntity.class));
        }
        Collections.sort(listUser, new Comparator<UserEntity>() {
            public int compare(UserEntity o1, UserEntity o2) {
                return o2.getLastLoginTime().compareTo(o1.getLastLoginTime());
            }
        });
        onlines = listUser;
        int start = (page - 1) * pageSize;
        int toIndex = (start + pageSize) > onlines.size() ? onlines.size() : start + pageSize;
        List list = onlines.subList(start, toIndex);
        return list;
    }

    private static String getKey(Object obj) {
        String temp = String.valueOf(obj);
        String key[] = temp.split(":");
        return SID_PREFIX + key[key.length - 1];
    }

    /**
     * @param @return
     * @return int
     * @throws
     * @Title: onlineCount
     * @Description: 总在线人数
     */
    public static int onlineCount() {
        Jedis jedis = JedisUtils.getResource();;
        Set online = jedis.keys(SID_PREFIX + "*");
        JedisUtils.close();
        return online.size();
    }

    /**
     * 获取指定页面在线人数总数
     */
    public static int broadcastCount(String identify) {
        Jedis jedis = JedisUtils.getResource();;
        Set online = jedis.keys(VID_PREFIX + identify + ":*");
        JedisUtils.close();
        return online.size();
    }

    /**
     * 自己是否在线
     */
    public static boolean broadcastIsOnline(String identify, String uid) {
        Jedis jedis = JedisUtils.getResource();;
        String online = jedis.get(VID_PREFIX + identify + ":" + uid);
        JedisUtils.close();
        return !StringUtils.isBlank(online);//不为空就代表已经找到数据了，也就是上线了
    }

    /**
     * 获取指定页面在线人数总数
     */
    public static int broadcastCount() {
        Jedis jedis = JedisUtils.getResource();;
        Set online = jedis.keys(VID_PREFIX + "*");
        JedisUtils.close();
        return online.size();
    }

    /**
     * @param @param  sessionId
     * @param @return
     * @return boolean
     * @throws
     * @Title: isOnline
     * @Description: 指定账号是否登陆
     */
    public static boolean isOnline(String uid) {
        Jedis jedis = JedisUtils.getResource();;
        boolean isLogin = jedis.exists(UID_PREFIX + uid);
        JedisUtils.close();
        return isLogin;
    }

    public static boolean isOnline(String uid, String sid) {
        Jedis jedis = JedisUtils.getResource();;
        String loginSid = jedis.get(UID_PREFIX + uid);
        JedisUtils.close();
        return sid.equals(loginSid);
    }
}