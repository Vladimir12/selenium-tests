package com.wikia.webdriver.elements.mercury.components.discussions.common;

import java.net.URL;

public interface PostsCreator {

  boolean isSignInDialogVisible();

  boolean isPostButtonActive();

  boolean hasOpenGraph();

  PostsCreator click();

  PostsCreator closeGuidelinesMessage();

  CategoryPills clickAddCategoryButton();

  PostsCreator addTitleWith(final String text);

  PostsCreator clearTitle();

  PostsCreator addDescriptionWith(final String text);

  PostsCreator addDescriptionWith(final URL url);

  PostsCreator clearDescription();

  PostsCreator clickSubmitButton();
}
