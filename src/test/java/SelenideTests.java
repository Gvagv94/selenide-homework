import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Listeners({ SoftAsserts.class})
public class SelenideTests {
    public SelenideTests() {
        baseUrl = "http://the-internet.herokuapp.com";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        timeout=20000;
        holdBrowserOpen=false;
        reopenBrowserOnFail = true;
        fastSetValue=true;
    }

    @Test
    public void checkboxes(){
        open("/checkboxes");
        $(byTagName("form")).findAll("input").forEach(el->{
            if(!el.has(checked)){
                el.setSelected(true);
            }
        });
        $(byTagName("form")).findAll("input").forEach(el -> {
            System.out.println(el.shouldBe(checked));
            System.out.println(el.shouldHave(type("checkbox")));
        });
    }

    @Test
    public void daropdown(){
        open("/dropdown");
        $(byId("dropdown")).shouldBe(text("Please select an option"));
        $(byId("dropdown")).selectOptionContainingText("Option 2");
        $(byId("dropdown")).shouldHave(text("Option 2"));
    }

    @Test
    public void demoqaText(){

        open("https://demoqa.com/text-box");
        $(byId("userName")).setValue("Gvantsa Shanava");
        $("form #userEmail").setValue("g.shanava@mail.com");
        $(byTagName("textarea")).setValue("some address");
        $(byXpath("//*[@id=\"permanentAddress\"]")).sendKeys("Petriashvili Str");

        //შეგვიძლია პირდაპირ მთლიანი ფორმა ავიღოთ და ციკლი გადავატაროთ შესავსებად მაგ:
//        ElementsCollection elems =  $$(byClassName("form-control"));
//        elems.get(elems.size()-1).setValue("permanent address");


        executeJavaScript("window.scrollBy(0,500)");
        $(byId("submit")).click();
        $$(byClassName("mb-1")).shouldHave(exactTexts("Name:Gvantsa Shanava", "Email:g.shanava@mail.com", "Current Address :some address", "Permananet Address :Petriashvili Str"));
    }
}
