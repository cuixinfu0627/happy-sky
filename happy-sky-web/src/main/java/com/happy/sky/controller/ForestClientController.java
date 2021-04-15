package com.happy.sky.controller;

import com.alibaba.fastjson.JSON;
import com.dtflys.forest.config.ForestConfiguration;
import com.happy.sky.common.utils.JSONHandler;
import com.happy.sky.forest.FireForestClient;
import com.happy.sky.forest.request.params.FireInspection;
import com.happy.sky.forest.request.params.FireInspectionNodes;
import com.happy.sky.service.ForestClientService;
import com.happy.sky.view.R;
import com.happy.sky.view.ResultVoWrapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @name: ForestClientController <tb>
 * @title: https://gitee.com/dt_flys/forest  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 14:30 <tb>
 */
@RestController
@RequestMapping("/forest")
public class ForestClientController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ForestClientService forestClientService;

    @Resource(name = "config0")
    private ForestConfiguration config0;

    @Autowired
    private FireForestClient fireForestClient;

    @GetMapping("/getLocation")
    public R getLocation(@RequestParam(value = "cityName") String cityName) throws Exception {
        System.out.println("request receive params cityName: " + cityName);
        Map location = forestClientService.getLocation();
        return R.ok().put("data", location);
    }

    /** ########################### 获取消防平台单位下相关基础信息接口 ############################"**/
    /**
     * @title: 单位列表 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "hello")
    public Object hello() {
        String appSecret = "132456789";
        String url = "http://127.0.0.1:8080:/forest/list";
        Map<String, Object> data = new HashMap<>();
        data.put("time", System.currentTimeMillis());
        String body = JSON.toJSONString(data);
        String sign = DigestUtils.md5DigestAsHex(("POST" + url + body + appSecret).getBytes());
        return fireForestClient.queryFireList(url, sign, body);
    }

    /**
     * @title: 请输入描述 <tb>
     * @params: 测试返回数据  <tb
     */
    @PostMapping(value = "list")
    public Object list(HttpServletRequest request,
                       @RequestParam("sign") String sign,
                       @RequestBody String data) {

        //获取配置信息
        Map<String, Object> variables = config0.getVariables();
        String forestVariables = JSON.toJSONString(variables);
        logger.info("get forest config info :{}", forestVariables);

        String appSecret = "9c1fe55af3b3444991a51f23d6775693";
        String url = request.getRequestURL().toString();

        String signature = DigestUtils.md5DigestAsHex(("POST" + url + data + appSecret).getBytes());
        logger.info("upload platform get the md5 signature result :{}", signature);
        if (!signature.equals(sign)) {
            return ResultVoWrapper.buildFail();
        }
        List<Object> unitList = new ArrayList<>();
        Unit unit1 = new Unit(1L, "单位一");
        Unit unit2 = new Unit(2L, "单位二");
        Unit unit3 = new Unit(3L, "单位三");
        unitList.add(unit1);
        unitList.add(unit2);
        unitList.add(unit3);
        return ResultVoWrapper.buildSuccess(unitList);
    }

    @Data
    class Unit {
        private Long id;
        private String name;

        public Unit(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * @title: 单位列表 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "queryUnitList")
    public Object queryUnitList() {
        String url = "http://api.fc-shaodong.zhxf.ltd/unit/queryPagerUnitList?currentPage=1&likeName=&pageSize=16";
        return fireForestClient.queryUnitList(url);
    }

    /**
     * @title: 添加设备 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "addBuilding")
    public R addBuilding(@RequestParam(value = "prefix") String prefix,
                         @RequestParam(value = "max") Integer max) {
        String base = "http://api.fc-shaodong.zhxf.ltd";
        for (int i = 2; i < max; i++) {
            String strIndex = charStr[getRandomNumber(25, 0)];
            String numIndex = charnumber[getRandomNumber(8, 0)];
            String name = strIndex + numIndex + "栋";
            String unitId = "9029";
            String unitName = "数据管理单位";
            String location = "后湖路" + getRandomNumber(600, 10) + "号";
            String area = "";
            String heightOfBuilding = "";
            String floors = "23";
            String structure = "混凝土结构";
            String buildYear = "2014-01-01";
            String property = "居住";
            String linkname = "梁永辉";
            String phone = "1569396116";
            String pointX = "116.3610202562" + getRandomNumber(4); //随机后4位
            String pointY = "40.07020874293" + getRandomNumber(4); //随机后4位
            String headers = "{\"Content-Type\":\"application/json\"}";
            Object obj = fireForestClient.fireAddBuilding(base, name, unitId, unitName, location, area, heightOfBuilding, floors, structure, buildYear, property, linkname, phone, pointX, pointY, headers);
            String result = JSONHandler.getGson().toJson(obj);
            logger.info("添加建筑返回结果：{}", result);
        }
        return R.ok("this is ok!");
    }

    /**
     * @title: 添加设备 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "addDevice")
    public R addDevice(@RequestParam(value = "prefix") String prefix,
                       @RequestParam(value = "max") Integer max) throws InterruptedException {
        long start, end;
        start = System.currentTimeMillis();
        String base = "http://api.fc-shaodong.zhxf.ltd";
        for (int i = 1; i < max; i++) {
            String name = "烟感";
            String unitId = "9029";
            String unitName = "数据管理单位";
            String buildingId = "0";
            String buildingName = "室外";
            String floorId = "";
            String floorNumber = "";
            String roomId = "";
            String roomNumber = "";
            String deviceTypeId = "78";
            String deviceTypeName = "点型光电感烟火灾探测器";
            String pointX = "116.3610202562" + getRandomNumber(4); //随机后4位
            String pointY = "40.07020874293" + getRandomNumber(4); //随机后4位
            String xRate = "";
            String mac = "jsca-" + System.currentTimeMillis() + "-" + getCharBaseStr() + "-" + getRandomString(13); //随机后8位
            String startDate = "2020-08-27";
            String height = "";
            String fheight = "";
            String lifeMonth = "1000";
            String firm = "泛海三江";
            String productDate = "2020-08-27";
            String maintenanceUnit = "钜升畅安";
            String maintenanceUser = "秦永浩";
            String maintenancePhone = "15010089119";
            String controllerId = "0";
            String modelCode = "";
            String deviceUrl = "";

//            String params = "name=" + name
//                    + "&unitId=" + unitId + "&unitName=" + unitName
//                    + "&buildingId=" + buildingId + "&buildingName=" + buildingName
//                    + "&floorId=" + floorId + "&floorNumber=" + floorNumber
//                    + "&roomId=" + roomId + "&roomNumber=" + roomNumber
//                    + "&deviceTypeId=" + deviceTypeId + "&deviceTypeName=" + deviceTypeName + "&mac=" + mac
//                    + "&pointX=" + pointX + "&pointY=" + pointY + "&xRate=" + xRate
//                    + "&mac=" + mac + "&startDate=" + startDate
//                    + "&height=" + height + "&fheight=" + fheight
//                    + "&lifeMonth=" + lifeMonth + "&firm=" + firm
//                    + "&productDate=" + productDate + "&maintenanceUnit=" + maintenanceUnit
//                    + "&maintenanceUser=" + maintenanceUser + "&maintenancePhone=" + maintenancePhone
//                    + "&controllerId=" + controllerId + "&modelCode=" + modelCode + "&deviceUrl=" + deviceUrl;
//            Object obj = fireForestClient.fireAddDeviceBak(base, params);
            Thread.sleep(500);
            Object obj = fireForestClient.fireAddDevice(base, name, unitId, unitName, buildingId, buildingName, floorId, floorNumber, roomId, roomNumber,
                    deviceTypeId, deviceTypeName, pointX, pointY, xRate, mac, startDate, height, fheight, lifeMonth, firm, productDate,
                    maintenanceUnit, maintenanceUser, maintenancePhone, controllerId, modelCode, deviceUrl);
            String result = JSONHandler.getGson().toJson(obj);
            logger.info("InsertTotal:[{}],First:[{}],Response:{}", max, i, result);
        }
        end = System.currentTimeMillis();
        System.out.println("total: " + max + " start time:" + start + "; end time:" + end + "; Run Time:" + (end - start) + "(ms)");
        return R.ok("Add device total: " + max + "; Run Time:" + (end - start) + "(ms)");
    }

    /**
     * @title: 添加巡检路线 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "addInspection")
    public R addInspection(@RequestParam(value = "prefix") String prefix,
                           @RequestParam(value = "max") Integer max) {
        String url = "http://api.fc-shaodong.zhxf.ltd/admin/inspection/insertInspectionPlan";
        for (int i = 1; i < max; i++) {
            //组装数据
            FireInspection inspection = new FireInspection();
            String strIndex = charStr[getRandomNumber(5, 0)];
            String numIndex = charnumber[getRandomNumber(8, 0)] + charnumber[getRandomNumber(8, 0)];
            inspection.setName("巡检路线" + strIndex + numIndex);    //路线随机
            inspection.setType(2);
            inspection.setUnitId(9029L);
            inspection.setUnitName("数据管理单位");
            List<FireInspectionNodes> listInspectionNodes = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                FireInspectionNodes ispectionNodes = new FireInspectionNodes();
                ispectionNodes.setSorting(j);
                ispectionNodes.setBuildingId(0L);
                ispectionNodes.setBuildingName("室外");
                ispectionNodes.setFloorId(0L);
                ispectionNodes.setFloorNumber("");
                ispectionNodes.setRoomId(0L);
                ispectionNodes.setRoomNumber("");
                ispectionNodes.setDeviceId(0L);
                ispectionNodes.setDeviceName("");
                listInspectionNodes.add(ispectionNodes);
            }
            inspection.setInspectionNodes(listInspectionNodes);
            //执行插入操作
            Object obj = fireForestClient.fireAddInspection(url, inspection);
            String result = JSONHandler.getGson().toJson(obj);
            logger.info("添加巡检路线返回结果：{}", result);
        }
        return R.ok("this is ok!");
    }

    /**
     * @title: 添加上传隐患 <tb>
     * @params: projectId  <tb
     */
    @GetMapping(value = "addTrouble")
    public R addTrouble(@RequestParam(value = "prefix") String prefix,
                        @RequestParam(value = "max") Integer max) {
        String base = "http://api.fc-shaodong.zhxf.ltd";
        for (int i = 1; i < max; i++) {
            Integer type = getRandomNumber(4, 1);  //随机类型1-4
            Integer levels = getRandomNumber(3, 1); //随机级别1-3
            String dangerName = "";
            String unitId = "9029";
            String unitName = "数据管理单位";
            String buildingId = "0";
            String buildingName = "室外";
            String floorId = "";
            String floorNumber = "";
            String roomId = "";
            String roomNumber = "";
            String pointX = "116.3610202562" + getRandomNumber(4); //随机后4位
            String pointY = "40.07020874293" + getRandomNumber(4); //随机后4位
            String xRate = "0";
            String yRate = "0";
            String cont = "发现隐患-测试00" + i;
            String gridId = "";

//            String params = "type=" + type + "&levels=" + levels + "&dangerName=" + dangerName
//                    + "&unitId=" + unitId + "&unitName=" + unitName
//                    + "&buildingId=" + buildingId + "&buildingName=" + buildingName
//                    + "&floorId=" + floorId + "&floorNumber=" + floorNumber
//                    + "&roomId=" + roomId + "&roomNumber=" + roomNumber
//                    + "&pointX=" + pointX + "&pointY=" + pointY + "&xRate=" + xRate + "&yRate=" + yRate
//                    + "&cont=" + cont + "&gridId=" + gridId;
//            Object obj = fireForestClient.fireAddTroubleBak(url, params);

            Object obj = fireForestClient.fireAddTrouble(base, type, levels, dangerName,
                    unitId, unitName, buildingId, buildingName, floorId, floorNumber, roomId, roomNumber,
                    pointX, pointY, xRate, yRate, cont, gridId);

            String result = JSONHandler.getGson().toJson(obj);
            logger.info("添加隐患返回结果：{}", result);
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
    public static String getRandomNumber(int n) {
        Random rad = new Random();
        String result = rad.nextInt(1000000) + "";
        if (result.length() != n) {
            return getRandomNumber(n);
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
        System.out.println("ForestClientController.main");
        System.out.println(System.currentTimeMillis());
        System.out.println(getRandomString(6));
    }
}
