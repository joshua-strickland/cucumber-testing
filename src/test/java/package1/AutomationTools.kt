package package1

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class AutomationTools(var driver: AppiumDriver<*>, var os: OperatingSystem) {
    val ANDROID_INDEX = 0
    val IOS_INDEX = 1

    enum class IdType {
        ACCESSIBILITY, RESOURCE, DEFAULT
    }

    enum class OperatingSystem {
        ANDROID, IOS
    }

    fun getElement(id: Array<String?>, idType: IdType = IdType.DEFAULT, waitTimeout: Long = 20): MobileElement? {
        var me: MobileElement? = null
        me = when (os) {
            OperatingSystem.ANDROID ->
                if (idType == IdType.DEFAULT || idType == IdType.RESOURCE) {
                    if (waitTimeout > -1) { //if wait timeout is 0 or higher
                        WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.id(id[ANDROID_INDEX]))) as MobileElement
                    } else { //wait timeout was -1 or lower, don't use wait.
                        driver.findElement(By.id(id[ANDROID_INDEX])) as MobileElement
                    }
                } else { //idType is specific. If android, this must be accessibility id, or else it'd make no sense to change param.
                    if (waitTimeout > -1) {
                        WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(id[ANDROID_INDEX]))) as MobileElement
                    } else {
                        driver.findElementByAccessibilityId(id[ANDROID_INDEX]) as MobileElement
                    }
                }
            OperatingSystem.IOS ->  //If we have the default idtype or if the id on both iOS and android apps is an accessibility id, use this strategy.
                if (idType == IdType.DEFAULT || idType == IdType.ACCESSIBILITY) {
                    if (waitTimeout > -1) {
                        WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(id[IOS_INDEX]))) as MobileElement
                    } else {
                        driver.findElementByAccessibilityId(id[IOS_INDEX]) as MobileElement
                    }
                } else { //idType is specific. If iOS, this must be resource id, or else it'd make no sense to change param. still never seen resource id on ios, but hey might as well add the 2 lines here too :P
                    if (waitTimeout > -1) {
                        WebDriverWait(driver, waitTimeout).until(ExpectedConditions.elementToBeClickable(MobileBy.id(id[IOS_INDEX]))) as MobileElement
                    } else {
                        driver.findElement(By.id(id[IOS_INDEX])) as MobileElement
                    }
                }
        }
        return me
    }

}