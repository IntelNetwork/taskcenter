package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskTagService;
import org.smartwork.dal.entity.ZGTaskTag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"任务标签管理"})
@RestController
@RequestMapping("/zgtasktag")
public class ZGTaskTagController extends BaseProvider<IZGTaskTagService, ZGTaskTag> {
}