package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.mapper.ZGTaskMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskServiceImpl extends ServiceImpl<ZGTaskMapper, ZGTask> implements IZGTaskService {
}