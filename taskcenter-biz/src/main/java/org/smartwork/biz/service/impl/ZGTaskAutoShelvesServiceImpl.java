package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.ZGTaskAutoShelvesService;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.mapper.ZGTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: TODO
 * @author: xfx
 * @date: Created in 2020/3/5 9:25
 * @version: 1.0
 * @modified By:
 */
@Slf4j
@Service
public class ZGTaskAutoShelvesServiceImpl extends ServiceImpl<ZGTaskMapper, ZGTask>implements ZGTaskAutoShelvesService {


    @Autowired
    private ZGTaskMapper zgTaskMapper;

    /**
     * @description 任务超时自动下架
     * @author xfx
     * @date 2020/3/5 9:26
     * @parameter
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<ZGTask> shelves(ZGTask zgTask) {
        return null;
    }
}
