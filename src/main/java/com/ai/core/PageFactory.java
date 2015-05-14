package com.ai.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhoufan on 15/5/6.
 */
public class PageFactory {


    /**
     * Instantiate an instance of the given class, and set a lazy proxy for each of the WebElement and
     * List&lt;WebElement&gt; fields that have been declared, assuming that the field
     * name is also the HTML element's "id" or "name". This means that for the class:
     *
     * <code> public class Page { private WebElement submit; } </code>
     *
     * there will be an element that can be located using the xpath expression "//*[@id='submit']" or
     * "//*[@name='submit']"
     *
     * By default, the element or the list is looked up each and every time a method is called upon
     * it. To change this behaviour, simply annotate the field with the {@link org.openqa.selenium.support.CacheLookup}. To change
     * how the element is located, use the {@link org.openqa.selenium.support.FindBy} annotation.
     *
     * This method will attempt to instantiate the class given to it, preferably using a constructor
     * which takes a WebDriver instance as its only argument or falling back on a no-arg constructor.
     * An exception will be thrown if the class cannot be instantiated.
     *
     * @param browser           The driver that will be used to look up the elements
     * @param pageClassToProxy A class which will be initialised.
     * @return An instantiated instance of the class with WebElement and List&lt;WebElement&gt;
     * fields proxied
     * @see org.openqa.selenium.support.FindBy
     * @see org.openqa.selenium.support.CacheLookup
     */
    public static <T> T initPage(TRISBrowser browser, Class<T> pageClassToProxy) {
        T page = instantiatePage(browser, pageClassToProxy);
        return page;
    }


    private static <T> T instantiatePage(TRISBrowser browser, Class<T> pageClassToProxy) {
        try {
            try {
                //Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return pageClassToProxy.getConstructor(TRISBrowser.class).newInstance(browser);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
