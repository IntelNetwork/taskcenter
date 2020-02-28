package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskTypeService;
import org.smartwork.dal.entity.ZGTaskType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"任务类型管理"})
@RestController
@RequestMapping("/zgtasktype")
public class ZGTaskTypeController extends BaseProvider<IZGTaskTypeService, ZGTaskType> {
}