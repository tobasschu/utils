package de.tschumacher.utils.validation;

import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {

    @Test
    public void shouldReturnFalseForStringsWithoutDomain() {
        String emailAddress = "tobias";

        boolean valid = EmailValidator.validate(emailAddress);
        Assert.assertFalse(valid);
    }

    @Test
    public void shouldReturnFalseForStringsWithJustDomain() {
        String emailAddress = "@gmail.com";

        boolean valid = EmailValidator.validate(emailAddress);
        Assert.assertFalse(valid);
    }

    @Test
    public void shouldReturnTrueForSimpleDomain() {
        String emailAddress = "tobias@schumacher.de";

        boolean valid = EmailValidator.validate(emailAddress);
        Assert.assertTrue(valid);
    }

    @Test
    public void shouldReturnTrueForComplexDomain() {
        String emailAddress = "Tobias@test.test-test.de";

        boolean valid = EmailValidator.validate(emailAddress);
        Assert.assertTrue(valid);
    }

    @Test
    public void shouldReturnTrueForEmailAddressWithPluSign() {
        String emailAddress = "Tobias+test@schumacher.de";

        boolean valid = EmailValidator.validate(emailAddress);
        Assert.assertTrue(valid);
    }

}