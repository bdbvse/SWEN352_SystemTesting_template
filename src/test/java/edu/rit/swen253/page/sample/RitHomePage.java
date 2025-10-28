package edu.rit.swen253.page.sample;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


/**
 * A Page Object for the RIT Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class RitHomePage extends AbstractPage {

  private DomElement mainContentPanel;

  public RitHomePage() {
    super();
    // validate basic page structure
    try {
      mainContentPanel = findOnPage(By.tagName("main"))
        .findChildBy(By.cssSelector("div.advanced-page-content"))
        .findChildBy(By.cssSelector("div.field--name-field-content"));
    } catch (TimeoutException e) {
      fail("Main content panel not found");
    }
  }

  /**
   * Extract the complete collection of "Rating Info" views.
   */
  public List<RatingInfoView> getRatingViews() {
    // the Ratings panel is in the fifth <div> of the main content
    // WARN: this is fragile code
    return mainContentPanel.findChildBy(By.xpath("div[5]"))
      // scroll the "Areas of Study" list into view
      .doWith(listElement -> scrollToTopOfElement(listElement))
      // the statistics columns are organized in a layout div
      .findChildBy(By.cssSelector("div.row"))
      // extract each statistics column
      .findChildrenBy(By.cssSelector("div.card"))
      // build a Rating Info view object for each of these divs
      .stream()
      .map(RatingInfoView::new)
      .toList();
  }

  /**
   * Extract the complete collection of "Area of Study" links.
   */
  public List<RitAreaOfStudyLink> getStudyLinks() {
    // the Study Links panel is in the eight <div> of the main content
    // WARN: this is fragile code
    return mainContentPanel.findChildBy(By.xpath("div[8]"))
      // scroll the "Areas of Study" list into view
      .doWith(listElement -> scrollToTopOfElement(listElement))
      // the curriculum links are organized in an unordered list embedded in a layout div
      .findChildBy(By.cssSelector("div.row ul"))
      // extract each curriculum link
      .findChildrenBy(By.cssSelector("li > div.card"))
      // build an Area of Study link View Object for each of these divs
      .stream()
      .map(RitAreaOfStudyLink::new)
      .toList();
  }
}
