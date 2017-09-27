package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CommunityBadge;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.HeroUnit;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Moderation;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Promoting;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.SortingFiltersOnDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.DiscussionsHeader;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;

import com.google.common.base.Predicate;
import lombok.Getter;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class PostsListPage extends PageWithPosts {

  public static final String PATH = "/d/f";

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final PostEditor postEditor = new PostEditor();

  @Getter(lazy = true)
  private final CommunityBadge communityBadge = new CommunityBadge();

  @Getter(lazy = true)
  private final HeroUnit heroUnit = new HeroUnit();

  @Getter(lazy = true)
  private final Moderation moderation = new Moderation();

  @Getter(lazy = true)
  private final PostsCreatorDesktop postsCreatorDesktop = new PostsCreatorDesktop();

  @Getter(lazy = true)
  private final PostsCreatorMobile postsCreatorMobile = new PostsCreatorMobile();

  @Getter(lazy = true)
  private final Promoting promoting = new Promoting();

  @Getter(lazy = true)
  private final SortingFiltersOnDesktop sortingFiltersOnDesktop = new SortingFiltersOnDesktop();

  @Getter(lazy = true)
  private final DiscussionsHeader discussionsHeader = new DiscussionsHeader();

  @Getter(lazy = true)
  private final FiltersPopOver filtersPopOver = new FiltersPopOver();

  @Getter(lazy = true)
  private final SignInToFollowModalDialog
      signInToFollowModalDialog =
      new SignInToFollowModalDialog();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  @Getter(lazy = true)
  private final CategoriesFieldset categories = new CategoriesFieldset();


  public PostsListPage open() {
    driver.get(urlBuilder.getUrlForWiki() + PATH);
    waitForPageReload();
    return this;
  }

  public void waitForPageReloadWith(final String categoryName) {
    waitForPageReload();

    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      new FluentWait<>(getPost())
          .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
          .until((Predicate<Post>) p -> p.getPosts().stream()
              .allMatch(postEntity -> postEntity.findCategory().endsWith(categoryName)));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

}
