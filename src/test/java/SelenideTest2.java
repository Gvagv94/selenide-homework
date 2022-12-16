import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

    @Listeners({ SoftAsserts.class, ScreenShooter.class})

    public class SelenideTest2 {

        public SelenideTest2() {
            Configuration.timeout = 2000;
            Configuration.browser = "Chrome";
            Configuration.browserSize = "1920x1080";
            Configuration.reportsFolder="src/main/resources/Reports/";
            ScreenShooter.captureSuccessfulTests = false;



        }

        @Test
        public void FindeBooks() {
            Configuration.assertionMode=SOFT;
            open("https://demoqa.com/books");

            ElementsCollection books = $(".rt-table").find(".rt-tbody").findAll(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(matchText("JavaScript"));
            books.shouldBe(CollectionCondition.size(10));
            books.get(0).shouldHave(text("Learning JavaScript Design Patterns"));
            books.stream().forEach(el ->{el.find(".mr-2").click(); back();});

        }

        @Test
        public void CheckImage() {
            open("https://demoqa.com/books");
            ElementsCollection books = $(".rt-tbody").$$(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(matchText("JavaScript"));
            ElementsCollection bookimages = $(".rt-table").$(".rt-tbody").findAll(".rt-tr-group");
            bookimages.stream().forEach(el -> el.shouldHave(attribute("alt", "image")));

        }

    }

