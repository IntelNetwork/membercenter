package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGLevelPermNameService;
import org.smartwork.dal.entity.ZGLevelPermName;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"null管理"})
@RestController
@RequestMapping("/zglevelpermname")
public class ZGLevelPermNameController extends BaseProvider<IZGLevelPermNameService, ZGLevelPermName> {
}