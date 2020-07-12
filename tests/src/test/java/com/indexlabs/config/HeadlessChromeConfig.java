package com.indexlabs.config;

import com.indexlabs.pages.RandomDateGeneratorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("chrome-headless")
public class HeadlessChromeConfig {

    @Bean
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");

        return new ChromeDriver(options);
    }

    @Bean
    public RandomDateGeneratorPage randomDateGeneratorPage() {
        return new RandomDateGeneratorPage(chromeDriver());
    }



}
