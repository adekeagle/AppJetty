import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class JettyServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_prepareGreeting_nullName_returnGreetingWithFallbackName(){

        LangRepository mockRepo = alwaysReturnHelloRepository();

        //given
        JettyService SUT = new JettyService(mockRepo);

        // when
        String result = SUT.prepareGreeting(null, "-1");

        Assert.assertEquals(WELCOME + " " + JettyService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnGreetingWithFallback(){

        JettyService SUT = new JettyService();
        String name = "test";

        //given + when
        String result = SUT.prepareGreeting(name, "-1");

        Assert.assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nulllang_returnGreetingWithFallbackLang(){

        LangRepository mockRepo = fallbackLangIdRepo();

        //given
        JettyService SUT = new JettyService(mockRepo);

        // when
        String result = SUT.prepareGreeting(null, null);

        Assert.assertEquals(FALLBACK_ID_WELCOME + " " + JettyService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textlang_returnGreetingWithFallbackLang(){

        LangRepository mockRepo = fallbackLangIdRepo();

        //given
        JettyService SUT = new JettyService(mockRepo);

        // when
        String result = SUT.prepareGreeting(null, "abc");

        Assert.assertEquals(FALLBACK_ID_WELCOME + " " + JettyService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackIdLang() throws Exception{

        LangRepository mockRepo = new LangRepository(){
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.empty();
            }
        };

        //given
        JettyService SUT = new JettyService(mockRepo);

        // when
        String result = SUT.prepareGreeting(null, null);

        Assert.assertEquals(JettyService.FALLBACK_LANG.getWelcomeMessage() + " " + JettyService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepo() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Long id) {
                if(id.equals(JettyService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturnHelloRepository(){
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Long id){
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
