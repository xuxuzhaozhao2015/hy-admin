package top.xuxuzhaozhao.demo.core.systemlog;

import org.springframework.stereotype.Component;
import top.xuxuzhaozhao.demo.domain.SystemLog;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class SystemLogQueue {
    private BlockingQueue<SystemLog> blockingQueue = new LinkedBlockingQueue<>() ;

    public void add(SystemLog systemLog){
        blockingQueue.add(systemLog);
    }

    public SystemLog poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}
