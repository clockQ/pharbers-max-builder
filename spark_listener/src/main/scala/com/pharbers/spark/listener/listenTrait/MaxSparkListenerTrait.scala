package com.pharbers.spark.listener.listenTrait

import org.apache.spark.scheduler.SparkListener

trait MaxSparkListenerTrait extends SparkListener {
    val app_name: String
}
