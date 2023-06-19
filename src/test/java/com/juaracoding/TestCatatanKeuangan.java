package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestCatatanKeuangan {
    AndroidDriver<MobileElement> driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "Pixel 2 API 30");
        dc.setCapability("udid", "emulator-5554");
        dc.setCapability("platformName", "android");
        dc.setCapability("appPackage", "com.chad.financialrecord");
        dc.setCapability("appActivity", "com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset", "true");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(url, dc);
    }


    @Test (priority = 1)
    public void inputPengeluaran() {
        WebDriverWait wait = new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.chad.financialrecord:id/action_bar_root")));
        MobileElement tombolMenu = driver.findElementById("com.chad.financialrecord:id/fabMenu");
        tombolMenu.click();
        MobileElement jumlah = driver.findElementById("com.chad.financialrecord:id/etAmount");
        jumlah.sendKeys("50000");
        MobileElement keterangan = driver.findElementById("com.chad.financialrecord:id/etNote");
        keterangan.sendKeys("sarapan");
        MobileElement tombolSave = driver.findElementById("com.chad.financialrecord:id/btSave");
        tombolSave.click();
        MobileElement expendResult = driver.findElementById("com.chad.financialrecord:id/tvExpense");
        String actualResult =  expendResult.getText();
        String expectResult = "50.000";
        Assert.assertEquals(actualResult,expectResult);
    }

    @Test (priority = 2)
    public void inputPemasukan() {
        MobileElement tombolMenu1 = driver.findElementById("com.chad.financialrecord:id/fabMenu");
        tombolMenu1.click();
        MobileElement tombolPemasukan = driver.findElementById("com.chad.financialrecord:id/btnIncome");
        tombolPemasukan.click();
        MobileElement jumlah1 = driver.findElementById("com.chad.financialrecord:id/etAmount");
        jumlah1.sendKeys("100000");
        MobileElement keterangan1 = driver.findElementById("com.chad.financialrecord:id/etNote");
        keterangan1.sendKeys("Gajian");
        MobileElement tombolSave1 = driver.findElementById("com.chad.financialrecord:id/btSave");
        tombolSave1.click();
        MobileElement expendResult = driver.findElementById("com.chad.financialrecord:id/tvCurrencyIncome");
        String actualResult =  expendResult.getText();
        String expectResult = "100.000";
        Assert.assertEquals(actualResult,expectResult);
    }
}
