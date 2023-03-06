import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserAPI {
    public Response newUser (User user){
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .post(Endpoints.API_CREATE);
    }
    public static Response loginUser(User user){
        return
                given()
                        .header("Content-type", "application/json")
                        .body(user)
                        .when().post(Endpoints.API_LOGIN);
    }

    public Response deleteUser (User user){
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .delete(Endpoints.API_DELETE);
    }

    public ValidatableResponse getAllIngredients() {
        return given()
                .spec(Endpoints.getBaseSpec())
                .log().all()
                .get(Endpoints.INGREDIENTS_PATH)
                .then()
                .log().all();
    }

    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .auth().oauth2(accessToken)
                .log().all()
                .delete(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
    public ValidatableResponse updateUserWithAuth(User user, String accessToken) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .header("Authorization", accessToken)
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
    public ValidatableResponse updateUserWithoutAuth(User user) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }

}
