package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGLevelPermMemberService;
import org.smartwork.dal.entity.ZGLevelPermMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"null管理"})
@RestController
@RequestMapping("/zglevelpermmember")
public class ZGLevelPermMemberController extends BaseProvider<IZGLevelPermMemberService, ZGLevelPermMember> {
}