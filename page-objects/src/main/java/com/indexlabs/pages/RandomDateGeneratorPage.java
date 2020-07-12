package com.indexlabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RandomDateGeneratorPage extends BasePage {

    @FindBy(id = "option-count-147fe348")
    private WebElement dateCount;

    @FindBy(id = "option-format-147fe348")
    private WebElement dateOutputFormat;

    @FindBy(id = "option-custom-format-147fe348")
    private WebElement customDateFormat;

    @FindBy(id = "option-start-147fe348")
    private WebElement startDate;

    @FindBy(id = "option-end-147fe348")
    private WebElement endDate;

    @FindBy(css = ".btn1")
    private WebElement generateButton;

    @FindBy(css = ".data")
    private WebElement dateTextArea;


    public RandomDateGeneratorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String [] getGeneratedDateValues() {
        String[] values = dateTextArea.getAttribute("value").split("\\n");

        if (values.length == 1 && values[0].isEmpty()) {
            return new String[0];
        }
        return values;
    }

    public void setDateCount(String count) {
        dateCount.clear();
        dateCount.sendKeys(count);
    }

    public void setCustomDateFormat(String text) {
        customDateFormat.clear();
        customDateFormat.sendKeys(text);
    }

    public void setStartDate(String text) {
        startDate.clear();
        startDate.sendKeys(text);
    }

    public void setEndDate(String text) {
        endDate.clear();
        endDate.sendKeys(text);
    }

    public void clickGenerateButton() {
        generateButton.click();
    }

    public void selectDateOutputFormat(String text) {
        try {
            if(super.isNullOrEmpty(text)) {
                throw new IllegalArgumentException("Must not be null or empty");
            }
            Select dateOutputDropDown = new Select(dateOutputFormat);
            dateOutputDropDown.selectByValue(text); }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStartAndEndDates(String startDate, String endDate) {
        setStartDate(startDate);
        setEndDate(endDate);
    }
}
