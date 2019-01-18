package core;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Integer.*;

public class Boot {

    public static void start() throws Exception {
        Core core = new CoreBuilder()
                .build()
                .getCore();

        Server server = new Server(8080);
        server.setHandler(new Handler(core));
        server.start();
        server.join();
    }



}

class Handler extends AbstractHandler {

    private Core core;

    public Handler(Core core) {
        super();
        this.core = core;
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (target.equals("/admin")) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);

            var chainNames = core.getChainNames();

            StringBuilder p = new StringBuilder();
            for (String name : chainNames) {
                p.append("<p style=\"color:#a0e2ff\">")
                        .append(name).append(" : ")
                        .append(core.getChainNameAsString(name))
                        .append("</p>\n");
            }

            var html = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body style=\"background-color:#042430;\">\n" +
                    "\n" +
                    p +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";

            response.getWriter().println(html);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            System.out.println(target);

            var params = target
                    .replace("/", "")
                    .split("&");

            var chainName = params[0];
//            var in = params[1];

            var buffReader = baseRequest.getReader();

            var in0 = buffReader.readLine();
            System.out.println(in0);

//            var inParams = parseInParams(in0);

//            core.process(chainName, inParams);

            Object result = core.getSynch(chainName);
            response.getWriter().println(result);
        }

    }



}