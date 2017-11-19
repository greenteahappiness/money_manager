package monikamichael.money_manager.engine;

import org.junit.Test;

public class CurrencyTest {

    @Test
    public void acceptsValueWithCurrencyZloty() {
        String value = "125 zł";
        int valueInt = Currency.parseString(value);
        assert valueInt == 12500;
    }
    @Test
    public void acceptsValueWithNoCurrency() {
        String value = "125";
        int valueInt = Currency.parseString(value);
        assert valueInt == 12500;
    }

    @Test
    public void acceptsValueWithComma() {
        String value = "1,25";
        int valueInt = Currency.parseString(value);
        assert valueInt == 125;
    }

    @Test
    public void acceptsValueWithDot() {
        String value = "1.5";
        int valueInt = Currency.parseString(value);
        assert valueInt == 150;
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsExceptionIfOtherCurrencyThanZloty() {
        String value = "124 EUR";
        Currency.parseString(value);
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsExceptionIfValueIsNotPrice() {
        String value = "abcd";
        Currency.parseString(value);
    }
}
