package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskAttachService;
import org.smartwork.dal.entity.ZGTaskAttach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"任务附件管理"})
@RestController
@RequestMapping("/zgtaskattach")
public class ZGTaskAttachController extends BaseProvider<IZGTaskAttachService, ZGTaskAttach> {
}