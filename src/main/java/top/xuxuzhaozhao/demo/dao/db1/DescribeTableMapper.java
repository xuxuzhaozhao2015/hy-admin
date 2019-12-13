package top.xuxuzhaozhao.demo.dao.db1;

import org.apache.ibatis.annotations.Param;
import top.xuxuzhaozhao.demo.domain.DescribeTable;


import java.util.List;
import java.util.Map;

public interface DescribeTableMapper {

    List<DescribeTable> selectDescribeTableByName(@Param("tableName")String tableName);

    List<Map<String,Object>> selectTableNameData(@Param("tableName")String tableName);
}
