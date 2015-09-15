package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class AllTagsTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = "WidgetsCombined";
  private static ArrayList<WidgetPageObject> widgets;

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

    widgets = new ArrayList<>();
    widgets.add(new PollsnackWidgetPageObject(driver));
    widgets.add(new SoundCloudWidgetPageObject(driver));
    widgets.add(new SpotifyWidgetPageObject(driver));
    widgets.add(new TwitterWidgetPageObject(driver));
    widgets.add(new VKWidgetPageObject(driver));
    widgets.add(new WeiboWidgetPageObject(driver));

    String content = "";
    for (WidgetPageObject widget : widgets) {
      content += widget.getTag();
    }

    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(ARTICLE_NAME);
    articleContent.push(content, ARTICLE_NAME);
  }

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void AllTagsWidgetTest_001_isLoaded() {
    new ArticlePageObject(driver).openMercuryArticleByNameWithCbAndNoAds(wikiURL, ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
    }
  }
}