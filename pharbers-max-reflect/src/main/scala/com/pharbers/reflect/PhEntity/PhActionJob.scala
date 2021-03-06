package com.pharbers.reflect.PhEntity

import com.pharbers.channel.detail.channelEntity
import com.pharbers.macros.api.commonEntity
import com.pharbers.macros.common.connecting._
import com.pharbers.reflect.PhEntity.confEntity._

@One2OneConn[PhXmppConf]("xmppConf")
@One2OneConn[PhCalcYmConf]("calcYmConf")
@One2ManyConn[PhPanelConf]("panelConf")
@One2ManyConn[PhCalcConf]("calcConf")
@One2ManyConn[PhResultExportConf]("resultExportConf")
@One2ManyConn[PhUnitTestConf]("unitTestConf")
@One2ManyConn[PhDataConversionConf]("dataConversionConf")
@ToStringMacro
class PhActionJob() extends commonEntity with channelEntity {
    var job_id: String = ""
    var user_id: String = ""
    var company_id: String = ""

    var panel_path: String = "hdfs:///workData/Panel/"
    var max_path: String = "hdfs:///workData/Max/"
    var export_path: String = "hdfs:///workData/Export/"
    var prod_lst: String = ""

    private def ckElem(value: String): String = {
        if (value.isEmpty) throw new Exception("element is none")
        value
    }

    private def ckPath(value: String): String = {
        if (ckElem(value).endsWith("/")) value
        else value + "/"
    }

    def ymArgs(ymConf: PhCalcYmConf): Map[String, String] = {
        Map(
            "job_id" -> ckElem(job_id),
            "user_id" -> ckElem(user_id),
            "company_id" -> ckElem(company_id)
        ) ++ ymConf.conf
    }

    def panelArgs(p_current: Int, p_total: Int)
                 (panelConf: PhPanelConf): Map[String, String] = {
        Map(
            "job_id" -> ckElem(job_id),
            "user_id" -> ckElem(user_id),
            "company_id" -> ckElem(company_id),
            "panel_path" -> ckPath(panel_path),
            "prod_lst" -> ckElem(prod_lst),
            "ym" -> ckElem(panelConf.ym),
            "mkt" -> ckElem(panelConf.mkt),
            "panel_name" -> ckElem(panelConf.panel_name),
            "p_current" -> ckElem(p_current.toString),
            "p_total" -> ckElem(p_total.toString)
        ) ++ panelConf.conf
    }

    def calcArgs(p_current: Int, p_total: Int)
                (calcConf: PhCalcConf): Map[String, String] = {
        Map(
            "job_id" -> ckElem(job_id),
            "user_id" -> ckElem(user_id),
            "company_id" -> ckElem(company_id),
            "panel_path" -> ckPath(panel_path),
            "max_path" -> ckPath(max_path),
            "prod_lst" -> ckElem(prod_lst),
            "ym" -> ckElem(calcConf.ym),
            "mkt" -> ckElem(calcConf.mkt),
            "panel_name" -> ckElem(calcConf.panel_name),
            "max_name" -> ckElem(calcConf.max_name),
            "max_search_name" -> ckElem(calcConf.max_search_name),
            "p_current" -> ckElem(p_current.toString),
            "p_total" -> ckElem(p_total.toString)
        ) ++ calcConf.conf
    }

    def exportArgs(p_current: Int, p_total: Int)
                  (exportConf: PhResultExportConf): Map[String, String] = {
        Map(
            "ym" -> exportConf.ym,
            "mkt" -> exportConf.mkt,
            "job_id" -> ckElem(job_id),
            "user_id" -> ckElem(user_id),
            "company_id" -> ckElem(company_id),
            "p_current" -> ckElem(p_current.toString),
            "p_total" -> ckElem(p_total.toString),
            "max_path" -> ckPath(max_path),
            "max_name" -> ckElem(exportConf.max_name),
            "export_path" -> ckPath(export_path),
            "export_name" -> ckElem(exportConf.export_name)
        ) ++ exportConf.conf
    }

    def conversionArgs(conversionConf: PhDataConversionConf): Map[String, String] = {
        Map(
            "company_id" -> ckElem(company_id)
        ) ++ conversionConf.conf
    }
}

