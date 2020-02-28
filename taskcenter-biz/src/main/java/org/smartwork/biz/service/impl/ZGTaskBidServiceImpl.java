package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.dal.entity.ZGTaskBid;
import org.smartwork.dal.mapper.ZGTaskBidMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskBidServiceImpl extends ServiceImpl<ZGTaskBidMapper, ZGTaskBid> implements IZGTaskBidService {
}