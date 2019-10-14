package cn.zhengsigen.test.demo.testdemo;

import lombok.Data;

@Data
public class WeatherData {
    // 时间
    private String date;
    // 天气
    private String weather;
    // 高温低温
    private String temperature;
}
