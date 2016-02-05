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

  private static MoneyFormatter mf = new MoneyFormatterBuilder().appendAmountLocalized()
      .appendLiteral(" ").appendCurrencySymbolLocalized().toFormatter(DEFAULT_LOCALE);

  public static BigMoney sum(final List<BigMoney> moneyList) {
    if (moneyList == null || moneyList.size() <= 0)
      throw new java.lang.IllegalAccessError();

    final BigMoney money = BigMoney.of(moneyList.get(0).getCurrencyUnit(), 0.0);
    for (final BigMoney moneyItem : moneyList) {
      money.plus(moneyItem);
    }
    return money;
  }

  public static String toString(BigMoney money) {
    return mf.print(money.withScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE));
  }

  public static BigMoney calulateNetFromGross(final BigMoney price) {
    return price.dividedBy(1.0 + MWST, DEFAULT_ROUNDING_MODE);
  }


  public static BigMoney calulateMwstFromGross(final BigMoney price) {
    return price.multipliedBy(MWST);
  }
}
