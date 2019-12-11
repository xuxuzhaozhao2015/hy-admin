package top.xuxuzhaozhao.demo.dao.db1;

import top.xuxuzhaozhao.demo.core.universal.Mapper;
import top.xuxuzhaozhao.demo.domain.SystemLog;

import java.util.List;

public interface SystemLogMapper extends Mapper<SystemLog> {

    Integer insertByBatch(List<SystemLog> list);
}