package APISteps;

import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import utils.apiConstants;

public class GenerateTokenSteps {

   public static String token;
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json").body("{\n" +
                "\"email\":\"rawCRUD@gmail.com\",\n" +
                "    \"password\":\"Password123\"\n" +
                "\n" +
                "    }");
        Response generateTokenResponse = generateTokenRequest.when().post(apiConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();

        token = "Bearer "+generateTokenResponse.jsonPath().getString("token");

    }
}
