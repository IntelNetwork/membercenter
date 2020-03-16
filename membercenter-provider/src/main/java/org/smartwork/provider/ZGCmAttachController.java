package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCmAttachService;
import org.smartwork.dal.entity.ZGCmAttach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"公司附件管理"})
@RestController
@RequestMapping("/zgcmattach")
public class ZGCmAttachController extends BaseProvider<IZGCmAttachService, ZGCmAttach> {
}