import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloWorld", urlPatterns = {"/api/*"})
public class JettyServlet extends HttpServlet {
    private final static String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private static Logger logger = LoggerFactory.getLogger(JettyServlet.class);

    private JettyService jettyService;

    public JettyServlet() {
        this(new JettyService());
    }

    public JettyServlet(JettyService jettyService) {
        this.jettyService = jettyService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters " + req.getParameterMap());
//        resp.getWriter().write("Hello World");
        String name = req.getParameter(NAME_PARAM);
        String lang = req.getParameter(LANG_PARAM);
        resp.getWriter().write(jettyService.prepareGreeting(name, lang));
    }
}
