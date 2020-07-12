package com.indexlabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.stream.Stream;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void clearTextField(Stream<WebElement> webElements) {
        webElements.forEach(WebElement::clear);
    }

    protected boolean isNullOrEmpty(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
