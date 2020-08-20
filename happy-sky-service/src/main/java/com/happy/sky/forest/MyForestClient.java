package com.happy.sky.forest;

import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;

import javax.xml.ws.Response;
import java.util.Map;

/**
 * @name: ForestClientService <tb>
 * @title: https://dt_flys.gitee.io/forest  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 14:26 <tb>
 */
public interface MyForestClient {

    // GET请求
    @Request(
            url = "http://localhost:5000/hello",
            type = "get"
    )
    String simpleGet();

    // POST请求
    @Request(
            url = "http://localhost:5000/hello",
            type = "post"
    )
    String simplePost();

    // PUT请求
    @Request(
            url = "http://localhost:5000/hello",
            type = "put"
    )
    String simplePut();

    // HEAD请求
    @Request(
            url = "http://localhost:5000/hello",
            type = "head"
    )
    String simpleHead();

    // Options请求
    @Request(
            url = "http://localhost:5000/hello",
            type = "options"
    )
    String simpleOptions();

    // Delete请求
    @Request(
            url = "http://localhost:5000/hello",
            type = "delete"
    )
    String simpleDelete();

    // 本地测试样列 1
    @Request(
            url = "http://ditu.amap.com/service/regeo",
            dataType = "json"
    )
    Map getLocation(@DataParam("longitude") String longitude, @DataParam("latitude") String latitude);

    // 4.1 模板表达式和参数的映射绑定功能 模板表达式在使用的时候特别方便，举个栗子
    @Request(
            url = "${0}/send?un=${1}&pw=${2}&ph=${3}&ct=${4}",
            type = "get",
            dataType = "json"
    )
    Map send(
            String base,
            String userName,
            String password,
            String phone,
            String content
    );

    //上述是用序号下标进行取值，也可以通过名字进行取值：
    @Request(
            url = "${base}/send1?un=${un}&pw=${pw}&ph=${3}&ct=${ct}",
            type = "get",
            dataType = "json"
    )
    Map send1(
            @DataVariable("base") String base,
            @DataVariable("un") String userName,
            @DataVariable("pw") String password,
            @DataVariable("ph") String phone,
            @DataVariable("ct") String content
    );

    //甚至于可以这样简化写：
    @Request(
            url = "${base}/send2",
            type = "get",
            dataType = "json"
    )
    Map send2(
            @DataVariable("base") String base,
            @DataParam("un") String userName,
            @DataParam("pw") String password,
            @DataParam("ph") String phone,
            @DataParam("ct") String content
    );

    //当然你也可以把参数绑定到header和body里去，你甚至于可以用一些表达式简单的把对象序列化成json或者xml：
    @Request(
            url = "${base}/pay",
            contentType = "application/json",
            type = "post",
            dataType = "json",
            headers = {"Authorization: ${1}"},
            data = "${json($0)}"
    )
    //public PayResponse pay(PayRequest request, String auth);
    Response pay(Request request, String auth);

}
