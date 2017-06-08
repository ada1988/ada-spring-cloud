package org.ada.cloud.redis.session.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
 * Filename: SessionServlet.java  <br>
 *
 * Description: 观察session  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年5月31日 <br>
 *
 *  
 */

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                String attributeName = req.getParameter("attributeName");
                String attributeValue = req.getParameter("attributeValue");
                req.getSession().setAttribute(attributeName, attributeValue);
                resp.sendRedirect(req.getContextPath() + "/");
        }

        private static final long serialVersionUID = 2878267318695777395L;
}
