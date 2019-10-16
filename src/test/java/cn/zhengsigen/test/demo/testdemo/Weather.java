package cn.zhengsigen.test.demo.testdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Weather {
    /*
    1.7. 获取今天实时的天气
    调用API
    最近7天的天气/温度(最高低),  今天实时温度 天气
     */
    @Test
    public void weather() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://v.juhe.cn/weather/index?cityname=1&dtype=&format=&key=d10e5709c83497dd2a31187dc3013833";
        ResponseEntity<String> results = null;
        try {
            results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        } catch (Exception e) {
        }
        JSONObject object = JSON.parseObject(results.getBody());
        JSONObject result = object.getJSONObject("result");
        JSONObject future = result.getJSONObject("future");
        JSONObject sk = result.getJSONObject("sk");
        JSONObject today = result.getJSONObject("today");
        Map mapType = JSON.parseObject(String.valueOf(future), Map.class);
        List<WeatherData> lists = new ArrayList<>();
        for (Object map : mapType.entrySet()) {
            WeatherData weatherData = JSONObject.parseObject(((Map.Entry) map).getValue().toString(), WeatherData.class);
            lists.add(weatherData);
        }
        System.out.println("未来七天天气预报：");
        for (WeatherData list : lists) {
            System.out.println(list);
        }
        Today time = JSONObject.parseObject(sk.toString(), Today.class);
        Today today1 = JSONObject.parseObject(today.toString(), Today.class);
        today1.setTime(time.getTime());
        System.out.println("实时天气：");
        System.out.println(today1);
    }
}
