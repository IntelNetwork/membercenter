package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGProAttachService;
import org.smartwork.dal.entity.ZGProAttach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"项目/任务附件管理"})
@RestController
@RequestMapping("/zgproattach")
public class ZGProAttachController extends BaseProvider<IZGProAttachService, ZGProAttach> {
}