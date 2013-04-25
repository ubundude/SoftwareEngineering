package school;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAuthSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request,
                                        HttpServletResponse response) {
          return goToUrl;
    }

    private String goToUrl;

    public void setUrl(String goToUrl) {
        this.goToUrl = goToUrl;

    }
}
