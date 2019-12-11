package top.xuxuzhaozhao.demo.service;

import top.xuxuzhaozhao.demo.domain.SystemLog;
import top.xuxuzhaozhao.demo.core.universal.Service;

import java.util.List;

/**
* @Description: SystemLogService接口
* @author xcy
* @date 2019/12/04 16:09
*/
public interface SystemLogService extends Service<SystemLog> {
    Integer insertByBatch(List<SystemLog> list);
}