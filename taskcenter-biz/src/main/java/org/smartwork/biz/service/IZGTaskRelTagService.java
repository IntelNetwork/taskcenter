package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.dal.entity.ZGTaskRelTag;

import java.util.List;

public interface IZGTaskRelTagService extends IService<ZGTaskRelTag> {

    List<String> selectTagName(Long taskId);
}