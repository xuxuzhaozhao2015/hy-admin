package top.xuxuzhaozhao.demo.service.impl;

import org.springframework.stereotype.Service;
import top.xuxuzhaozhao.demo.dao.db1.DescribeTableMapper;
import top.xuxuzhaozhao.demo.domain.DescribeTable;
import top.xuxuzhaozhao.demo.service.DescribeTableService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DescribeTableServiceImpl implements DescribeTableService {
    @Resource
    private DescribeTableMapper describeTableMapper;

    @Override
    public List<DescribeTable> selectDescribeTableByName(String tableName) {
        return describeTableMapper.selectDescribeTableByName(tableName);
    }

    @Override
    public List<Map<String, Object>> selectTableNameData(String tableName) {
        return describeTableMapper.selectTableNameData(tableName);
    }
}
