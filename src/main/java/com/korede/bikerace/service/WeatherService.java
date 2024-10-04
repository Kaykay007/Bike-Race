package com.korede.bikerace.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public String getCurrentWeather(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl + "/weather")
                .queryParam("q", cityName)
                .queryParam("appid", apiKey)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getForecastWeather(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl + "/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", apiKey)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}