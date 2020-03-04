package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"任务管理"})
@RestController
@RequestMapping("/zgtask")
public class ZGTaskController extends BaseProvider<IZGTaskService, ZGTask> {
}