package com.happy.sky.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.happy.sky.common.utils.JSONHandler;
import com.happy.sky.view.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/fire")
public class FireController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String INSERT_TROUBLE_SQL = "INSERT INTO XF_TROUBLE(ID, USERID, CREATETIME, CONT, TREATMENT, STATUS, UNITID, REVIEWTIME, REVIEWER, LEVELS, TYPE, POINTX, POINTY, IDCODE, DELETED, NICKNAME, BUILDINGNAME, UNITNAME, REVIEWERNAME) VALUES " +
            "('${id}', '${userid}',${createtime}, '发现隐患', '已解决', '1', '${unitid}', ${reviewtime}, '${reviewer}', '${levels}', '${type}', '${pointX}', '${pointY}', '${idcode}', '1', '${nickname}', '室外', '${unitname}', '${reviewername}');";

    @GetMapping(value = "troubleSql")
    public R addTrouble(@RequestParam(value = "max") Integer max) {
        //二维数据的用户id链表
        //随机抽取上传人和解决人
        String jsonUser = "{\"1\":{\"userId\":\"1\",\"username\":\"魏世星\"},\"2\":{\"userId\":\"2\",\"username\":\"刘键\"},\"3\":{\"userId\":\"8\",\"username\":\"王晓波\"},\"4\":{\"userId\":\"1722\",\"username\":\"胡英\"},\"5\":{\"userId\":\"1761\",\"username\":\"刘杰\"},\"6\":{\"userId\":\"1772\",\"username\":\"段亚伟\"},\"7\":{\"userId\":\"1784\",\"username\":\"刘辉\"},\"8\":{\"userId\":\"1964\",\"username\":\"阴金玲\"},\"9\":{\"userId\":\"2048\",\"username\":\"崔辛福\"},\"10\":{\"userId\":\"2116\",\"username\":\"王大海\"}}";
        List<UserInfo> userList = getUserList(jsonUser);
        for (int i = 1; i < max; i++) {
            UserInfo createUser = userList.get(getRandomNumber(9, 0));
            UserInfo closeUser = userList.get(getRandomNumber(9, 0));

            int idStart = 4500;
            String unitid = "9029";
            String unitname = "数据管理单位";
            String userid = createUser.getUserId();
            String nickname = createUser.getUsername();
            String idcode = "X" + System.currentTimeMillis() + getRandomNum(3);
            String createtime = "to_timestamp('2020-07-28 10:41:19.429000', 'SYYYY-MM-DD HH24:MI:SS:FF6')";

            String reviewer = closeUser.getUserId();
            String reviewername = closeUser.getUsername();
            String reviewtime = "to_timestamp('2020-07-29 9:30:20.419000', 'SYYYY-MM-DD HH24:MI:SS:FF6')";

            Integer type = getRandomNumber(4, 1);  //随机类型1-4
            Integer levels = getRandomNumber(3, 1); //随机级别1-3
            String pointX = "116.3610202562" + getRandomNum(4); //随机后4位
            String pointY = "40.07020874293" + getRandomNum(4); //随机后4位

            String sql = INSERT_TROUBLE_SQL.replace("${id}", String.valueOf(idStart+i));
            sql = sql.replace("${unitid}", unitid);
            sql = sql.replace("${unitname}", unitname);
            sql = sql.replace("${userid}", userid);
            sql = sql.replace("${nickname}", nickname);
            sql = sql.replace("${idcode}", idcode);
            sql = sql.replace("${createtime}", createtime);

            sql = sql.replace("${reviewer}", reviewer);
            sql = sql.replace("${reviewername}", reviewername);
            sql = sql.replace("${reviewtime}", reviewtime);

            sql = sql.replace("${type}", String.valueOf(type));
            sql = sql.replace("${levels}", String.valueOf(levels));
            sql = sql.replace("${pointX}", pointX);
            sql = sql.replace("${pointY}", pointY);

            System.out.println(sql);
        }
        return R.ok("this is ok!");
    }

    private static String charBaseStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String charStr[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static String charnumber[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * 随机字符串
     *
     * @param length 长度
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(charBaseStr.length());
            sb.append(charBaseStr.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机数值 指定范围大小
     *
     * @return
     */
    public static int getRandomNumber(int max, int min) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 产生N位的随机数字
     *
     * @return
     */
    public static String getRandomNum(int n) {
        Random rad = new Random();
        String result = rad.nextInt(10000) + "";
        if (result.length() != n) {
            return getRandomNum(n);
        }
        return result;
    }

    /**
     * 此方法描述的是：  随机生成4位随机验证码
     *
     * @return String
     */
    public static String getCharBaseStr() {
        String vcode = "";
        for (int i = 0; i < 4; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }

    /**
     * 获取格式化当前时间、毫秒字符串
     *
     * @return
     */
    public static String getStrDateS() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        Date currentTime = new Date();
        String strDate = formatter.format(currentTime);
        return strDate;
    }

    public static void main(String[] args) {
        //X20082800010001
        //1598598060523
        //X202008281459139436162
        System.out.println("ForestClientController.main");
        System.out.println(System.currentTimeMillis());
        String dateStr = "X" + System.currentTimeMillis() + getRandomNum(3);
        dateStr = dateStr.replace("-", "");
        dateStr = dateStr.replace(":", "");
        dateStr = dateStr.replace(" ", "");
        System.out.println(dateStr);
    }

    public static List<UserInfo> getUserList(String jsonUser) {
        JSONObject jsonObject = JSON.parseObject(jsonUser);
        List<UserInfo> userList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String jsonStr = entry.getValue().toString();
            UserInfo userInfo = JSONHandler.getObjectByJsonStr(jsonStr, UserInfo.class);
            userList.add(userInfo);
        }
        return userList;
    }


    class UserInfo {
        private String userId;
        private String username;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
