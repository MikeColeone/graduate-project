package org.example.graduatemanage.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.example.graduatemanage.FileTest;
import org.example.graduatemanage.dox.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@Builder
@RequestMapping("/user")
@CrossOrigin(origins = """
        http://localhost:8080""")//解决跨域请求方式二
public class ExportController {
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String year = df.format(new Date());
        //为了方便大家理解，自己创建Uesr
        List<User> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {

            User user = User.builder().id(String.valueOf(i)).number("202221282"+i).name("小"+i).password("909090"+i).build();
            list.add(user);
        }
        //设置Excel表头
        String[] title = {"账号", "密码", "姓名",  "编号"};
        //设置Excel文件名
        String filename = "LeaderList_"+year+".xls";
        //设置工作表名称
        String sheetName = "sheet1";
        //开始对从数据库中获取到的数据进行处理
        String[][] content = new String[list.size()][6];
        try {
            for (int i = 0; i < list.size(); i++) {
                content[i][0] = String.valueOf(list.get(i).getNumber());
                content[i][1] = list.get(i).getPassword();
                content[i][2] = list.get(i).getName();
                content[i][5] = String.valueOf(list.get(i).getId());
            }
        } catch (Exception e) {
            log.error("exception message", e);
        }
        HSSFWorkbook wb = FileTest.getHSSFWorkbook(sheetName, title, content, null);
        try {
            // 响应到客户端
            this.setResponseHeader(response, filename);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.error("exception message", e);
        }
    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("exception message", e);
        }
    }
}
