package org.pettyfox.framework.gateway.commapi;

import org.pettyfox.base.web.rest.RestObjectResponse;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 服务器软件版本信息
 * @author Petty Fox
 */
@Controller
@RequestMapping("commApi/version")
@Slf4j
public class VersionController {

    @Value("${project.version}")
    private String serviceVersion;
    @javax.annotation.Resource
    private ResourceLoader resourceLoader;
    @GetMapping("backVersionLog.html")
    public void backVersionLog(HttpServletResponse response) throws IOException {
        responseMd(response,"classpath:/server-version-log.md","后端版本更新日志");
    }
    @GetMapping("webVersionLog.html")
    public void frontVersionLog(HttpServletResponse response) throws IOException {
        responseMd(response,"classpath:/web-version-record.md","前端版本更新日志");
    }
    @GetMapping("/")
    @ResponseBody
    public RestObjectResponse<String> version(){
        return RestObjectResponse.ok(serviceVersion);
    }
    private void responseMd(HttpServletResponse response,String filename,String title) throws IOException {
        Resource resource = resourceLoader.getResource(filename);
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String data = "";
        StringBuffer sb = new StringBuffer();
        while((data = br.readLine()) != null) {
            sb.append(data).append("\n");
        }
        br.close();
        isr.close();
        is.close();

        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse(sb.toString());
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(String.format("<html><head><title>%s</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head><body>%s</body></html>",title,html));
    }
}
