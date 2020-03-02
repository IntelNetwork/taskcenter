package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.Result;
import org.smartwork.dal.entity.ZGTask;
import src.java.org.smartwork.comm.model.ZGTaskDto;

/***
 * 任务接口层
 */
public interface IZGTaskService extends IService<ZGTask> {


    /***
     * addZGTask方法概述:添加任务
     * @param  taskDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Result<ZGTask> addZGTask(ZGTaskDto taskDto);

}