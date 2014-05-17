/**
 * $Id: ScheduleTaskSupport.java 110445 2012-10-24 07:42:23Z aiquan.yuan@XIAONEI.OPI.COM $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.geekway.task;

import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 自定义调度
 * 
 * @author Marshal(shuai.ma@opi-corp.com) Initial Created at 2012-3-2
 */
public class ScheduleTaskSupport extends TimerTask {

    private static final Log logger = LogFactory.getLog(ScheduleTaskSupport.class);

    private int times = -1;

    private Map<Runnable, Integer> taskMap;

    @Override
    public void run() {
        if (taskMap == null || taskMap.isEmpty()) {
            return;
        }
        times++;
        if (times < 0) {
            times = 0;
        }
        for (Runnable task : taskMap.keySet()) {
            int period = taskMap.get(task);//获取间隔数值
            
            if (period <=1 || times % period == 0) {//如果time累加到period指定的数据
                try {
                    task.run();
                } catch (Throwable t) {
                    logger.error("run() - exception ignored", t); //$NON-NLS-1$
                }
            }
        }
    }

    public void setTaskMap(Map<Runnable, Integer> taskMap) {
        this.taskMap = taskMap;
    }

}
