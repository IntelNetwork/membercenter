package org.smartwork.provider;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${smartwork.verision}/member-level")
@Api(tags = {"前端会员等级管理"})
@Slf4j
public class ZGMemberLevelApiProvider {
}
