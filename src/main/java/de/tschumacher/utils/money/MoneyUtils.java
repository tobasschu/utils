package de.tschumacher.utils.money;

import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

import org.joda.money.BigMoney;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

public class MoneyUtils {

  private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
  private static final double MWST = 0.19;
  private static final Locale DEFAULT_LOCALE = Locale.GERMANY;
  private static final int DEFAULT_SCALE = 2;



  public static BigMoney sum(final List<BigMoney> moneyList) {
    if (moneyList == null || moneyList.size() <= 0)
      throw new java.lang.IllegalAccessError();

    BigMoney money = BigMoney.of(moneyList.get(0).getCurrencyUnit(), 0.0);
    for (final BigMoney moneyItem : moneyList) {
      money = money.plus(moneyItem);
    }
    return money;
  }

  public static String toString(BigMoney money, Locale locale, int scale, RoundingMode roundingMode) {
    final MoneyFormatter mf =
        new MoneyFormatterBuilder().appendAmountLocalized().appendLiteral(" ")
            .appendCurrencySymbolLocalized().toFormatter(locale);
    return mf.print(money.withScale(scale, roundingMode));
  }

  public static String toString(BigMoney money) {
    return toString(money, DEFAULT_LOCALE, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
  }

  public static String toString(BigMoney money, Locale locale) {
    return toString(money, locale, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
  }

  public static BigMoney calulateNetFromGross(final BigMoney price) {
    return calulateNetFromGross(price, MWST);
  }

  public static BigMoney calulateNetFromGross(final BigMoney price, double mwst) {
    return price.multipliedBy(1 / (1.0 + mwst));
  }

  public static BigMoney calulateMwstFromGross(final BigMoney price) {
    return price.minus(calulateNetFromGross(price));
  }

  public static BigMoney calulateMwstFromGross(final BigMoney price, double mwst) {
    return price.minus(calulateNetFromGross(price, mwst));
  }
}
