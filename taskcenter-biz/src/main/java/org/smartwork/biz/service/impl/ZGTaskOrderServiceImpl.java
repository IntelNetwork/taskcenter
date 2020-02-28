package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.smartwork.dal.mapper.ZGTaskOrderMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskOrderServiceImpl extends ServiceImpl<ZGTaskOrderMapper, ZGTaskOrder> implements IZGTaskOrderService {
}