package com.indexlabs.config;

import com.indexlabs.pages.RandomDateGeneratorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("chrome-browser")
public class ChromeBrowserConfig {

    @Bean
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @Bean
    public RandomDateGeneratorPage randomDateGeneratorPage() {
        return new RandomDateGeneratorPage(chromeDriver());
    }
}
