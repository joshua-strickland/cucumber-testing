package package1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationTools {

    AppiumDriver driver;

    final int ANDROID_INDEX = 0;
    final int IOS_INDEX = 1;

    public enum IdType {ACCESSIBILITY, RESOURCE, DEFAULT}

    public enum OperatingSystem {ANDROID, IOS}

    public OperatingSystem os;

    public AutomationTools(AppiumDriver driver, OperatingSystem os) {
        this.driver = driver;
        this.os = os;
    }

    public MobileElement getElement(String[] id, IdType idType, int waitTimeout) {
        MobileElement me = null;

        switch (os) {
            case ANDROID:
                //If we have the default idtype or it's hardset to resource ids, use this strategy.
                if (idType == IdType.DEFAULT || idType == IdType.RESOURCE) {
                    //This conditional should work, if it's not webviewonly, it's a hybrid or native page so use default strategy. if webview only, it won't meet 1st conditional.
                    if (waitTimeout > -1) { //if wait timeout is 0 or higher
                        //waituntilclickable works very well!
                        me = (MobileElement) new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.id(id[ANDROID_INDEX])));
                    } else { //wait timeout was -1 or lower, don't use wait.
                        me = (MobileElement) driver.findElement(By.id(id[ANDROID_INDEX]));
                    }

                } else { //idType is specific. If android, this must be accessibility id, or else it'd make no sense to change param.
                    if (waitTimeout > -1) {
                        me = (MobileElement) new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(id[ANDROID_INDEX])));
                    } else {
                        me = (MobileElement) driver.findElementByAccessibilityId(id[ANDROID_INDEX]);
                    }
                }
                break;
            case IOS:
                //If we have the default idtype or if the id on both iOS and android apps is an accessibility id, use this strategy.
                if (idType == IdType.DEFAULT || idType == IdType.ACCESSIBILITY) {
                    if (waitTimeout > -1) {
                        me = (MobileElement) new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(id[IOS_INDEX])));
                    } else {
                        me = (MobileElement) driver.findElementByAccessibilityId(id[IOS_INDEX]);
                    }

                } else { //idType is specific. If iOS, this must be resource id, or else it'd make no sense to change param. still never seen resource id on ios, but hey might as well add the 2 lines here too :P
                    if (waitTimeout > -1) {
                        me = (MobileElement) new WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.id(id[IOS_INDEX])));
                    } else {
                        me = (MobileElement) driver.findElement(By.id(id[IOS_INDEX]));
                    }
                }
                break;
        }

        return me;
    }

}
