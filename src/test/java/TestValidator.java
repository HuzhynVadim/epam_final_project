import org.junit.Test;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.validator.LoginValidator;
import ua.nure.huzhyn.validator.SearchValidator;

public class TestValidator {

    @Test(expected = IncorrectDataException.class)
    public void testLoginValidator() {
        LoginValidator loginValidator = new LoginValidator();
        loginValidator.isValid("ghrok@#$com", "ghrok231");
    }

    @Test(expected = IncorrectDataException.class)
    public void testSearchValidator() {
        SearchValidator searchValidator = new SearchValidator();
        searchValidator.isValidSearch("Odessa", "Harkov");
    }
}
