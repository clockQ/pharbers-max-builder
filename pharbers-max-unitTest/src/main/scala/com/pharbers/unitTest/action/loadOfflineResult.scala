package com.pharbers.unitTest.action

import akka.actor.ActorSystem
import com.pharbers.pactions.actionbase._
import com.pharbers.pactions.generalactions.readCsvAction

object loadOfflineResult {
    def apply(args: pActionArgs)(implicit as: ActorSystem): pActionTrait = new loadOfflineResult(args)
}

class loadOfflineResult(override val defaultArgs: pActionArgs) extends pActionTrait {
    override val name: String = "loadOfflineResult"

    override def perform(prMap: pActionArgs): pActionArgs = {
        val action = defaultArgs.asInstanceOf[MapArgs].get("checkAction").asInstanceOf[PhActionArgs].get
        val offline_result_file = action.unitTestConf.get.head.conf("offline_result_file")

        readCsvAction(offline_result_file, applicationName = action.job_id).perform(NULLArgs)
    }
}
