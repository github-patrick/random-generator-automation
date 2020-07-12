package com.indexlabs.uitests;

import com.indexlabs.annotation.SmokeTest;
import com.indexlabs.config.ChromeBrowserConfig;
import com.indexlabs.config.HeadlessChromeConfig;
import com.indexlabs.pages.RandomDateGeneratorPage;
import com.indexlabs.utils.ActiveProfileResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HeadlessChromeConfig.class, ChromeBrowserConfig.class})
@ActiveProfiles(value = "chrome-headless",resolver = ActiveProfileResolver.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RandomDateGeneratorTestIT {

    @Autowired
    @Qualifier("chromeDriver")
    private WebDriver driver;

    @Autowired
    private RandomDateGeneratorPage randomDateGeneratorPage;

    private JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;


    private static final String URL = "https://codebeautify.org/generate-random-date";


    @BeforeAll
    public void setUp() {
        javascriptExecutor = (JavascriptExecutor)driver;
        driver.get(URL);
    }

    @BeforeEach
    public void beforeEach() {
        javascriptExecutor.executeScript("localStorage.clear()");
        driver.navigate().refresh();
    }

    @DisplayName("Should generate 10 random dates by default")
    @SmokeTest
    @Order(1)
    public void shouldGenerateTenRandomDates() {
        assertEquals(10, randomDateGeneratorPage.getGeneratedDateValues().length);
    }

    @DisplayName("Should generate more random dates")
    @SmokeTest
    @Order(2)
    public void shouldGenerateRandomDates() {
        String[] defaultValues = randomDateGeneratorPage.getGeneratedDateValues();
        randomDateGeneratorPage.clickGenerateButton();
        String[] generatedDateValues = randomDateGeneratorPage.getGeneratedDateValues();
        assertFalse(Arrays.equals(defaultValues, generatedDateValues));
    }

    @DisplayName("Should display specific amount of dates set")
    @SmokeTest
    @Order(3)
    public void shouldGenerateSpecificAmountOfDates() {
        randomDateGeneratorPage.setDateCount("20");
        assertEquals(20, randomDateGeneratorPage.getGeneratedDateValues().length);

    }

    @DisplayName("Should not generate dates using non positive numbers")
    @ParameterizedTest
    @ValueSource(strings = {"-1","-2.0", "0"})
    public void shouldRejectNegativeIntegers(String countText) {
        randomDateGeneratorPage.setDateCount(countText);
        assertEquals(0, randomDateGeneratorPage.getGeneratedDateValues().length);
    }

    @DisplayName("should display the correct format from the preset list")
    @Test
    public void shouldChangeDateOutputFormat() throws Exception {
        randomDateGeneratorPage.setDateCount("1");
        randomDateGeneratorPage.setStartAndEndDates("2020-01-01 00:00:00","2020-01-01 00:00:00");
        randomDateGeneratorPage.selectDateOutputFormat("month-date-year-hh-mm-ss");

        String dateString = Arrays.stream(randomDateGeneratorPage.getGeneratedDateValues()).findFirst().get();

        assertEquals("January 01 2020 00:00:00", dateString);
    }

    @DisplayName("should display the custom formatted date")
    @Test
    public void shouldDisplayDateCustomFormat() {
        randomDateGeneratorPage.setDateCount("1");
        randomDateGeneratorPage.setStartAndEndDates("2020-01-01 00:00:00","2020-01-01 00:00:00");
        randomDateGeneratorPage.selectDateOutputFormat("custom");
        randomDateGeneratorPage.setCustomDateFormat("YYYY-MM-DD");

        String dateString = Arrays.stream(randomDateGeneratorPage.getGeneratedDateValues()).findFirst().get();
        assertEquals("2020-01-01", dateString);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}
