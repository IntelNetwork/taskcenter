package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskRelTagService;
import org.smartwork.dal.entity.ZGTaskRelTag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"任务标签关联表管理"})
@RestController
@RequestMapping("/zgtaskreltag")
public class ZGTaskRelTagController extends BaseProvider<IZGTaskRelTagService, ZGTaskRelTag> {
}