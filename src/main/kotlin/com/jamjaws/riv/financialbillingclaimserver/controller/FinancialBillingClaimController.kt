package com.jamjaws.riv.financialbillingclaimserver.controller

import org.springframework.stereotype.Controller
import riv.financial.billing.claim.processclaimspecification._1.rivtabp21.ProcessClaimSpecificationResponderInterface
import riv.financial.billing.claim.processclaimspecificationresponder._1.ProcessClaimSpecificationResponseType
import riv.financial.billing.claim.processclaimspecificationresponder._1.ProcessClaimSpecificationType
import riv.financial.billing.claim.processclaimspecificationresponder._1.ResultCodeEnum

@Controller
class FinancialBillingClaimController : ProcessClaimSpecificationResponderInterface {

    override fun processClaimSpecification(
        logicalAddress: String?,
        parameters: ProcessClaimSpecificationType?
    ): ProcessClaimSpecificationResponseType {
        return ProcessClaimSpecificationResponseType().apply {
            resultCode = ResultCodeEnum.OK
            comment = "looks good"
        }
    }
}