package com.jamjaws.riv.financialbillingclaimserver.config

import com.jamjaws.riv.financialbillingclaimserver.controller.FinancialBillingClaimController
import org.apache.cxf.Bus
import org.apache.cxf.ext.logging.LoggingFeature
import org.apache.cxf.jaxws.EndpointImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.xml.ws.Endpoint

@Configuration
class WebServiceConfig(
    private val bus: Bus,
    private val financialBillingClaimController: FinancialBillingClaimController
) {

    @Bean
    fun loggingFeature(): LoggingFeature = LoggingFeature().apply {
        setVerbose(true)
        setPrettyLogging(true)
        setLogMultipart(true)
        initialize(bus)
    }

    @Bean
    fun endpoint(): Endpoint = EndpointImpl(bus, financialBillingClaimController).apply {
        publish("financial-billing-claim")
    }
}
