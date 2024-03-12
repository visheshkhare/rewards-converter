import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardValueTests {

    @Test
    void create_with_cash_value() {
        double cashValue = 0;
        var rewardValue = new RewardValue();
        assertEquals(cashValue, rewardValue.getCashValue());
    }

    @Test
    void create_with_miles_value() {
        int milesValue = 10000;
        var rewardValue = new RewardValue(milesValue);
        assertEquals(milesValue, rewardValue.getMilesValue());
    }

    @Test
    void convert_from_cash_to_miles() throws Exception {
        RewardValue rewardValue = new RewardValue();
        double cashAmount = 100.0; // Example cash amount
        double expectedMiles = 28571.428571428572; // Example expected miles based on conversion rate

        // Using reflection to access the private method
        Method cashToMilesMethod = RewardValue.class.getDeclaredMethod("cashToMiles", double.class);
        cashToMilesMethod.setAccessible(true); // Allow access to private method
        double actualMiles = (double) cashToMilesMethod.invoke(rewardValue, cashAmount);

        assertEquals(expectedMiles, actualMiles, 0.001);
    }

    // Helper method to access private method cashToMiles(double) in RewardValue class
    private double cashToMiles(double cashAmount) {
        return cashAmount / 10.0; // Example conversion rate: 1 cash = 10 miles
    }

    @Test
    public void testCashToMilesConversionUsingPublicMethod() {
        RewardValue rewardValue = new RewardValue();
        double cashAmount = 100.0; // Example cash amount
        double expectedMiles = 0; // Example expected miles based on conversion rate
        assertEquals(expectedMiles, rewardValue.calculateMilesFromCash(cashAmount), 0.001);
    }
}
