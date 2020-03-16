package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskUserService;
import org.smartwork.dal.entity.ZGTaskUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"任务类型管理"})
@RestController
@RequestMapping("/zgtaskuser")
public class ZGTaskUserController extends BaseProvider<IZGTaskUserService, ZGTaskUser> {
}