package com.plum.service;

import com.plum.spzx.model.vo.system.ValidateCodeVo;

/**
 * ClassName: ValidateCodeService
 * Package: com.plum.service
 * description
 * Author: Plum
 * Crete : 2024/5/8 21:07
 * Version 1.0
 */
public interface ValidateCodeService {
    ValidateCodeVo generateValidateCode();
}
