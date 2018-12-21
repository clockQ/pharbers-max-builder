package com.pharbers.reflect.PhEntity

import com.pharbers.macros.api.commonEntity
import com.pharbers.macros.common.connecting.ToStringMacro
import com.pharbers.reflect.PhEntity.confTrait.PhActionTrait

@ToStringMacro
class PhCalcYmConf extends commonEntity with PhActionTrait {
    val jar_path: String = ""
    val clazz: String = ""

    val conf: Map[String, String] = Map()
}