package com.example.carvaluation.tests;

import com.example.carvaluation.config.CustomDriverManager;
import com.example.carvaluation.model.CarDetails;
import com.example.carvaluation.pages.CarValuationPage;
import com.example.carvaluation.util.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CarValuationTests {
    private WebDriver driver;
    private CarValuationPage valuationPage;

    @BeforeClass
    public void setUp() {
        driver = CustomDriverManager.getDriver();
        valuationPage = new CarValuationPage(driver);
    }

    @AfterClass
    public void tearDown() {
        CustomDriverManager.quitDriver();
    }

    @Test
    public void testCarValuationSG18HTN() throws IOException {
        testCarValuation("SG18 HTN");
    }

    @Test
    public void testCarValuationAD58VNF() throws IOException {
        testCarValuation("AD58 VNF");
    }

    @Test
    public void testCarValuationBW57BOF() throws IOException {
        testCarValuation("BW57 BOF");
    }

    @Test
    public void testCarValuationKT17DLX() throws IOException {
        testCarValuation("KT17 DLX");
    }

    private void testCarValuation(String registration) throws IOException {
        valuationPage.navigateTo(CustomDriverManager.getWebsiteUrl());
        valuationPage.enterRegistration(registration);
        valuationPage.clickSearch();
        CarDetails actualDetails = valuationPage.getCarDetails();
        actualDetails.setRegistration(registration); // Set registration manually

        List<CarDetails> expectedDetails = FileUtils.readExpectedOutput("src/main/resources/car_output.txt");
        CarDetails expected = null;
        for (CarDetails detail : expectedDetails) {
            if (detail.getRegistration().equalsIgnoreCase(registration)) {
                expected = detail;
                break;
            }
        }

        org.testng.Assert.assertNotNull(expected, "No expected details found for registration: " + registration);
        org.testng.Assert.assertEquals(actualDetails.getMake(), expected.getMake(), "Make mismatch for " + registration);
        org.testng.Assert.assertEquals(actualDetails.getModel(), expected.getModel(), "Model mismatch for " + registration);
        org.testng.Assert.assertEquals(actualDetails.getYear(), expected.getYear(), "Year mismatch for " + registration);
        org.testng.Assert.assertEquals(actualDetails.getValue(), expected.getValue(), 0.01, "Value mismatch for " + registration);
    }
}