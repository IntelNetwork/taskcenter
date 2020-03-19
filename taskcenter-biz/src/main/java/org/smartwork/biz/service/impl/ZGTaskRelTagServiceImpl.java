package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskRelTagService;
import org.smartwork.dal.entity.ZGTaskRelTag;
import org.smartwork.dal.mapper.ZGTaskRelTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZGTaskRelTagServiceImpl extends ServiceImpl<ZGTaskRelTagMapper, ZGTaskRelTag> implements IZGTaskRelTagService {

    @Autowired
    ZGTaskRelTagMapper zgTaskRelTagMapper;

    @Override
    public List<String> selectTagName(Long taskId) {
        return zgTaskRelTagMapper.selectTagName(taskId);
    }
}