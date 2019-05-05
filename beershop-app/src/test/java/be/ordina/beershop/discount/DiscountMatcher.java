package be.ordina.beershop.discount;

import be.ordina.beershop.common.matcher.BaseEntityMatcher;
import be.ordina.beershop.domain.Discount;
import org.hamcrest.Description;

public class DiscountMatcher extends BaseEntityMatcher<Discount> {

    public static DiscountMatcher matchesDiscount(Discount discount) {
        return new DiscountMatcher(discount);
    }

    private DiscountMatcher(Discount discount) {
        super(discount);
    }

    @Override
    protected final boolean matchesSafely(Discount other) {
        return isEqual(this.objectToMatch.getPercentage(), other.getPercentage())
                && isEqual(this.objectToMatch.getStartDate(), other.getStartDate())
                && isEqual(this.objectToMatch.getEndDate(), other.getEndDate());
    }

    @Override
    protected void describeToEntity(Description description, Discount discount) {
        appendField(description, "percentage", discount.getPercentage()).appendText(",");
        appendField(description, "startDate", discount.getStartDate()).appendText(",");
        appendField(description, "endDate", discount.getEndDate()).appendText(",");
    }
}