package com.mmcoe.pojo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogDemo {

    // Create Logger object
    private static final Logger logger =
            LogManager.getLogger(LogDemo.class);

    public static void main(String[] args) {

        logger.trace("Trace Message");

        logger.debug("Debug Message");

        logger.info("Application Started");

        logger.warn("Low Memory Warning");

        logger.error("Database Connection Failed");

        logger.fatal("System Crash");

        logger.info("Application Ended");
    }
}