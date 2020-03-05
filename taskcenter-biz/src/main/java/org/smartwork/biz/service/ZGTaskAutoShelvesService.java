package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.Result;
import org.smartwork.dal.entity.ZGTask;

/**
 * @description: 任务超时自动下架
 * @author: xfx
 * @date: Created in 2020/3/5 9:21
 * @version: 1.0
 * @modified By:
 */
public interface ZGTaskAutoShelvesService extends IService<ZGTask> {

    /**
     * @description 任务超时自动下架
     * @author xfx
     * @date 2020/3/5 9:24
     * @parameter
     * @return
     */
    boolean shelves(ZGTask zgTask);
}
