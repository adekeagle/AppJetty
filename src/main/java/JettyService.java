import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JettyService {
    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1L, "Hello", "EN");
    private static Logger logger = LoggerFactory.getLogger(JettyService.class);

    private LangRepository langRepository;

    public JettyService() {
        this(new LangRepository());
    }

    public JettyService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    String prepareGreeting(String name){
//        String result = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return prepareGreeting(name, null);
    }

    String prepareGreeting(String name, String longValue){
        Long langId;
        try{
            langId = Optional.ofNullable(longValue).map(Long::valueOf).orElse(FALLBACK_LANG.getId());
        }catch (NumberFormatException ne){
            logger.warn("Non-numeric Language id used: " + longValue);
            langId = FALLBACK_LANG.getId();
        }
        String welcomeMsg = langRepository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMessage();
        String nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg +  " " + nameToWelcome + "!";
    }
}
