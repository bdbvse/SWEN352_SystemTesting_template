package edu.rit.swen253.page.sample;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;


/**
 * A View Object that contains rating information.
 *
 * <p>
 * Example:
 * <pre>
 *   <div class="card-text ">
 * 			<div class="rankings-badge-text">
 *        <p class="rankings-badge-text--number">
 *          #6
 *        </p>
 *        <p class="rankings-badge-text--title">
 *          <strong>Top School for Co-op or Internship Programs</strong>
 *        </p>
 *        <p class="rankings-badge-text--description">...</p>
 *     </div>
 *   </div>
 * </pre>
 */
public class RatingInfoView {

  private final DomElement viewContainer;

  public RatingInfoView(final DomElement viewContainer) {
    this.viewContainer = viewContainer;
  }

  public String getRating() {
    return viewContainer.findChildBy(By.cssSelector("p.rankings-badge-text--number")).getText();
  }

  public String getTitle() {
    return viewContainer.findChildBy(By.cssSelector("p.rankings-badge-text--title")).getText();
  }
}
