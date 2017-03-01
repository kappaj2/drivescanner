/*
 *
 *  * Copyright (c) PCMS Group plc 2016. All Rights Reserved.
 *  * This source code is copyright of PCMS Group plc. The information
 *  * contained herein is proprietary and confidential to PCMS Group plc.
 *  * This proprietary and confidential information, either in whole or in
 *  * part, shall not be used for any purpose unless permitted by the terms
 *  * of a valid license agreement.
 *
 */

package za.co.ajk.drivescanner.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
}
