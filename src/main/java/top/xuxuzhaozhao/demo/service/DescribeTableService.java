package top.xuxuzhaozhao.demo.service;

import org.apache.ibatis.annotations.Param;
import top.xuxuzhaozhao.demo.domain.DescribeTable;

import java.util.List;
import java.util.Map;

public interface DescribeTableService {
    List<DescribeTable> selectDescribeTableByName(String tableName);

    List<Map<String,Object>> selectTableNameData(String tableName);
}
