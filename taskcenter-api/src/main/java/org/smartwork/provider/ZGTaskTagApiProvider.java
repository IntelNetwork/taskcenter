package org.smartwork.provider;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${smartwork.verision}/task-type")
@Api(tags={"API任务类型"})
@Slf4j
public class ZGTaskTagApiProvider {
}
