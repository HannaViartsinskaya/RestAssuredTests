import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.parsing.Parser;
import org.json.JSONObject;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;
import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;

public class Main {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = "https://petstore.swagger.io";
    }

    @Test(priority = 1)
    public void addPet() {

        Pet pet1 = new Pet(1, "Villy", "sold");
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//
//            //Converting the Object to JSONString
//            String jsonString = null;
//            jsonString = mapper.writeValueAsString(pet1);
        System.out.println(pet1.toString());

        given().urlEncodingEnabled(true)
                .headers("Accept", "application/json", "Content-type", "application/json")
                .body(pet1)
                .when()
                .post("v2/pet/")
                .then().statusCode(200).log().body();

    }

    @Test(priority = 1)
    public void uploadImagePost() {
        File myFile = new File("C://Users//avertinskaya//Documents//Lightshot//pexels-photo.jpg");
        if (myFile.exists())
            System.out.println("File exists");
        else
            System.out.println("File not found");

        given().urlEncodingEnabled(true)
                .multiPart("file", myFile, "image/png")
                .when()
                .post("v2/pet/1/uploadImage")
                .then().statusCode(200).log().all();


    }


    @Test(priority = 2)
    public void testPUTChangeName() {
        Pet pet1 = new Pet(1, "Villy2", "sold");
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(pet1);
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given().urlEncodingEnabled(true)
                .headers("Accept", "application/json", "Content-type", "application/json")
                .body(json).when()
                .put("v2/pet/")
                .then()
                .statusCode(200).log().all();
        System.out.println("Pet was changed = " + json);

    }


@Test(priority = 2)
public void testGetFindByStatusNegative(){
        given().urlEncodingEnabled(true)
                .headers("Accept", "application/json", "Content-type", "application/json")
                .get("v2/pet/sold")
                .then()
                .statusCode(404).log().all();
}

    @Test(priority = 3)
    public void testGetReceivePet() {

        given().urlEncodingEnabled(true)
                .headers("Accept", "application/json", "Content-type", "application/json")
                .when()
                .get("v2/pet/1/")
                .then()
                .statusCode(200).log().all();
    }



    @Test(priority = 4)

    public void deletePet() {

        given().urlEncodingEnabled(true)
                .headers("Accept", "application/json", "Content-type", "application/json")
                .when()
                .delete("v2/pet/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}
