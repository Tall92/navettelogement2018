package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kane
 */
public class GestionRestriction implements Filter {

    public static final String ATT_CON = "/gestionufr";
    public static final String ATT_SES = "ses_ges";

    /**
     *
     * @param req The servlet request we are processing
     * @param res The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String chemin = request.getRequestURI().substring(request.getContextPath().length());
        
        if (chemin.startsWith("/bootstrap-4.1.2") || chemin.startsWith("/css") || chemin.startsWith("/font-awesome-4.7.0")) {
            chain.doFilter(request, response);
            return;
        }

        String query = request.getQueryString();

        if (query != null) {

            if (session.getAttribute(ATT_SES) == null) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + ATT_CON);
                return;

            }
        } else {
            if (chemin.startsWith("/bootstrap-4.1.2") || chemin.startsWith("/css") || chemin.startsWith("/font-awesome-4.7.0")) {
                chain.doFilter(request, response);
                return;
            }
        }

        if (session.getAttribute(ATT_SES) == null) {
            session.invalidate();
            request.getRequestDispatcher(ATT_CON).forward(
                    request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

}
