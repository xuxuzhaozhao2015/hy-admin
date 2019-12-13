package top.xuxuzhaozhao.demo.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xuxuzhaozhao.demo.core.constant.ExcelConstants;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.core.util.ExcelUtil;
import top.xuxuzhaozhao.demo.core.util.UploadActionUtil;
import top.xuxuzhaozhao.demo.domain.DescribeTable;
import top.xuxuzhaozhao.demo.domain.User;
import top.xuxuzhaozhao.demo.model.ExcelData;
import top.xuxuzhaozhao.demo.service.DescribeTableService;
import top.xuxuzhaozhao.demo.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Resource
    private IUserService userInfoService;

    @Autowired
    private DescribeTableService describeTableService;

    /**
     * 动态导出excel表格
     */
    @GetMapping("/export")
    public void test(HttpServletResponse response, @RequestParam String tableName) {
        List<DescribeTable> describeTables = describeTableService.selectDescribeTableByName(tableName);

        ExcelData data = new ExcelData();
        data.setName(tableName);
        List<String> titles = new ArrayList<>();
        for (DescribeTable table : describeTables) {
            titles.add(table.getField());
        }
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList<>();

        List<Map<String, Object>> tableDatas = describeTableService.selectTableNameData(tableName);

        for (Map<String, Object> tableData : tableDatas) {
            List<Object> row = new ArrayList<>();
            for (String title : titles) {
                Object columnValue = tableData.get(title);
                row.add(columnValue);
            }
            rows.add(row);
        }

        data.setRows(rows);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = tableName + "_" + sdf.format(new Date());
            ExcelUtil.exportExcel(response, fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/import")
    public Object excelImport(HttpServletRequest request, @RequestParam String tableName) throws Exception {
        //1、上传文件
        List<String> fileList = UploadActionUtil.uploadFile(request);
        if (fileList.isEmpty()) throw new Exception("没有获取到相关excel数据源");
        String filePath = fileList.get(0);

        // 2、获取表头信息
        List<DescribeTable> describeTables = describeTableService.selectDescribeTableByName(tableName);
        List<String> titles = new ArrayList();
        for (DescribeTable table : describeTables) {
            titles.add(table.getField());
        }

        // 3、读出Excel表
        InputStream inputStream = new FileInputStream(filePath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = hssfWorkbook.getSheet(tableName);
        if (sheet == null) throw new Exception("请确保Excel的Sheet名称为：" + tableName);

        // 4、将数据转为Map存起来
        List<Map<String, Object>> maps = new ArrayList<>();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = sheet.getRow(rowNum);
            if (hssfRow == null) continue;
            Map<String, Object> map = new HashMap<>();
            for (int cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
                HSSFCell hssfCell = hssfRow.getCell(cellNum);
                hssfCell.getStringCellValue();
                map.put(titles.get(cellNum), hssfCell.getStringCellValue());
            }

            maps.add(map);
        }
        // 5、组建insert语句前半部分
        StringBuilder fieldsb = new StringBuilder();
        for (String title : titles) {
            fieldsb.append(title).append(",");
        }
        String fields = fieldsb.toString();
        StringBuilder insert = new StringBuilder("INSERT INTO " + tableName + " (" + fields.substring(0, fields.length() - 1) + ") VALUES ");


        // 6、HashMap转为insert语句
        for (Map<String, Object> map : maps) {
            StringBuilder tempValue = new StringBuilder();
            for (String title : titles) {
                tempValue.append("'").append(map.get(title)).append("',");
            }
            String temps = tempValue.toString();
            insert.append("(").append(temps, 0, temps.length() - 1).append("),");
        }

        String insertSql = insert.toString();
        return RetResponse.makeOKRsp(insertSql.substring(0, insertSql.length() - 1));
    }

    @GetMapping("/export2")
    public Object test2(HttpServletResponse response, @RequestParam String tableName) {

        List<Map<String, Object>> tableDatas = describeTableService.selectTableNameData(tableName);
        return RetResponse.makeOKRsp(tableDatas);
//        List<User> list = userInfoService.selectAll();
//        ExcelData data = new ExcelData();
//        data.setName("hello");
//        List<String> titles = new ArrayList();
//        titles.add("ID");
//        titles.add("userName");
//        titles.add("password");
//        data.setTitles(titles);
//
//        List<List<Object>> rows = new ArrayList();
//        for (int i = 0, length = list.size(); i < length; i++) {
//            User userInfo = list.get(i);
//            List<Object> row = new ArrayList();
//            row.add(userInfo.getId());
//            row.add(userIndfo.getUsername());
//            row.add(userInfo.getPassword());
//            rows.add(row);
//        }
//        data.setRows(rows);
//        try {
//            ExcelUtil.exportExcel(response, "test2", data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
