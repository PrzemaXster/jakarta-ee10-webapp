package org.pgrabarek.jakartawebproject.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

@WebServlet(name = "velocityServlet", value = "/hello-velocity")
public class VelocityServlet extends VelocityViewServlet {

    @Override
    public Template handleRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            Context context) {
        return getTemplate("/templates/index.vm");
    }
}
