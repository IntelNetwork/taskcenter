package org.smartwork.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.smartwork.dal.entity.ZGTaskRelTag;

import java.util.List;

public interface ZGTaskRelTagMapper extends BaseMapper<ZGTaskRelTag> {

    List<String> selectTagName(@Param(value = "taskId")Long taskId);
}