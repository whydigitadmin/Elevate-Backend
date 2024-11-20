package com.ebooks.elevate.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebooks.elevate.common.CommonConstant;
import com.ebooks.elevate.common.UserConstants;
import com.ebooks.elevate.dto.EltCompanyDTO;
import com.ebooks.elevate.dto.ResponseDTO;
import com.ebooks.elevate.entity.EltCompanyVO;
import com.ebooks.elevate.service.CompanyService;

@CrossOrigin
@RestController
@RequestMapping("/api/companycontroller")

 class CompanyController extends BaseController{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyService companyService;
	
	
	@PutMapping("/updateCreateCompany")
	public ResponseEntity<ResponseDTO> updateCreateCompany(@Valid @RequestBody EltCompanyDTO eltCompanyDTO) {
		String methodName = "updateCreateCompany()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
	        Map<String, Object> eltCompanyVO = companyService.updateCreateCompany(eltCompanyDTO);
	        responseObjectsMap.put(CommonConstant.STRING_MESSAGE, eltCompanyVO.get("message"));
	        responseObjectsMap.put("eltCompanyVO", eltCompanyVO.get("eltCompanyVO")); // Corrected key
	        responseDTO = createServiceResponse(responseObjectsMap);
	    } catch (Exception e) {
	        errorMsg = e.getMessage();
	        LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
	        responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
	    }
	    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	    return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getEltCompanyById")
	public ResponseEntity<ResponseDTO> getEltCompanyById(@RequestParam(required = false) Long id) {
		String methodName = "getAllCostInvoiceById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Optional<EltCompanyVO> eltCompanyVO = null;
		try {
			eltCompanyVO = companyService.getEltCompanyById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Elt CompanyVO information get successfully By id");
			responseObjectsMap.put("eltCompanyVO", eltCompanyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Elt CompanyVO information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
}