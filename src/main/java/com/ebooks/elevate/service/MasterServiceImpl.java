package com.ebooks.elevate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebooks.elevate.dto.Account1DTO;
import com.ebooks.elevate.dto.Account2DTO;
import com.ebooks.elevate.dto.Account3DTO;
import com.ebooks.elevate.dto.AccountDTO;
import com.ebooks.elevate.dto.BranchDTO;
import com.ebooks.elevate.dto.ChargeTypeRequestDTO;
import com.ebooks.elevate.dto.ChequeBookDTO;
import com.ebooks.elevate.dto.ChequeBookDetailsDTO;
import com.ebooks.elevate.dto.CostCenterDTO;
import com.ebooks.elevate.dto.EmployeeDTO;
import com.ebooks.elevate.dto.GroupLedgerDTO;
import com.ebooks.elevate.dto.ListOfValues1DTO;
import com.ebooks.elevate.dto.ListOfValuesDTO;
import com.ebooks.elevate.dto.PartyAddressDTO;
import com.ebooks.elevate.dto.PartyChargesExemptionDTO;
import com.ebooks.elevate.dto.PartyCurrencyMappingDTO;
import com.ebooks.elevate.dto.PartyDetailsOfDirectorsDTO;
import com.ebooks.elevate.dto.PartyMasterDTO;
import com.ebooks.elevate.dto.PartyPartnerTaggingDTO;
import com.ebooks.elevate.dto.PartySalesPersonTaggingDTO;
import com.ebooks.elevate.dto.PartySpecialTDSDTO;
import com.ebooks.elevate.dto.PartyStateDTO;
import com.ebooks.elevate.dto.PartyTdsExemptedDTO;
import com.ebooks.elevate.dto.SacCodeDTO;
import com.ebooks.elevate.dto.SetTaxRateDTO;
import com.ebooks.elevate.dto.SubLedgerAccountDTO;
import com.ebooks.elevate.dto.TaxMasterDTO;
import com.ebooks.elevate.dto.TaxMasterDetailsDTO;
import com.ebooks.elevate.dto.TcsMaster2DTO;
import com.ebooks.elevate.dto.TcsMasterDTO;
import com.ebooks.elevate.dto.TdsMaster2DTO;
import com.ebooks.elevate.dto.TdsMasterDTO;
import com.ebooks.elevate.entity.Account1VO;
import com.ebooks.elevate.entity.Account2VO;
import com.ebooks.elevate.entity.Account3VO;
import com.ebooks.elevate.entity.AccountVO;
import com.ebooks.elevate.entity.BranchVO;
import com.ebooks.elevate.entity.ChargeTypeRequestVO;
import com.ebooks.elevate.entity.ChequeBookDetailsVO;
import com.ebooks.elevate.entity.ChequeBookVO;
import com.ebooks.elevate.entity.CostCenterVO;
import com.ebooks.elevate.entity.DocumentTypeMappingDetailsVO;
import com.ebooks.elevate.entity.EmployeeVO;
import com.ebooks.elevate.entity.GroupLedgerVO;
import com.ebooks.elevate.entity.ListOfValues1VO;
import com.ebooks.elevate.entity.ListOfValuesVO;
import com.ebooks.elevate.entity.PartyAddressVO;
import com.ebooks.elevate.entity.PartyChargesExemptionVO;
import com.ebooks.elevate.entity.PartyCurrencyMappingVO;
import com.ebooks.elevate.entity.PartyDetailsOfDirectorsVO;
import com.ebooks.elevate.entity.PartyMasterVO;
import com.ebooks.elevate.entity.PartyPartnerTaggingVO;
import com.ebooks.elevate.entity.PartySalesPersonTaggingVO;
import com.ebooks.elevate.entity.PartySpecialTDSVO;
import com.ebooks.elevate.entity.PartyStateVO;
import com.ebooks.elevate.entity.PartyTdsExemptedVO;
import com.ebooks.elevate.entity.PartyTypeVO;
import com.ebooks.elevate.entity.PartyVendorEvaluationVO;
import com.ebooks.elevate.entity.SacCodeVO;
import com.ebooks.elevate.entity.SetTaxRateVO;
import com.ebooks.elevate.entity.SubLedgerAccountVO;
import com.ebooks.elevate.entity.TaxMasterDetailsVO;
import com.ebooks.elevate.entity.TaxMasterVO;
import com.ebooks.elevate.entity.TcsMaster2VO;
import com.ebooks.elevate.entity.TcsMasterVO;
import com.ebooks.elevate.entity.TdsMaster2VO;
import com.ebooks.elevate.entity.TdsMasterVO;
import com.ebooks.elevate.exception.ApplicationException;
import com.ebooks.elevate.repo.Account1Repo;
import com.ebooks.elevate.repo.Account2Repo;
import com.ebooks.elevate.repo.Account3Repo;
import com.ebooks.elevate.repo.AccountRepo;
import com.ebooks.elevate.repo.BranchRepo;
import com.ebooks.elevate.repo.ChargeTypeRequestRepo;
import com.ebooks.elevate.repo.ChequeBookDetailsRepo;
import com.ebooks.elevate.repo.ChequeBookRepo;
import com.ebooks.elevate.repo.CostCenterRepo;
import com.ebooks.elevate.repo.DocumentTypeMappingDetailsRepo;
import com.ebooks.elevate.repo.EmployeeRepo;
import com.ebooks.elevate.repo.GroupLedgerRepo;
import com.ebooks.elevate.repo.ListOfValues1Repo;
import com.ebooks.elevate.repo.ListOfValuesRepo;
import com.ebooks.elevate.repo.PartyAddressRepo;
import com.ebooks.elevate.repo.PartyChargesExemptionRepo;
import com.ebooks.elevate.repo.PartyCurrencyMappingRepo;
import com.ebooks.elevate.repo.PartyDetailsOfDirectorsRepo;
import com.ebooks.elevate.repo.PartyMasterRepo;
import com.ebooks.elevate.repo.PartyPartnerTaggingRepo;
import com.ebooks.elevate.repo.PartySalesPersonTaggingRepo;
import com.ebooks.elevate.repo.PartySpecialTDSRepo;
import com.ebooks.elevate.repo.PartyStateRepo;
import com.ebooks.elevate.repo.PartyTdsExemptedRepo;
import com.ebooks.elevate.repo.PartyTypeRepo;
import com.ebooks.elevate.repo.PartyVendorEvaluationRepo;
import com.ebooks.elevate.repo.SacCodeRepo;
import com.ebooks.elevate.repo.SetTaxRateRepo;
import com.ebooks.elevate.repo.SubLedgerAccountRepo;
import com.ebooks.elevate.repo.TaxMasterDetailsRepo;
import com.ebooks.elevate.repo.TaxMasterRepo;
import com.ebooks.elevate.repo.TcsMaster2Repo;
import com.ebooks.elevate.repo.TcsMasterRepo;
import com.ebooks.elevate.repo.TdsMaster2Repo;
import com.ebooks.elevate.repo.TdsMasterRepo;

@Service
public class MasterServiceImpl implements MasterService {
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);

	@Autowired
	BranchRepo branchRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	SetTaxRateRepo setTaxRateRepo;

	@Autowired
	TaxMasterRepo taxMasterRepo;

	@Autowired
	TaxMasterDetailsRepo taxMasterDetailsRepo;

	@Autowired
	TcsMasterRepo tcsMasterRepo;

	@Autowired
	TcsMaster2Repo tcsMaster2Repo;

	@Autowired
	TdsMasterRepo tdsMasterRepo;

	@Autowired
	TdsMaster2Repo tdsMaster2Repo;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	Account1Repo account1Repo;

	@Autowired
	Account2Repo account2Repo;

	@Autowired
	Account3Repo account3Repo;

	@Autowired
	GroupLedgerRepo groupLedgerRepo;

	@Autowired
	SacCodeRepo sacCodeRepo;

	@Autowired
	SubLedgerAccountRepo subLedgerAccountRepo;

	@Autowired
	CostCenterRepo costCenterRepo;

	@Autowired
	ChequeBookRepo chequeBookRepo;

	@Autowired
	ChargeTypeRequestRepo chargeTypeRequestRepo;

	@Autowired
	ListOfValuesRepo listOfValuesRepo;

	@Autowired
	ListOfValues1Repo listOfValues1Repo;

	@Autowired
	ChequeBookDetailsRepo chequeBookDetailsRepo;

	@Autowired
	PartyMasterRepo partyMasterRepo;

	@Autowired
	PartyStateRepo partyStateRepo;

	@Autowired
	PartyAddressRepo partyAddressRepo;

	@Autowired
	PartyDetailsOfDirectorsRepo partyDetailsOfDirectorsRepo;

	@Autowired
	PartySpecialTDSRepo partySpecialTDSRepo;

	@Autowired
	PartyChargesExemptionRepo partyChargesExemptionRepo;

	@Autowired
	PartyCurrencyMappingRepo partyCurrencyMappingRepo;

	@Autowired
	PartySalesPersonTaggingRepo partySalesPersonTaggingRepo;

	@Autowired
	PartyTdsExemptedRepo partyTdsExemptedRepo;

	@Autowired
	PartyPartnerTaggingRepo partyPartnerTaggingRepo;

	@Autowired
	PartyVendorEvaluationRepo partyVendorEvaluationRepo;

	@Autowired
	PartyTypeRepo partyTypeRepo;
	
	@Autowired
	DocumentTypeMappingDetailsRepo documentTypeMappingDetailsRepo;
	// Branch

	@Override
	public List<BranchVO> getAllBranch(Long orgid) {
		return branchRepo.findAll(orgid);
	}

	@Override
	public Optional<BranchVO> getBranchById(Long branchid) {

		return branchRepo.findById(branchid);
	}

	@Override
	@Transactional
	public Map<String, Object> createUpdateBranch(BranchDTO branchDTO) throws Exception {
		BranchVO branchVO;
		String message = null;

		if (ObjectUtils.isEmpty(branchDTO.getId())) {
			// Check if the branch already exists for creation
			if (branchRepo.existsByBranchAndOrgId(branchDTO.getBranch(), branchDTO.getOrgId())) {
				String errorMessage = String.format("This Branch: %s Already Exists in This Organization",
						branchDTO.getBranch());
				throw new ApplicationException(errorMessage);
			}

			if (branchRepo.existsByBranchCodeAndOrgId(branchDTO.getBranchCode(), branchDTO.getOrgId())) {
				String errorMessage = String.format("This BranchCode: %s Already Exists in This Organization",
						branchDTO.getBranchCode());
				throw new ApplicationException(errorMessage);
			}

			// Create new branch
			branchVO = new BranchVO();
			branchVO.setCreatedBy(branchDTO.getCreatedBy());
			branchVO.setUpdatedBy(branchDTO.getCreatedBy());
			message = "Branch Created Successfully";
		} else {
			// Update existing branch
			branchVO = branchRepo.findById(branchDTO.getId())
					.orElseThrow(() -> new ApplicationException("Branch not found with id: " + branchDTO.getId()));

			branchVO.setUpdatedBy(branchDTO.getCreatedBy());

			if (!branchVO.getBranch().equalsIgnoreCase(branchDTO.getBranch())) {
				if (branchRepo.existsByBranchAndOrgId(branchDTO.getBranch(), branchDTO.getOrgId())) {
					String errorMessage = String.format("This Branch: %s Already Exists in This Organization",
							branchDTO.getBranch());
					throw new ApplicationException(errorMessage);
				}
				branchVO.setBranch(branchDTO.getBranch().toUpperCase());
			}

			if (!branchVO.getBranchCode().equalsIgnoreCase(branchDTO.getBranchCode())) {
				if (branchRepo.existsByBranchCodeAndOrgId(branchDTO.getBranchCode(), branchDTO.getOrgId())) {
					String errorMessage = String.format("This BranchCode: %s Already Exists in This Organization",
							branchDTO.getBranchCode());
					throw new ApplicationException(errorMessage);
				}
				branchVO.setBranchCode(branchDTO.getBranchCode().toUpperCase());
			}

			message = "Branch Updated Successfully";
		}

		getBranchVOFromBranchDTO(branchVO, branchDTO);
		branchRepo.save(branchVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("branchVO", branchVO);
		return response;
	}

	private void getBranchVOFromBranchDTO(BranchVO branchVO, BranchDTO branchDTO) {
		branchVO.setBranch(branchDTO.getBranch().toUpperCase());
		branchVO.setBranchCode(branchDTO.getBranchCode().toUpperCase());
		branchVO.setOrgId(branchDTO.getOrgId());
		branchVO.setAddressLine1(branchDTO.getAddressLine1());
		branchVO.setAddressLine2(branchDTO.getAddressLine2());
		branchVO.setPan(branchDTO.getPan());
		branchVO.setGstIn(branchDTO.getGstIn());
		branchVO.setPhone(branchDTO.getPhone());
		branchVO.setState(branchDTO.getState().toUpperCase());
		branchVO.setCity(branchDTO.getCity().toUpperCase());
		branchVO.setPinCode(branchDTO.getPinCode());
		branchVO.setCountry(branchDTO.getCountry().toUpperCase());
		branchVO.setStateNo(branchDTO.getStateNo().toUpperCase());
		branchVO.setStateCode(branchDTO.getStateCode().toUpperCase());
		branchVO.setLccurrency(branchDTO.getLccurrency());
		branchVO.setCancelRemarks(branchDTO.getCancelRemarks());
		branchVO.setActive(branchDTO.isActive());
	}

	@Override
	public void deleteBranch(Long branchid) {
		branchRepo.deleteById(branchid);
	}

	// Employee

	@Override
	public List<EmployeeVO> getAllEmployeeByOrgId(Long orgId) {
		return employeeRepo.findAllEmployeeByOrgId(orgId);
	}

	@Override
	public List<EmployeeVO> getAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<EmployeeVO> getEmployeeById(Long employeeid) {
		return employeeRepo.findById(employeeid);
	}

	@Override
	public Map<String, Object> createEmployee(EmployeeDTO employeeDTO) throws ApplicationException {
		EmployeeVO employeeVO;
		String message = null;

		if (ObjectUtils.isEmpty(employeeDTO.getId())) {
			// Check for existing employee by employee code within the organization
			if (employeeRepo.existsByEmployeeCodeAndOrgId(employeeDTO.getEmployeeCode(), employeeDTO.getOrgId())) {
				String errorMessage = String.format("This EmployeeCode: %s Already Exists in This Organization",
						employeeDTO.getEmployeeCode());
				throw new ApplicationException(errorMessage);
			}
			// Create new employee
			employeeVO = new EmployeeVO();
			employeeVO.setCreatedBy(employeeDTO.getCreatedBy());
			employeeVO.setUpdatedBy(employeeDTO.getCreatedBy());
			message = "Employee Creation Successfully";
		} else {
			// Update existing employee
			employeeVO = employeeRepo.findById(employeeDTO.getId()).orElseThrow(
					() -> new ApplicationException("ID is Not Found Any Information: " + employeeDTO.getId()));

			employeeVO.setUpdatedBy(employeeDTO.getCreatedBy());

			if (!employeeVO.getEmployeeCode().equalsIgnoreCase(employeeDTO.getEmployeeCode())) {
				if (employeeRepo.existsByEmployeeCodeAndOrgId(employeeDTO.getEmployeeCode(), employeeDTO.getOrgId())) {
					String errorMessage = String.format("This EmployeeCode: %s Already Exists in This Organization",
							employeeDTO.getEmployeeCode());
					throw new ApplicationException(errorMessage);
				}
				employeeVO.setEmployeeCode(employeeDTO.getEmployeeCode());
			}
			message = "Employee Update Successfully";
		}

		// Map the remaining fields
		getEmployeeVOFromEmployeeDTO(employeeVO, employeeDTO);

		// Save the entity
		employeeRepo.save(employeeVO);

		// Prepare the response
		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("employeeVO", employeeVO);

		return response;
	}

	private void getEmployeeVOFromEmployeeDTO(EmployeeVO employeeVO, EmployeeDTO employeeDTO) {
		employeeVO.setEmployeeCode(employeeDTO.getEmployeeCode());
		employeeVO.setEmployeeName(employeeDTO.getEmployeeName());
		employeeVO.setGender(employeeDTO.getGender());
		employeeVO.setEmail(employeeDTO.getEmail());)
		employeeVO.setDepartment(employeeDTO.getDepartment());
		employeeVO.setDesignation(employeeDTO.getDesignation());
		employeeVO.setDateOfBirth(employeeDTO.getDateOfBirth());
		employeeVO.setJoiningDate(employeeDTO.getJoiningdate());
		employeeDTO.setEmail(employeeDTO.getEmail());
		employeeVO.setOrgId(employeeDTO.getOrgId());
		employeeVO.setCancel(employeeDTO.isCancel());
		employeeVO.setActive(employeeDTO.isActive());
		employeeVO.setCancelRemark(employeeDTO.getCancelRemark());
	}

	@Override
	public void deleteEmployee(Long employeeid) {
		employeeRepo.deleteById(employeeid);
	}

	// setTaxesRate
	@Override
	public List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId) {
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
			setTaxRateVO = setTaxRateRepo.getAllSetTaxRateByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
			setTaxRateVO = setTaxRateRepo.findAll();
		}
		return setTaxRateVO;
	}

	@Override
	public List<SetTaxRateVO> getAllSetTaxRateById(Long id) {
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			setTaxRateVO = setTaxRateRepo.getAllSetTaxRateById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			setTaxRateVO = setTaxRateRepo.findAll();
		}
		return setTaxRateVO;
	}

	@Override
	public SetTaxRateVO updateCreateSetTaxRate(@Valid SetTaxRateDTO setTaxRateDTO) throws Exception {
		SetTaxRateVO setTaxRateVO = new SetTaxRateVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(setTaxRateDTO.getId())) {
			isUpdate = true;
			setTaxRateVO = setTaxRateRepo.findById(setTaxRateDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid SetTaxRate details"));
			setTaxRateVO.setUpdatedBy(setTaxRateDTO.getCreatedBy());

		} else {
			if (setTaxRateRepo.existsByChapterAndOrgId(setTaxRateDTO.getChapter(), setTaxRateDTO.getOrgId())) {
				throw new ApplicationException("The given chapter already exists");
			}
			if (setTaxRateRepo.existsByHsnCodeAndOrgId(setTaxRateDTO.getHsnCode(), setTaxRateDTO.getOrgId())) {
				throw new ApplicationException("The given Hsn Code already exists.");
			}
			setTaxRateVO.setUpdatedBy(setTaxRateDTO.getCreatedBy());
			setTaxRateVO.setCreatedBy(setTaxRateDTO.getCreatedBy());
		}

		getSetTaxRateVOFromSetTaxRateDTO(setTaxRateDTO, setTaxRateVO);

		if (ObjectUtils.isNotEmpty(setTaxRateDTO.getId())) {
			SetTaxRateVO setTaxRate = setTaxRateRepo.findById(setTaxRateDTO.getId()).orElse(null);
			if (!setTaxRate.getChapter().equalsIgnoreCase(setTaxRateDTO.getChapter())) {
				if (setTaxRateRepo.existsByChapterAndOrgId(setTaxRateDTO.getChapter(), setTaxRateDTO.getOrgId())) {
					throw new ApplicationException("The given chapter already exists.");
				}
			}
			if (!setTaxRate.getHsnCode().equalsIgnoreCase(setTaxRateDTO.getHsnCode())) {
				if (setTaxRateRepo.existsByHsnCodeAndOrgId(setTaxRateDTO.getHsnCode(), setTaxRateDTO.getOrgId())) {
					throw new ApplicationException("The given Hsn Code already exists.");
				}
			}
		}
		return setTaxRateRepo.save(setTaxRateVO);
	}

	private void getSetTaxRateVOFromSetTaxRateDTO(@Valid SetTaxRateDTO setTaxRateDTO, SetTaxRateVO setTaxRateVO)
			throws Exception {
		setTaxRateVO.setOrgId(setTaxRateDTO.getOrgId());
		setTaxRateVO.setChapter(setTaxRateDTO.getChapter());
		setTaxRateVO.setSubChapter(setTaxRateDTO.getSubChapter());
		setTaxRateVO.setHsnCode(setTaxRateDTO.getHsnCode());
		setTaxRateVO.setBranch(setTaxRateDTO.getBranch());
		setTaxRateVO.setNewRate(setTaxRateDTO.getNewRate());
		setTaxRateVO.setExcepmted("e");
		setTaxRateVO.setActive(setTaxRateDTO.isActive());
	}

	@Override
	public List<SetTaxRateVO> getSetTaxRateByActive() {
		return setTaxRateRepo.findSetTaxRateByActive();
	}

	// TaxMaster
	@Override
	public TaxMasterVO updateCreateTaxMaster(@Valid TaxMasterDTO taxMasterDTO) throws ApplicationException {
		TaxMasterVO taxMasterVO = new TaxMasterVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(taxMasterDTO.getId())) {
			isUpdate = true;
			taxMasterVO = taxMasterRepo.findById(taxMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid TaxMaster details"));
			taxMasterVO.setUpdatedBy(taxMasterDTO.getCreatedBy());
		} else {
			taxMasterVO.setUpdatedBy(taxMasterDTO.getCreatedBy());
			taxMasterVO.setCreatedBy(taxMasterDTO.getCreatedBy());
		}

		List<TaxMasterDetailsVO> taxMasterDetailsVOs = new ArrayList<>();
		if (taxMasterDTO.getTaxMasterDetailsDTO() != null) {
			for (TaxMasterDetailsDTO taxMasterDetailsDTO : taxMasterDTO.getTaxMasterDetailsDTO()) {
				TaxMasterDetailsVO taxMasterDetailsVO;
				if (taxMasterDetailsDTO.getId() != null & ObjectUtils.isEmpty(taxMasterDetailsDTO.getId())) {
					taxMasterDetailsVO = taxMasterDetailsRepo.findById(taxMasterDetailsDTO.getId())
							.orElse(new TaxMasterDetailsVO());
				} else {
					taxMasterDetailsVO = new TaxMasterDetailsVO();
				}
				taxMasterDetailsVO.setGst(taxMasterDetailsDTO.getGst());
				taxMasterDetailsVO.setGstType(taxMasterDetailsDTO.getGstType());
				taxMasterDetailsVO.setPercentage(taxMasterDetailsDTO.getPercentage());
				taxMasterDetailsVO.setTaxType(taxMasterDetailsDTO.getTaxType());
				taxMasterDetailsVO.setFromDate(taxMasterDetailsDTO.getFromDate());
				taxMasterDetailsVO.setToDate(taxMasterDetailsDTO.getToDate());
				taxMasterDetailsVO.setRevenueLedger(taxMasterDetailsDTO.getRevenueLedger());
				taxMasterDetailsVO.setCostLedger(taxMasterDetailsDTO.getCostLedger());
				taxMasterDetailsVO.setActive(taxMasterDetailsDTO.isActive());
				taxMasterDetailsVO.setTaxMasterVO(taxMasterVO);
			}
		}

		getTaxMasterVOFromTaxMasterDTO(taxMasterDTO, taxMasterVO);
		taxMasterVO.setTaxMasterDetailsVO(taxMasterDetailsVOs);
		return taxMasterRepo.save(taxMasterVO);
	}

	private void getTaxMasterVOFromTaxMasterDTO(@Valid TaxMasterDTO taxMasterDTO, TaxMasterVO taxMasterVO) {
		taxMasterVO.setOrgId(taxMasterDTO.getOrgId());
		taxMasterVO.setFinYear(taxMasterDTO.getFinYear());
		taxMasterVO.setServiceAccountCode(taxMasterDTO.getServiceAccountCode());
		taxMasterVO.setWarehouse(taxMasterDTO.getWarehouse());
		taxMasterVO.setActive(taxMasterDTO.isActive());
		taxMasterVO.setGst(taxMasterDTO.getGst());
		taxMasterVO.setGstSlab(taxMasterDTO.getGstSlab());
	}

	@Override
	public List<TaxMasterVO> getAllTaxMasterByOrgId(Long orgId) {
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TaxMasterInformation BY OrgId : {}", orgId);
			taxMasterVO = taxMasterRepo.getAllTaxMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TaxMasterInformation For All OrgId.");
			taxMasterVO = taxMasterRepo.findAll();
		}
		return taxMasterVO;
	}

	@Override
	public List<TaxMasterVO> getAllTaxMasterById(Long id) {
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TaxMasterInformation BY Id : {}", id);
			taxMasterVO = taxMasterRepo.getAllTaxMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TaxMasterInformation For All Id.");
			taxMasterVO = taxMasterRepo.findAll();
		}
		return taxMasterVO;
	}

	@Override
	public List<TaxMasterVO> getTaxMasterByActive() {
		return taxMasterRepo.findTaxMasterByActive();

	}

	// TCSMASTER

	@Override
	public List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId) {
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TcsMasterInformation BY OrgId : {}", orgId);
			tcsMasterVO = tcsMasterRepo.getAllTcsMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TcsMasterInformation For All OrgId.");
			tcsMasterVO = tcsMasterRepo.findAll();
		}
		return tcsMasterVO;
	}

	@Override
	public List<TcsMasterVO> getAllTcsMasterById(Long id) {
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TcsMasterInformation BY Id : {}", id);
			tcsMasterVO = tcsMasterRepo.getAllTcsMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TcsMasterInformation For All Id.");
			tcsMasterVO = tcsMasterRepo.findAll();
		}
		return tcsMasterVO;
	}

	@Override
	public TcsMasterVO updateCreateTcsMaster(@Valid TcsMasterDTO tcsMasterDTO) throws ApplicationException {
		TcsMasterVO tcsMasterVO = new TcsMasterVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(tcsMasterDTO.getId())) {
			isUpdate = true;
			tcsMasterVO = tcsMasterRepo.findById(tcsMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Tcs Master details"));
			tcsMasterVO.setUpdatedBy(tcsMasterDTO.getCreatedBy());
		} else {
			if (tcsMasterRepo.existsBySectionNameAndOrgId(tcsMasterDTO.getSectionName(), tcsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given section name already exists.");
			}
			if (tcsMasterRepo.existsBySectionAndOrgId(tcsMasterDTO.getSection(), tcsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given Section already exists.");
			}
			tcsMasterVO.setUpdatedBy(tcsMasterDTO.getCreatedBy());
			tcsMasterVO.setCreatedBy(tcsMasterDTO.getCreatedBy());
		}
		if (isUpdate) {
			TcsMasterVO tcsMaster = tcsMasterRepo.findById(tcsMasterDTO.getId()).orElse(null);
			if (!tcsMaster.getSection().equals(tcsMasterDTO.getSection())) {
				if (tcsMasterRepo.existsBySectionAndOrgId(tcsMasterDTO.getSection(), tcsMasterDTO.getOrgId())) {
					throw new ApplicationException("The given section already exists.");
				}
			}
			if (!tcsMaster.getSectionName().equalsIgnoreCase(tcsMasterDTO.getSectionName())) {
				if (tcsMasterRepo.existsBySectionNameAndOrgId(tcsMasterDTO.getSectionName(), tcsMasterDTO.getOrgId())) {
					throw new ApplicationException("The given Section name already exists.");
				}
			}
		}

		List<TcsMaster2VO> tcsMaster2VOs = new ArrayList<>();
		if (tcsMasterDTO.getTcsMaster2DTO() != null) {
			for (TcsMaster2DTO tcsMaster2DTO : tcsMasterDTO.getTcsMaster2DTO()) {
				if (tcsMaster2DTO.getId() != null & ObjectUtils.isNotEmpty(tcsMaster2DTO.getId())) {
					TcsMaster2VO tcsMaster2VO = tcsMaster2Repo.findById(tcsMaster2DTO.getId()).get();
					tcsMaster2VO.setFromDate(tcsMaster2DTO.getFromDate());
					tcsMaster2VO.setToDate(tcsMaster2DTO.getToDate());
					tcsMaster2VO.setSerialNo(tcsMaster2DTO.getSerialNo());
					tcsMaster2VO.setTcsPercentage(tcsMaster2DTO.getTcsPercentage());
					tcsMaster2VO.setTcsMasterVO(tcsMasterVO);
					tcsMaster2VOs.add(tcsMaster2VO);

				} else {
					TcsMaster2VO tcsMaster2VO = new TcsMaster2VO();
					tcsMaster2VO.setFromDate(tcsMaster2DTO.getFromDate());
					tcsMaster2VO.setToDate(tcsMaster2DTO.getToDate());
					tcsMaster2VO.setSerialNo(tcsMaster2DTO.getSerialNo());
					tcsMaster2VO.setTcsPercentage(tcsMaster2DTO.getTcsPercentage());
					tcsMaster2VO.setTcsMasterVO(tcsMasterVO);
					tcsMaster2VOs.add(tcsMaster2VO);
				}
			}
		}

		getTcsMasterVOFromTcsMasterDTO(tcsMasterDTO, tcsMasterVO);

		tcsMasterVO.setTcsMaster2VO(tcsMaster2VOs);
		return tcsMasterRepo.save(tcsMasterVO);

	}

	private void getTcsMasterVOFromTcsMasterDTO(@Valid TcsMasterDTO tcsMasterDTO, TcsMasterVO tcsMasterVO) {
		tcsMasterVO.setOrgId(tcsMasterDTO.getOrgId());
		tcsMasterVO.setSection(tcsMasterDTO.getSection());
		tcsMasterVO.setSectionName(tcsMasterDTO.getSectionName());
		tcsMasterVO.setActive(tcsMasterDTO.isActive());
	}

	@Override
	public List<TcsMasterVO> getTcsMasterByActive() {
		return tcsMasterRepo.findTcsMasterByActive();

	}

	// TDSMaster

	@Override
	public List<TdsMasterVO> getAllTdsMasterByOrgId(Long orgId) {
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TdsMasterInformation BY OrgId : {}", orgId);
			tdsMasterVO = tdsMasterRepo.getAllTdsMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TdsMasterInformation For All OrgId.");
			tdsMasterVO = tdsMasterRepo.findAll();
		}
		return tdsMasterVO;
	}

	@Override
	public List<TdsMasterVO> getAllTdsMasterById(Long id) {
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TdsMasterInformation BY Id : {}", id);
			tdsMasterVO = tdsMasterRepo.getAllTdsMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TdsMasterInformation For All Id.");
			tdsMasterVO = tdsMasterRepo.findAll();
		}
		return tdsMasterVO;
	}

	@Override
	public TdsMasterVO updateCreateTdsMaster(@Valid TdsMasterDTO tdsMasterDTO) throws ApplicationException {
		TdsMasterVO tdsMasterVO = new TdsMasterVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(tdsMasterDTO.getId())) {
			isUpdate = true;
			tdsMasterVO = tdsMasterRepo.findById(tdsMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Tds Master details"));
			tdsMasterVO.setUpdatedBy(tdsMasterDTO.getCreatedBy());
		} else {
			if (tdsMasterRepo.existsBySectionNameAndOrgId(tdsMasterDTO.getSectionName(), tdsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given section name already exists.");
			}
			if (tdsMasterRepo.existsBySectionAndOrgId(tdsMasterDTO.getSection(), tdsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given Section already exists.");
			}
			tdsMasterVO.setUpdatedBy(tdsMasterDTO.getCreatedBy());
			tdsMasterVO.setCreatedBy(tdsMasterDTO.getCreatedBy());
		}

		if (isUpdate) {
			TdsMasterVO tdsMaster = tdsMasterRepo.findById(tdsMasterDTO.getId()).orElse(null);
			if (!tdsMaster.getSection().equalsIgnoreCase(tdsMasterDTO.getSection())) {
				if (tdsMasterRepo.existsBySectionAndOrgIdAndId(tdsMasterDTO.getSection(), tdsMasterDTO.getOrgId(),
						tdsMasterDTO.getId())) {
					throw new ApplicationException("The given section already exists.");
				}
			}
			if (!tdsMaster.getSectionName().equals(tdsMasterDTO.getSectionName())) {
				if (tdsMasterRepo.existsBySectionNameAndOrgIdAndId(tdsMasterDTO.getSectionName(),
						tdsMasterDTO.getOrgId(), tdsMasterDTO.getId())) {
					throw new ApplicationException("The given Section name already exists.");
				}
			}
		}

		List<TdsMaster2VO> tdsMaster2VOs = new ArrayList<>();
		if (tdsMasterDTO.getTdsMaster2DTO() != null) {
			for (TdsMaster2DTO tdsMaster2DTO : tdsMasterDTO.getTdsMaster2DTO()) {
				if (tdsMaster2DTO.getId() != null & ObjectUtils.isNotEmpty(tdsMaster2DTO.getId())) {
					TdsMaster2VO tdsMaster2VO = tdsMaster2Repo.findById(tdsMaster2DTO.getId()).get();
					tdsMaster2VO.setFromDate(tdsMaster2DTO.getFromDate());
					tdsMaster2VO.setToDate(tdsMaster2DTO.getToDate());
					tdsMaster2VO.setSurPercentage(tdsMaster2DTO.getSurPercentage());
					tdsMaster2VO.setEdcessPercentage(tdsMaster2DTO.getEdcessPercentage());
					tdsMaster2VO.setTcsPercentage(tdsMaster2DTO.getTcsPercentage());
					tdsMaster2VO.setTdsMasterVO(tdsMasterVO);
					tdsMaster2VOs.add(tdsMaster2VO);

				} else {
					TdsMaster2VO tdsMaster2VO = new TdsMaster2VO();
					tdsMaster2VO.setFromDate(tdsMaster2DTO.getFromDate());
					tdsMaster2VO.setToDate(tdsMaster2DTO.getToDate());
					tdsMaster2VO.setSurPercentage(tdsMaster2DTO.getSurPercentage());
					tdsMaster2VO.setEdcessPercentage(tdsMaster2DTO.getEdcessPercentage());
					tdsMaster2VO.setTcsPercentage(tdsMaster2DTO.getTcsPercentage());
					tdsMaster2VO.setTdsMasterVO(tdsMasterVO);
					tdsMaster2VOs.add(tdsMaster2VO);
				}
			}
		}

		getTdsMasterVOFromTdsMasterDTO(tdsMasterDTO, tdsMasterVO);

		tdsMasterVO.setTdsMaster2VO(tdsMaster2VOs);
		return tdsMasterRepo.save(tdsMasterVO);

	}

	private void getTdsMasterVOFromTdsMasterDTO(@Valid TdsMasterDTO tdsMasterDTO, TdsMasterVO tdsMasterVO) {
		tdsMasterVO.setOrgId(tdsMasterDTO.getOrgId());
		tdsMasterVO.setSection(tdsMasterDTO.getSection());
		tdsMasterVO.setSectionName(tdsMasterDTO.getSectionName());
		tdsMasterVO.setActive(tdsMasterDTO.isActive());
	}

	@Override
	public List<TdsMasterVO> getTdsMasterByActive() {
		return tdsMasterRepo.findTdsMasterByActive();

	}

	// AccountVO

	@Override
	public List<AccountVO> getAllAccountByOrgId(Long orgId) {
		List<AccountVO> accountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
			accountVO = accountRepo.getAllAccountByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
			accountVO = accountRepo.findAll();
		}
		return accountVO;
	}

	@Override
	public List<AccountVO> getAllAccountById(Long id) {
		List<AccountVO> accountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			accountVO = accountRepo.getAllAccountById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			accountVO = accountRepo.findAll();
		}
		return accountVO;
	}

	@Override
	public AccountVO updateCreateAccount(@Valid AccountDTO accountDTO) throws ApplicationException {
		AccountVO accountVO = new AccountVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(accountDTO.getId())) {
			isUpdate = true;
			accountVO = accountRepo.findById(accountDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid account details"));
			accountVO.setUpdatedBy(accountDTO.getCreatedBy());
		} else {
			if (accountRepo.existsByAccountNameAndOrgId(accountDTO.getAccountName(), accountDTO.getOrgId())) {
				throw new ApplicationException("The given Account name already exists.");
			}
			if (accountRepo.existsByAccountCodeAndOrgId(accountDTO.getAccountCode(), accountDTO.getOrgId())) {
				throw new ApplicationException("The given Account Code already exists.");
			}
			accountVO.setUpdatedBy(accountDTO.getCreatedBy());
			accountVO.setCreatedBy(accountDTO.getCreatedBy());
		}
		if (isUpdate) {
			AccountVO account = accountRepo.findById(accountDTO.getId()).orElse(null);
			if (!account.getAccountName().equalsIgnoreCase(accountDTO.getAccountName())) {
				if (accountRepo.existsByAccountNameAndOrgId(accountDTO.getAccountName(), accountDTO.getOrgId())) {
					throw new ApplicationException("The given Account name already exists.");
				}
			}
			if (!account.getAccountCode().equals(accountDTO.getAccountCode())) {
				if (accountRepo.existsByAccountCodeAndOrgId(accountDTO.getAccountCode(), accountDTO.getOrgId())) {
					throw new ApplicationException("The given Account Code already exists.");
				}
			}
		}

		List<Account1VO> account1VOs = new ArrayList<>();
		if (accountDTO.getAccount1DTO() != null) {
			for (Account1DTO account1DTO : accountDTO.getAccount1DTO()) {
				if (account1DTO.getId() != null & ObjectUtils.isNotEmpty(account1DTO.getId())) {
					Account1VO account1VO = account1Repo.findById(account1DTO.getId()).get();
					account1VO.setBalanceSheet(account1DTO.getBalanceSheet());
					account1VO.setIncomeStatement(account1DTO.getIncomeStatement());
					account1VO.setCashFlowStatement(account1DTO.getCashFlowStatement());
					account1VO.setAccountVO(accountVO);
					account1VOs.add(account1VO);

				} else {
					Account1VO account1VO = new Account1VO();
					account1VO.setBalanceSheet(account1DTO.getBalanceSheet());
					account1VO.setIncomeStatement(account1DTO.getIncomeStatement());
					account1VO.setCashFlowStatement(account1DTO.getCashFlowStatement());
					account1VO.setAccountVO(accountVO);
					account1VOs.add(account1VO);
				}
			}
		}
		List<Account2VO> account2VOs = new ArrayList<>();
		if (accountDTO.getAccount2DTO() != null) {
			for (Account2DTO account2DTO : accountDTO.getAccount2DTO()) {
				if (account2DTO.getId() != null & ObjectUtils.isNotEmpty(account2DTO.getId())) {
					Account2VO account2VO = account2Repo.findById(account2DTO.getId()).get();
					account2VO.setBankType(account2DTO.getBankType());
					account2VO.setOverDraftLimit(account2DTO.getOverDraftLimit());
					account2VO.setAccountNo(account2DTO.getAccountNo());
					account2VO.setAccountVO(accountVO);
					account2VOs.add(account2VO);

				} else {
					Account2VO account2VO = new Account2VO();
					account2VO.setBankType(account2DTO.getBankType());
					account2VO.setOverDraftLimit(account2DTO.getOverDraftLimit());
					account2VO.setAccountNo(account2DTO.getAccountNo());
					account2VO.setAccountVO(accountVO);
					account2VOs.add(account2VO);

				}
			}
		}
		List<Account3VO> account3VOs = new ArrayList<>();
		if (accountDTO.getAccount3DTO() != null) {
			for (Account3DTO account3DTO : accountDTO.getAccount3DTO()) {
				if (account3DTO.getId() != null & ObjectUtils.isNotEmpty(account3DTO.getId())) {
					Account3VO account3VO = account3Repo.findById(account3DTO.getId()).get();
					account3VO.setCompany(account3DTO.getCompany());
					account3VO.setBranchLocation(account3DTO.getBranchLocation());
					account3VO.setAccountVO(accountVO);
					account3VOs.add(account3VO);

				} else {
					Account3VO account3VO = new Account3VO();
					account3VO.setCompany(account3DTO.getCompany());
					account3VO.setBranchLocation(account3DTO.getBranchLocation());
					account3VO.setAccountVO(accountVO);
					account3VOs.add(account3VO);

				}
			}
		}

		getAccountVOFromAccountDTO(accountDTO, accountVO);

		accountVO.setAccount1VO(account1VOs);
		accountVO.setAccount2VO(account2VOs);
		accountVO.setAccount3VO(account3VOs);
		return accountRepo.save(accountVO);

	}

	private void getAccountVOFromAccountDTO(@Valid AccountDTO accountDTO, AccountVO accountVO) {
		accountVO.setOrgId(accountDTO.getOrgId());
		accountVO.setAccountGroup(accountDTO.getAccountGroup());
		accountVO.setBranchLocation(accountDTO.getBranchLocation());
		accountVO.setAccountType(accountDTO.getAccountType());
		accountVO.setGroupName(accountDTO.getGroupName());
		accountVO.setAccountCode(accountDTO.getAccountCode());
		accountVO.setAccountName(accountDTO.getAccountName());
		accountVO.setCurrency(accountDTO.getCurrency());
		accountVO.setCategory(accountDTO.getCategory());
		accountVO.setBlock(accountDTO.isBlock());
		accountVO.setItcApplicable(accountDTO.isItcApplicable());
		accountVO.setCompanyName(accountDTO.getCompanyName());
		accountVO.setACatCode(accountDTO.getACatCode());
		accountVO.setACategory(accountDTO.getACategory());
		accountVO.setAType(accountDTO.getAType());
		accountVO.setACurrency(accountDTO.getACurrency());
		accountVO.setTransId(accountDTO.getTransId());
		accountVO.setBp(accountDTO.getBp());
		accountVO.setGroupCode(accountDTO.getGroupCode());
		accountVO.setGst(accountDTO.getGst());
	}

	@Override
	public List<AccountVO> getAccountByActive() {
		return accountRepo.findAccountByActive();

	}

	// groupledgerVO

	@Override
	public List<GroupLedgerVO> getAllGroupLedgerById(Long id) {
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			groupLedgerVO = groupLedgerRepo.getAllGroupLedgerById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			groupLedgerVO = groupLedgerRepo.findAll();
		}
		return groupLedgerVO;
	}

	@Override
	public List<GroupLedgerVO> getAllGroupLedgerByOrgId(Long orgId) {
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
			groupLedgerVO = groupLedgerRepo.getAllGroupLedgerByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
			groupLedgerVO = groupLedgerRepo.findAll();
		}
		return groupLedgerVO;
	}

	@Override
	public GroupLedgerVO updateCreateGroupLedger(@Valid GroupLedgerDTO groupLedgerDTO) throws ApplicationException {
		GroupLedgerVO groupLedgerVO = new GroupLedgerVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(groupLedgerDTO.getId())) {
			isUpdate = true;
			groupLedgerVO = groupLedgerRepo.findById(groupLedgerDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GroupLedger details"));
			groupLedgerVO.setUpdatedBy(groupLedgerDTO.getCreatedBy());
		} else {
			if (groupLedgerRepo.existsByAccountGroupNameAndOrgId(groupLedgerDTO.getAccountGroupName(),
					groupLedgerDTO.getOrgId())) {
				throw new ApplicationException("The given Account Group Name already exists.");
			}
			groupLedgerVO.setUpdatedBy(groupLedgerDTO.getCreatedBy());
			groupLedgerVO.setCreatedBy(groupLedgerDTO.getCreatedBy());
		}
		if (isUpdate) {
			GroupLedgerVO groupLedger = groupLedgerRepo.findById(groupLedgerDTO.getId()).orElse(null);
			if (!groupLedger.getGroupName().equals(groupLedgerDTO.getGroupName())) {
				if (groupLedgerRepo.existsByAccountGroupNameAndOrgId(groupLedgerDTO.getAccountGroupName(),
						groupLedgerDTO.getOrgId())) {
					throw new ApplicationException("The given Account Group Name already exists.");
				}
			}
		}
		getGroupLedgerVOFromGroupLedgerDTO(groupLedgerDTO, groupLedgerVO);
		return groupLedgerRepo.save(groupLedgerVO);
	}

	private void getGroupLedgerVOFromGroupLedgerDTO(@Valid GroupLedgerDTO groupLedgerDTO, GroupLedgerVO groupLedgerVO) {
		groupLedgerVO.setGroupName(groupLedgerDTO.getGroupName().toUpperCase());
		groupLedgerVO.setOrgId(groupLedgerDTO.getOrgId());
		groupLedgerVO.setCoaList(groupLedgerDTO.getCoaList().toUpperCase());
		groupLedgerVO.setType(groupLedgerDTO.getType().toUpperCase());
		groupLedgerVO.setCategory(groupLedgerDTO.getCategory().toUpperCase());
		groupLedgerVO.setCurrency(groupLedgerDTO.getCurrency());
		groupLedgerVO.setGstTaxFlag(groupLedgerDTO.getGstTaxFlag());
		groupLedgerVO.setActive(groupLedgerDTO.isActive());
		groupLedgerVO.setInterBranchAc(groupLedgerDTO.isInterBranchAc());
		groupLedgerVO.setControllAc(groupLedgerDTO.isControllAc());
		groupLedgerVO.setAccountGroupName(groupLedgerDTO.getAccountGroupName().toUpperCase());
	}

	@Override
	public List<Map<String, Object>> getGroupName(Long orgId) {
		Set<Object[]> groupDetails = groupLedgerRepo.getGroupDetails(orgId);
		return group(groupDetails);
	}

	private List<Map<String, Object>> group(Set<Object[]> groupDetails) {
		List<Map<String, Object>> groupdetails1 = new ArrayList<>();
		for (Object[] fs : groupDetails) {
			Map<String, Object> part = new HashMap<>();
			part.put("groupName", fs[0] != null ? fs[0].toString() : "");
			groupdetails1.add(part);
		}
		return groupdetails1;
	}

	@Override
	public List<GroupLedgerVO> getGroupLedgerByActive() {
		return groupLedgerRepo.findGroupLedgerByActive();

	}

	// SacCode

	@Override
	public List<SacCodeVO> getAllSacCodeById(Long id) {
		List<SacCodeVO> sacCodeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  sacCode Information BY Id : {}", id);
			sacCodeVO = sacCodeRepo.getAllSacCodeById(id);
		} else {
			LOGGER.info("Successfully Received  SacCode Information For All Id.");
			sacCodeVO = sacCodeRepo.findAll();
		}
		return sacCodeVO;
	}
	
	@Override
	public List<SacCodeVO> getAllActiveSacCodeByOrgId(Long orgId) {
		List<SacCodeVO> sacCodeVO = new ArrayList<>();
			sacCodeVO = sacCodeRepo.getAllActiveSacCodeByOrgId(orgId);
		
		return sacCodeVO;
	}

	@Override
	public List<SacCodeVO> getAllSacCodeByOrgId(Long orgId) {
		List<SacCodeVO> sacCodeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SacCode Information BY OrgId : {}", orgId);
			sacCodeVO = sacCodeRepo.getAllSacCodeByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SacCode Information For All OrgId.");
			sacCodeVO = sacCodeRepo.findAll();
		}
		return sacCodeVO;
	}

	@Override
	public SacCodeVO updateCreateSacCode(@Valid SacCodeDTO sacCodeDTO) throws ApplicationException {
		SacCodeVO sacCodeVO = new SacCodeVO();
		if (ObjectUtils.isNotEmpty(sacCodeDTO.getId())) {
			sacCodeVO = sacCodeRepo.findById(sacCodeDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid SacCode details"));
			if (!sacCodeVO.getServiceAccountCode().equals(sacCodeDTO.getServiceAccountCode())) {
				if (sacCodeRepo.existsByOrgIdAndServiceAccountCodeIgnoreCase(sacCodeDTO.getOrgId(),sacCodeDTO.getServiceAccountCode())) {
					throw new ApplicationException("This Service Account Code Already Exists.");
				}
			}
			if (!sacCodeVO.getSacDescription().equals(sacCodeDTO.getSacDescription())) {
				if (sacCodeRepo.existsByOrgIdAndSacDescriptionIgnoreCase(sacCodeDTO.getOrgId(),sacCodeDTO.getSacDescription())) {
					throw new ApplicationException("This SAC Descipition Already Exists.");
				}
			}
			sacCodeVO.setUpdatedBy(sacCodeDTO.getCreatedBy());
		} else {
			
			if (sacCodeRepo.existsByOrgIdAndServiceAccountCodeIgnoreCase(sacCodeDTO.getOrgId(),sacCodeDTO.getServiceAccountCode())) {
				throw new ApplicationException("This Service Account Code Already Exists.");
			}
			if (sacCodeRepo.existsByOrgIdAndSacDescriptionIgnoreCase(sacCodeDTO.getOrgId(),sacCodeDTO.getSacDescription())) {
				throw new ApplicationException("This SAC Descipition Already Exists.");
			}
			
			sacCodeVO.setUpdatedBy(sacCodeDTO.getCreatedBy());
			sacCodeVO.setCreatedBy(sacCodeDTO.getCreatedBy());
		}
		getSacCodeVOFromSacCodeDTO(sacCodeDTO, sacCodeVO);
		return sacCodeRepo.save(sacCodeVO);
	}

	private void getSacCodeVOFromSacCodeDTO(@Valid SacCodeDTO sacCodeDTO, SacCodeVO sacCodeVO) {
		sacCodeVO.setOrgId(sacCodeDTO.getOrgId());
		sacCodeVO.setServiceAccountCode(sacCodeDTO.getServiceAccountCode().toUpperCase());
		sacCodeVO.setSacDescription(sacCodeDTO.getSacDescription().toUpperCase());
		sacCodeVO.setActive(sacCodeDTO.isActive());
		sacCodeVO.setProduct(sacCodeDTO.getProduct().toUpperCase());
	}

//	@Override
//	public List<SacCodeVO> getSacCodeByActive() {
//		return sacCodeRepo.findsacCodeByActive();
//
//	}

	// SubLedgerAccount

	@Override
	public List<SubLedgerAccountVO> getAllSubLedgerAccountById(Long id) {
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SubLedgerAccount Information BY Id : {}", id);
			subLedgerAccountVO = subLedgerAccountRepo.getAllSubLedgerAccountById(id);
		} else {
			LOGGER.info("Successfully Received  SubLedgerAccount Information For All Id.");
			subLedgerAccountVO = subLedgerAccountRepo.findAll();
		}
		return subLedgerAccountVO;
	}

	@Override
	public List<SubLedgerAccountVO> getAllSubLedgerAccountByOrgId(Long orgId) {
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SubLedgerAccount Information BY OrgId : {}", orgId);
			subLedgerAccountVO = subLedgerAccountRepo.getAllSubLedgerAccountByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SubLedgerAccount Information For All OrgId.");
			subLedgerAccountVO = subLedgerAccountRepo.findAll();
		}
		return subLedgerAccountVO;
	}

	@Override
	public SubLedgerAccountVO updateCreateSubLedgerAccount(@Valid SubLedgerAccountDTO subLedgerAccountDTO)
			throws ApplicationException {
		SubLedgerAccountVO subLedgerAccountVO = new SubLedgerAccountVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(subLedgerAccountDTO.getId())) {
			isUpdate = true;
			subLedgerAccountVO = subLedgerAccountRepo.findById(subLedgerAccountDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid SubLedgerAccount details"));
			subLedgerAccountVO.setUpdatedBy(subLedgerAccountDTO.getCreatedBy());
		} else {
			if (subLedgerAccountRepo.existsByNewCodeAndOrgId(subLedgerAccountDTO.getNewCode(),
					subLedgerAccountDTO.getOrgId())) {
				throw new ApplicationException("The given code already exists.");
			}
			if (subLedgerAccountRepo.existsByOldCodeAndOrgId(subLedgerAccountDTO.getOldCode(),
					subLedgerAccountDTO.getOrgId())) {
				throw new ApplicationException("The given old code exists.");
			}
			if (subLedgerAccountRepo.existsBySubLedgerNameAndOrgId(subLedgerAccountDTO.getSubLedgerName(),
					subLedgerAccountDTO.getOrgId())) {
				throw new ApplicationException("The given sub ledger name exists.");
			}
			subLedgerAccountVO.setUpdatedBy(subLedgerAccountDTO.getCreatedBy());
			subLedgerAccountVO.setCreatedBy(subLedgerAccountDTO.getCreatedBy());
		}

		if (isUpdate) {
			SubLedgerAccountVO sub = subLedgerAccountRepo.findById(subLedgerAccountDTO.getId()).orElse(null);
			if (!sub.getNewCode().equalsIgnoreCase(subLedgerAccountDTO.getNewCode())) {
				if (subLedgerAccountRepo.existsByNewCodeAndOrgId(subLedgerAccountDTO.getNewCode(),
						subLedgerAccountDTO.getOrgId())) {
					throw new ApplicationException("The given new code already exists.");
				}
			}
			if (!sub.getOldCode().equalsIgnoreCase(subLedgerAccountDTO.getOldCode())) {
				if (subLedgerAccountRepo.existsByOldCodeAndOrgId(subLedgerAccountDTO.getOldCode(),
						subLedgerAccountDTO.getOrgId())) {
					throw new ApplicationException("The given old code exists.");
				}
			}
			if (!sub.getSubLedgerName().equalsIgnoreCase(subLedgerAccountDTO.getSubLedgerName())) {
				if (subLedgerAccountRepo.existsBySubLedgerNameAndOrgId(subLedgerAccountDTO.getSubLedgerName(),
						subLedgerAccountDTO.getOrgId())) {
					throw new ApplicationException("The given sub ledger name exists.");
				}
			}
		}
		getSubLedgerAccountVOFromSubLedgerAccountDTO(subLedgerAccountDTO, subLedgerAccountVO);
		return subLedgerAccountRepo.save(subLedgerAccountVO);
	}

	private void getSubLedgerAccountVOFromSubLedgerAccountDTO(@Valid SubLedgerAccountDTO subLedgerAccountDTO,
			SubLedgerAccountVO subLedgerAccountVO) {
		subLedgerAccountVO.setAccountsCategory(subLedgerAccountDTO.getAccountsCategory());
		subLedgerAccountVO.setSubLedgerType(subLedgerAccountDTO.getSubLedgerType());
		subLedgerAccountVO.setSubLedgerName(subLedgerAccountDTO.getSubLedgerName());
		subLedgerAccountVO.setNewCode(subLedgerAccountDTO.getNewCode());
		subLedgerAccountVO.setOldCode(subLedgerAccountDTO.getOldCode());
		subLedgerAccountVO.setControllAccount(subLedgerAccountDTO.getControllAccount());
		subLedgerAccountVO.setCurrency(subLedgerAccountDTO.getCurrency());
		subLedgerAccountVO.setCreditDays(subLedgerAccountDTO.getCreditDays());
		subLedgerAccountVO.setCreditLimit(subLedgerAccountDTO.getCreditLimit());
		subLedgerAccountVO.setVatno(subLedgerAccountDTO.getVatno());
		subLedgerAccountVO.setStateJutisiction(subLedgerAccountDTO.getStateJutisiction());
		subLedgerAccountVO.setInvoiceType(subLedgerAccountDTO.getInvoiceType());
		subLedgerAccountVO.setOrgId(subLedgerAccountDTO.getOrgId());
		subLedgerAccountVO.setActive(subLedgerAccountDTO.isActive());

	}

	@Override
	public List<SubLedgerAccountVO> getSubLedgerAccountByActive() {
		return subLedgerAccountRepo.findSubLedgerAccountByActive();

	}
	// CostCenter

	@Override
	public List<CostCenterVO> getAllCostCenterById(Long id) {
		List<CostCenterVO> costCenterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  CostCenter Information BY Id : {}", id);
			costCenterVO = costCenterRepo.getAllCostCenterById(id);
		} else {
			LOGGER.info("Successfully Received  CostCenter Information For All Id.");
			costCenterVO = costCenterRepo.findAll();
		}
		return costCenterVO;
	}

	@Override
	public List<CostCenterVO> getAllCostCenterByOrgId(Long orgId) {
		List<CostCenterVO> costCenterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  CostCenter Information BY OrgId : {}", orgId);
			costCenterVO = costCenterRepo.getAllCostCenterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  CostCenter Information For All OrgId.");
			costCenterVO = costCenterRepo.findAll();
		}
		return costCenterVO;
	}

	@Override
	public CostCenterVO updateCreateCostCenter(@Valid CostCenterDTO costCenterDTO) throws ApplicationException {
		CostCenterVO costCenterVO = new CostCenterVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(costCenterDTO.getId())) {
			isUpdate = true;
			costCenterVO = costCenterRepo.findById(costCenterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid CostCenter details"));
			costCenterVO.setUpdatedBy(costCenterDTO.getCreatedBy());
		} else {
			if (costCenterRepo.existsByValueCodeAndOrgId(costCenterDTO.getValueCode(), costCenterDTO.getOrgId())) {
				throw new ApplicationException("The given value code already exists.");
			}
			if (costCenterRepo.existsByValueDescriptionAndOrgId(costCenterDTO.getValueDescription(),
					costCenterDTO.getOrgId())) {
				throw new ApplicationException("The given value descripition already exists.");
			}
			costCenterVO.setUpdatedBy(costCenterDTO.getCreatedBy());
			costCenterVO.setCreatedBy(costCenterDTO.getCreatedBy());
		}
		if (isUpdate) {
			CostCenterVO cost = costCenterRepo.findById(costCenterDTO.getId()).orElse(null);
			if (!cost.getValueCode().equalsIgnoreCase(costCenterDTO.getValueCode())) {
				if (costCenterRepo.existsByValueCodeAndOrgId(costCenterDTO.getValueCode(), costCenterDTO.getOrgId())) {
					throw new ApplicationException("The given value code already exists.");
				}
			}
			if (!cost.getValueDescription().equalsIgnoreCase(costCenterDTO.getValueDescription())) {
				if (costCenterRepo.existsByValueDescriptionAndOrgId(costCenterDTO.getValueDescription(),
						costCenterDTO.getOrgId())) {
					throw new ApplicationException("The given value descripition already exists.");
				}
			}
		}
		getCostCenterVOFromCostCenterDTO(costCenterDTO, costCenterVO);
		return costCenterRepo.save(costCenterVO);
	}

	private void getCostCenterVOFromCostCenterDTO(@Valid CostCenterDTO costCenterDTO, CostCenterVO costCenterVO) {
		costCenterVO.setDimensionType(costCenterDTO.getDimensionType());
		costCenterVO.setValueCode(costCenterDTO.getValueCode());
		costCenterVO.setValueDescription(costCenterDTO.getValueDescription());
		costCenterVO.setOrgId(costCenterDTO.getOrgId());
		costCenterVO.setActive(costCenterDTO.isActive());

	}

	@Override
	public List<CostCenterVO> getCostCenterByActive() {
		return costCenterRepo.findCostCenterByActive();

	}

	// ChequeBook
	@Override
	public List<ChequeBookVO> getAllChequeBookById(Long id) {
		List<ChequeBookVO> chequeBookVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ChequeBook Information BY Id : {}", id);
			chequeBookVO = chequeBookRepo.getAllChequeBookById(id);
		} else {
			LOGGER.info("Successfully Received  ChequeBook Information For All Id.");
			chequeBookVO = chequeBookRepo.findAll();
		}
		return chequeBookVO;
	}

	@Override
	public List<ChequeBookVO> getAllChequeBookByOrgId(Long orgId) {
		List<ChequeBookVO> chequeBookVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ChequeBook Information BY OrgId : {}", orgId);
			chequeBookVO = chequeBookRepo.getAllChequeBookByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ChequeBook Information For All OrgId.");
			chequeBookVO = chequeBookRepo.findAll();
		}
		return chequeBookVO;
	}

	@Override
	public ChequeBookVO updateCreateChequeBook(@Valid ChequeBookDTO chequeBookDTO) throws ApplicationException {
		ChequeBookVO chequeBookVO = new ChequeBookVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(chequeBookDTO.getId())) {
			isUpdate = true;
			chequeBookVO = chequeBookRepo.findById(chequeBookDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ChequeBook details"));
			chequeBookVO.setUpdatedBy(chequeBookDTO.getCreatedBy());
		} else {
			chequeBookVO.setUpdatedBy(chequeBookDTO.getCreatedBy());
			chequeBookVO.setCreatedBy(chequeBookDTO.getCreatedBy());
		}

		List<ChequeBookDetailsVO> chequeBookDetailsVOs = new ArrayList<>();
		if (chequeBookDTO.getChequeBookDetailsDTO() != null) {
			for (ChequeBookDetailsDTO chequeBookDetailsDTO : chequeBookDTO.getChequeBookDetailsDTO()) {
				ChequeBookDetailsVO chequeBookDetailsVO;
				if (chequeBookDetailsDTO.getId() != null & ObjectUtils.isEmpty(chequeBookDetailsDTO.getId())) {
					chequeBookDetailsVO = chequeBookDetailsRepo.findById(chequeBookDetailsDTO.getId())
							.orElse(new ChequeBookDetailsVO());
				} else {
					chequeBookDetailsVO = new ChequeBookDetailsVO();
				}
				chequeBookDetailsVO.setChequeNo(chequeBookDetailsDTO.getChequeNo());
				chequeBookDetailsVO.setStatus(chequeBookDetailsDTO.getStatus());
				chequeBookDetailsVO.setCancelled(chequeBookDetailsDTO.getCancelled());
				chequeBookDetailsVO.setChequeBookVO(chequeBookVO);
				chequeBookDetailsVOs.add(chequeBookDetailsVO);
			}

		}
		// Update ChequeBookVO properties
		getChequeBookVOFromChequeBookDTO(chequeBookDTO, chequeBookVO);
		chequeBookVO.setChequeBookDetailsVO(chequeBookDetailsVOs);

		return chequeBookRepo.save(chequeBookVO);
	}

	private void getChequeBookVOFromChequeBookDTO(@Valid ChequeBookDTO chequeBookDTO, ChequeBookVO chequeBookVO) {
		chequeBookVO.setBranch(chequeBookDTO.getBranch());
		chequeBookVO.setChequeId(chequeBookDTO.getChequeId());
		chequeBookVO.setBank(chequeBookDTO.getBank());
		chequeBookVO.setCheckPrefix(chequeBookDTO.getCheckPrefix());
		chequeBookVO.setCheckStartNo(chequeBookDTO.getCheckStartNo());
		chequeBookVO.setNoOfChequeLeaves(chequeBookDTO.getNoOfChequeLeaves());
		chequeBookVO.setOrgId(chequeBookDTO.getOrgId());
		chequeBookVO.setActive(chequeBookDTO.isActive());
	}

	@Override
	public List<ChequeBookVO> getChequeBookByActive() {
		return chequeBookRepo.findChequeBookByActive();

	}

	// ChargeTypeRequest
	@Override
	public List<ChargeTypeRequestVO> getAllChargeTypeRequestById(Long id) {
		List<ChargeTypeRequestVO> chargeTypeRequestVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ChargeTypeRequest Information BY Id : {}", id);
			chargeTypeRequestVO = chargeTypeRequestRepo.getAllChargeTypeRequestById(id);
		} else {
			LOGGER.info("Successfully Received  ChargeTypeRequest Information For All Id.");
			chargeTypeRequestVO = chargeTypeRequestRepo.findAll();
		}
		return chargeTypeRequestVO;
	}

	@Override
	public List<Map<String, Object>> getChargeType(Long orgId) {
		Set<Object[]> getCharge = listOfValuesRepo.getChargeType(orgId);

		return chargeType(getCharge);

	}

	private List<Map<String, Object>> chargeType(Set<Object[]> getCharge) {
		List<Map<String, Object>> chargeList = new ArrayList<>();
		for (Object[] charge : getCharge) {
			Map<String, Object> ch = new HashMap<>();
			ch.put("chargeType", charge[0].toString());
			chargeList.add(ch);
		}
		return chargeList;
	}

	@Override
	public List<ChargeTypeRequestVO> getAllChargeTypeRequestByOrgId(Long orgId) {
		List<ChargeTypeRequestVO> chargeTypeRequestVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ChargeTypeRequest Information BY OrgId : {}", orgId);
			chargeTypeRequestVO = chargeTypeRequestRepo.getAllChargeTypeRequestByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ChargeTypeRequest Information For All OrgId.");
			chargeTypeRequestVO = chargeTypeRequestRepo.findAll();
		}
		return chargeTypeRequestVO;
	}

	@Override
	public ChargeTypeRequestVO updateCreateChargeTypeRequest(@Valid ChargeTypeRequestDTO chargeTypeRequestDTO)
			throws ApplicationException {
		ChargeTypeRequestVO chargeTypeRequestVO = new ChargeTypeRequestVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(chargeTypeRequestDTO.getId())) {
			isUpdate = true;
			chargeTypeRequestVO = chargeTypeRequestRepo.findById(chargeTypeRequestDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ChargeTypeRequest details"));
			chargeTypeRequestVO.setUpdatedBy(chargeTypeRequestDTO.getCreatedBy());
		} else {
			if (chargeTypeRequestRepo.existsByOrgIdAndChargeDescriptionIgnoreCase(chargeTypeRequestDTO.getOrgId(),chargeTypeRequestDTO.getChargeDescription())) {
				throw new ApplicationException("The given charge descripition already exists.");
			}
			if (chargeTypeRequestRepo.existsByOrgIdAndChargeCodeIgnoreCase(chargeTypeRequestDTO.getOrgId(),chargeTypeRequestDTO.getChargeCode())) {
				throw new ApplicationException("The given charge code already exists.");
			}
			chargeTypeRequestVO.setUpdatedBy(chargeTypeRequestDTO.getCreatedBy());
			chargeTypeRequestVO.setCreatedBy(chargeTypeRequestDTO.getCreatedBy());
		}

		if (isUpdate) {
			ChargeTypeRequestVO charge = chargeTypeRequestRepo.findById(chargeTypeRequestDTO.getId()).orElse(null);
			if (!charge.getChargeDescription().equals(chargeTypeRequestDTO.getChargeDescription())) {
				if (chargeTypeRequestRepo.existsByOrgIdAndChargeDescriptionIgnoreCase(chargeTypeRequestDTO.getOrgId(),chargeTypeRequestDTO.getChargeDescription())) {
					throw new ApplicationException("The given charge descripition already exists.");
				}
			}
			if (!charge.getChargeCode().equals(chargeTypeRequestDTO.getChargeCode())) {
				if (chargeTypeRequestRepo.existsByOrgIdAndChargeCodeIgnoreCase(chargeTypeRequestDTO.getOrgId(),chargeTypeRequestDTO.getChargeCode())) {
					throw new ApplicationException("The given charge code already exists.");
				}
			}
		}

		getChargeTypeRequestVOFromChargeTypeRequestDTO(chargeTypeRequestDTO, chargeTypeRequestVO);
		return chargeTypeRequestRepo.save(chargeTypeRequestVO);
	}

	private void getChargeTypeRequestVOFromChargeTypeRequestDTO(@Valid ChargeTypeRequestDTO chargeTypeRequestDTO,
			ChargeTypeRequestVO chargeTypeRequestVO) {
		chargeTypeRequestVO.setChargeType(chargeTypeRequestDTO.getChargeType().toUpperCase());
		chargeTypeRequestVO.setChargeCode(chargeTypeRequestDTO.getChargeCode().toUpperCase());
		chargeTypeRequestVO.setChargeDescription(chargeTypeRequestDTO.getChargeDescription().toUpperCase());
		chargeTypeRequestVO.setLocalChargeDescripition(chargeTypeRequestDTO.getLocalChargeDescripition().toUpperCase());
		chargeTypeRequestVO.setServiceAccountCode(chargeTypeRequestDTO.getServiceAccountCode().toUpperCase());
		chargeTypeRequestVO.setSacDescripition(chargeTypeRequestDTO.getSacDescripition().toUpperCase());
		chargeTypeRequestVO.setSalesAccount(chargeTypeRequestDTO.getSalesAccount().toUpperCase());
		chargeTypeRequestVO.setPurchaseAccount(chargeTypeRequestDTO.getPurchaseAccount().toUpperCase());
		chargeTypeRequestVO.setTaxable(chargeTypeRequestDTO.getTaxable().toUpperCase());
		chargeTypeRequestVO.setTaxType(chargeTypeRequestDTO.getTaxType().toUpperCase());
		chargeTypeRequestVO.setCcFeeApplicable(chargeTypeRequestDTO.getCcFeeApplicable().toUpperCase());
		chargeTypeRequestVO.setTaxablePercentage(chargeTypeRequestDTO.getTaxablePercentage());
		chargeTypeRequestVO.setCcJob(chargeTypeRequestDTO.getCcJob().toUpperCase());
		chargeTypeRequestVO.setGovtSac(chargeTypeRequestDTO.getGovtSac().toUpperCase());
		chargeTypeRequestVO.setExcempted(chargeTypeRequestDTO.getExcempted().toUpperCase());
		chargeTypeRequestVO.setGstTax(chargeTypeRequestDTO.getGstTax());
		chargeTypeRequestVO.setActive(chargeTypeRequestDTO.isActive());
		chargeTypeRequestVO.setOrgId(chargeTypeRequestDTO.getOrgId());

	}

	@Override
	public List<ChargeTypeRequestVO> getChargeTypeRequestByActive() {
		return chargeTypeRequestRepo.findChargeTypeRequestByActive();

	}

	@Override
	public List<Map<String, Object>> getSalesAccountFromGroup(Long orgId) {
		Set<Object[]> register = chargeTypeRequestRepo.findSalesAccountFromGroup(orgId);
		return getRegister(register);
	}

	private List<Map<String, Object>> getRegister(Set<Object[]> getRegister) {
		List<Map<String, Object>> doctypeMappingDetails = new ArrayList<>();
		for (Object[] sup : getRegister) {
			Map<String, Object> doctype = new HashMap<>();
			doctype.put("salesAccount", sup[0] != null ? sup[0].toString() : "");
			doctypeMappingDetails.add(doctype);
		}

		return doctypeMappingDetails;
	}

	
	@Override
	public List<Map<String, Object>> getPaymentAccountFromGroup(Long orgId) {
		Set<Object[]> payment = chargeTypeRequestRepo.findPaymentAccountFromGroup(orgId);
		return getPayment(payment);
	}

	private List<Map<String, Object>> getPayment(Set<Object[]> getPayment) {
		List<Map<String, Object>> doctypeMappingDetails = new ArrayList<>();
		for (Object[] sup : getPayment) {
			Map<String, Object> doctype = new HashMap<>();
			doctype.put("purchaseAccount", sup[0] != null ? sup[0].toString() : "");
			doctypeMappingDetails.add(doctype);
		}

		return doctypeMappingDetails;
	}

	// ListOfValues

	@Override
	public List<ListOfValuesVO> getListOfValuesById(Long id) {
		List<ListOfValuesVO> listOfValuesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ListOfValues BY Id : {}", id);
			listOfValuesVO = listOfValuesRepo.getListOfValuesById(id);
		} else {
			LOGGER.info("Successfully Received  ListOfValues For All Id.");
			listOfValuesVO = listOfValuesRepo.findAll();
		}
		return listOfValuesVO;
	}

	@Override
	public List<ListOfValuesVO> getListOfValuesByOrgId(Long orgid) {
		List<ListOfValuesVO> listOfValuesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgid)) {
			LOGGER.info("Successfully Received  ListOfValues BY OrgId : {}", orgid);
			listOfValuesVO = listOfValuesRepo.getListOfValuesByOrgId(orgid);
		} else {
			LOGGER.info("Successfully Received  ListOfValues For All OrgId.");
			listOfValuesVO = listOfValuesRepo.findAll();
		}
		return listOfValuesVO;
	}

	@Override
	public ListOfValuesVO updateCreateListOfValues(@Valid ListOfValuesDTO listOfValuesDTO) throws ApplicationException {
		ListOfValuesVO listOfValuesVO = new ListOfValuesVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(listOfValuesDTO.getId())) {
			isUpdate = true;
			listOfValuesVO = listOfValuesRepo.findById(listOfValuesDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ListOfValues details"));
			listOfValuesVO.setUpdatedBy(listOfValuesDTO.getCreatedBy());
		}

		else {
			if (listOfValuesRepo.existsByListCodeAndOrgId(listOfValuesDTO.getListCode(), listOfValuesDTO.getOrgId())) {
				throw new ApplicationException("ListCode already Exists");
			}
			if (listOfValuesRepo.existsByListDescriptionAndOrgId(listOfValuesDTO.getListDescription(),
					listOfValuesDTO.getOrgId())) {
				throw new ApplicationException("ListDescription already Exists");
			}
			listOfValuesVO.setUpdatedBy(listOfValuesDTO.getCreatedBy());
			listOfValuesVO.setCreatedBy(listOfValuesDTO.getCreatedBy());
		}

		if (isUpdate) {
			ListOfValuesVO listOfValues = listOfValuesRepo.findById(listOfValuesDTO.getId()).orElse(null);
			if (!listOfValues.getListCode().equals(listOfValuesDTO.getListCode())) {
				if (listOfValuesRepo.existsByListCodeAndOrgId(listOfValuesDTO.getListCode(),
						listOfValuesDTO.getOrgId())) {
					throw new ApplicationException("ListCode already Exists");
				}
				if (!listOfValues.getListDescription().equals(listOfValuesDTO.getListDescription())) {
					if (listOfValuesRepo.existsByListDescriptionAndOrgId(listOfValuesDTO.getListDescription(),
							listOfValuesDTO.getOrgId())) {
						throw new ApplicationException("ListDescription already Exists");
					}

				}
			}
		}
		getListOfValuesVOFromTypesOfValuesDTO(listOfValuesDTO, listOfValuesVO);
		listOfValuesVO = listOfValuesRepo.save(listOfValuesVO);

		List<ListOfValues1VO> listOfValues1VOList = listOfValues1Repo.findBylistOfValuesVO(listOfValuesVO);
		listOfValues1Repo.deleteAll(listOfValues1VOList);

		List<ListOfValues1VO> listOfValues1VOs = new ArrayList<>();
		if (listOfValuesDTO.getListOfValues1DTO() != null) {
			for (ListOfValues1DTO listOfValues1DTO : listOfValuesDTO.getListOfValues1DTO()) {

				ListOfValues1VO listOfValues1VO = new ListOfValues1VO();
				listOfValues1VO.setValueCode(listOfValues1DTO.getValueCode().toUpperCase());
				listOfValues1VO.setSNo(listOfValues1DTO.getSNo());
				listOfValues1VO.setValueDescription(listOfValues1DTO.getValueDescription().toUpperCase());
				listOfValues1VO.setActive(listOfValues1DTO.isActive());
				listOfValues1VO.setListOfValuesVO(listOfValuesVO);
				listOfValues1VOs.add(listOfValues1VO);
			}
		}

		listOfValuesVO.setListOfValues1VO(listOfValues1VOs);
		return listOfValuesRepo.save(listOfValuesVO);

	}
  
	private void getListOfValuesVOFromTypesOfValuesDTO(@Valid ListOfValuesDTO listOfValuesDTO,
			ListOfValuesVO listOfValuesVO) {
		listOfValuesVO.setListCode(listOfValuesDTO.getListCode());
		listOfValuesVO.setOrgId(listOfValuesDTO.getOrgId());
		listOfValuesVO.setListDescription(listOfValuesDTO.getListDescription());
		listOfValuesVO.setActive(listOfValuesDTO.isActive());

	}

	// PartyMaster

	@Override
	public List<PartyMasterVO> getPartyMasterById(Long id) {
		List<PartyMasterVO> partyMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  PartyMaster BY Id : {}", id);
			partyMasterVO = partyMasterRepo.findPartyMasterVOById(id);
		} 
		return partyMasterVO;
	}

	@Override
	public List<PartyMasterVO> getPartyMasterByOrgId(Long orgid) {
		List<PartyMasterVO> partyMasterVO = new ArrayList<>();
		partyMasterVO = partyMasterRepo.findByOrgId(orgid);
		return partyMasterVO;
	}

	@Override
	public PartyMasterVO updateCreatePartyMaster(@Valid PartyMasterDTO partyMasterDTO) throws ApplicationException {
		PartyMasterVO partyMasterVO = new PartyMasterVO();
		String screenCode ="PM";
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			isUpdate = true;
			partyMasterVO = partyMasterRepo.findById(partyMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid PartyMaster Details"));
			partyMasterVO.setUpdatedBy(partyMasterDTO.getCreatedBy());
		} else {
			// PARTCODE DOCID API
			String partyTypeDocId = partyTypeRepo.getPartyTypeDocId(partyMasterDTO.getOrgId(), partyMasterDTO.getPartyType());
			partyMasterVO.setPartyCode(partyTypeDocId);

			// UPDATE PARTCODE DOCID LASTNO +1
			PartyTypeVO partyTypeVO = partyTypeRepo.findByOrgIdAndPartyType(partyMasterDTO.getOrgId(),
					partyMasterDTO.getPartyType());
			partyTypeVO.setLastNo(partyTypeVO.getLastNo() + 1);
			partyTypeRepo.save(partyTypeVO);

			partyMasterVO.setCreatedBy(partyMasterDTO.getCreatedBy());
			partyMasterVO.setUpdatedBy(partyMasterDTO.getCreatedBy());
		}
		partyMasterVO = partyMasterRepo.save(partyMasterVO);

		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyStateVO> partyStateVOList = partyStateRepo.findByPartyMasterVO(partyMasterVO);
			partyStateRepo.deleteAll(partyStateVOList);
		}
		List<PartyStateVO> partyStateVOs = new ArrayList<>();
			for (PartyStateDTO partyStateDTO : partyMasterDTO.getPartyStateDTO()) {
				PartyStateVO partyStateVO = new PartyStateVO();
				partyStateVO.setState(partyStateDTO.getState());
				partyStateVO.setGstIn(partyStateDTO.getGstIn());
				partyStateVO.setStateNo(partyStateDTO.getStateNo());
				partyStateVO.setContactPerson(partyStateDTO.getContactPerson());
				partyStateVO.setContactPhoneNo(partyStateDTO.getContactPhoneNo());
				partyStateVO.setEmail(partyStateDTO.getEmail());
				partyStateVO.setStateCode(partyStateDTO.getStateCode());
				partyStateVO.setPartyMasterVO(partyMasterVO);
				partyStateVOs.add(partyStateVO);
			}
		
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyAddressVO> partyAddressVOList = partyAddressRepo.findByPartyMasterVO(partyMasterVO);
			partyAddressRepo.deleteAll(partyAddressVOList);
		}
		List<PartyAddressVO> partyAddressVOs = new ArrayList<>();
			for (PartyAddressDTO partyAddressDTO : partyMasterDTO.getPartyAddressDTO()) {
				PartyAddressVO partyAddressVO = new PartyAddressVO();
				partyAddressVO.setState(partyAddressDTO.getState());
				partyAddressVO.setStateGstIn(partyAddressDTO.getStateGstIn());
				partyAddressVO.setBusinessPlace(partyAddressDTO.getBusinessPlace());
				partyAddressVO.setCity(partyAddressDTO.getCity()); // Changed from cityName to city
				partyAddressVO.setAddressType(partyAddressDTO.getAddressType());
				partyAddressVO.setAddressLine1(partyAddressDTO.getAddressLine1());
				partyAddressVO.setAddressLine2(partyAddressDTO.getAddressLine2());
				partyAddressVO.setAddressLine3(partyAddressDTO.getAddressLine3());
				partyAddressVO.setPincode(partyAddressDTO.getPincode());
				partyAddressVO.setContact(partyAddressDTO.getContact()); // Changed from contactPerson to contact

				partyAddressVO.setPartyMasterVO(partyMasterVO);
				partyAddressVOs.add(partyAddressVO);
			}
		
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyDetailsOfDirectorsVO> partyDetailsOfDirectorsVOList = partyDetailsOfDirectorsRepo
					.findByPartyMasterVO(partyMasterVO);
			partyDetailsOfDirectorsRepo.deleteAll(partyDetailsOfDirectorsVOList);
		}
		List<PartyDetailsOfDirectorsVO> partyDetailsOfDirectorsVOs = new ArrayList<>();
			for (PartyDetailsOfDirectorsDTO partyDetailsOfDirectorsDTO : partyMasterDTO
					.getPartyDetailsOfDirectorsDTO()) {
				PartyDetailsOfDirectorsVO partyDetailsOfDirectorsVO = new PartyDetailsOfDirectorsVO();
				partyDetailsOfDirectorsVO.setName(partyDetailsOfDirectorsDTO.getName());
				partyDetailsOfDirectorsVO.setDesignation(partyDetailsOfDirectorsDTO.getDesignation());
				partyDetailsOfDirectorsVO.setPhone(partyDetailsOfDirectorsDTO.getPhone());
				partyDetailsOfDirectorsVO.setEmail(partyDetailsOfDirectorsDTO.getEmail());
				partyDetailsOfDirectorsVO.setPartyMasterVO(partyMasterVO);
				partyDetailsOfDirectorsVOs.add(partyDetailsOfDirectorsVO);

			}
		
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartySpecialTDSVO> partySpecialTDSVOList = partySpecialTDSRepo.findByPartyMasterVO(partyMasterVO);
			partySpecialTDSRepo.deleteAll(partySpecialTDSVOList);
		}
		List<PartySpecialTDSVO> partySpecialTDSVOs = new ArrayList<>();
			for (PartySpecialTDSDTO partySpecialTDSDTO : partyMasterDTO.getPartySpecialTDSDTO()) {
				PartySpecialTDSVO partySpecialTDSVO = new PartySpecialTDSVO();
				partySpecialTDSVO.setTdsWithSec(partySpecialTDSDTO.getTdsWithSec());
				partySpecialTDSVO.setRateFrom(partySpecialTDSDTO.getRateFrom());
				partySpecialTDSVO.setRateTo(partySpecialTDSDTO.getRateTo());
				partySpecialTDSVO.setTdsWithPer(partySpecialTDSDTO.getTdsWithPer());
				partySpecialTDSVO.setSurchargePer(partySpecialTDSDTO.getSurchargePer());
				partySpecialTDSVO.setEdPercentage(partySpecialTDSDTO.getEdPercentage());
				partySpecialTDSVO.setTdsCertifiNo(partySpecialTDSDTO.getTdsCertifiNo());
				partySpecialTDSVO.setPartyMasterVO(partyMasterVO);
				partySpecialTDSVOs.add(partySpecialTDSVO);

			}
		
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyChargesExemptionVO> partyChargesExemptionVOList = partyChargesExemptionRepo
					.findByPartyMasterVO(partyMasterVO);
			partyChargesExemptionRepo.deleteAll(partyChargesExemptionVOList);
		}
		List<PartyChargesExemptionVO> partyChargesExemptionVOs = new ArrayList<>();
			for (PartyChargesExemptionDTO partyChargesExemptionDTO : partyMasterDTO.getPartyChargesExemptionDTO()) {
				PartyChargesExemptionVO partyChargesExemptionVO = new PartyChargesExemptionVO();
				partyChargesExemptionVO.setTdsWithSec(partyChargesExemptionDTO.getTdsWithSec());
				partyChargesExemptionVO.setCharges(partyChargesExemptionDTO.getCharges());
				partyChargesExemptionVO.setPartyMasterVO(partyMasterVO);
				partyChargesExemptionVOs.add(partyChargesExemptionVO);

			}
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyCurrencyMappingVO> partyCurrencyMappingVOList = partyCurrencyMappingRepo
					.findByPartyMasterVO(partyMasterVO);
			partyCurrencyMappingRepo.deleteAll(partyCurrencyMappingVOList);
		}
		List<PartyCurrencyMappingVO> partyCurrencyMappingVOs = new ArrayList<>();
			for (PartyCurrencyMappingDTO partyCurrencyMappingDTO : partyMasterDTO.getPartyCurrencyMappingDTO()) {
				PartyCurrencyMappingVO partyCurrencyMappingVO = new PartyCurrencyMappingVO();
				partyCurrencyMappingVO.setTransCurrency(partyCurrencyMappingDTO.getTransCurrency());
				partyCurrencyMappingVO.setPartyMasterVO(partyMasterVO);
				partyCurrencyMappingVOs.add(partyCurrencyMappingVO);

			}
		
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartySalesPersonTaggingVO> partySalesPersonTaggingVOList = partySalesPersonTaggingRepo
					.findByPartyMasterVO(partyMasterVO);
			partySalesPersonTaggingRepo.deleteAll(partySalesPersonTaggingVOList);
		}
		List<PartySalesPersonTaggingVO> partySalesPersonTaggingVOs = new ArrayList<>();
			for (PartySalesPersonTaggingDTO partySalesPersonTaggingDTO : partyMasterDTO
					.getPartySalesPersonTaggingDTO()) {
				PartySalesPersonTaggingVO partySalesPersonTaggingVO = new PartySalesPersonTaggingVO();
				partySalesPersonTaggingVO.setSalesPerson(partySalesPersonTaggingDTO.getSalesPerson());
				partySalesPersonTaggingVO.setEmpCode(partySalesPersonTaggingDTO.getEmpCode());
				partySalesPersonTaggingVO.setSalesBranch(partySalesPersonTaggingDTO.getSalesBranch());
				partySalesPersonTaggingVO.setEffectiveFrom(partySalesPersonTaggingDTO.getEffectiveFrom());
				partySalesPersonTaggingVO.setEffectiveTill(partySalesPersonTaggingDTO.getEffectiveTill());
				partySalesPersonTaggingVO.setPartyMasterVO(partyMasterVO);
				partySalesPersonTaggingVOs.add(partySalesPersonTaggingVO);

			}
		

		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyTdsExemptedVO> partyTdsExemptedVOList = partyTdsExemptedRepo.findByPartyMasterVO(partyMasterVO);
			partyTdsExemptedRepo.deleteAll(partyTdsExemptedVOList);
		}
		List<PartyTdsExemptedVO> partyTdsExemptedVOs = new ArrayList<>();
			for (PartyTdsExemptedDTO partyTdsExemptedDTO : partyMasterDTO.getPartyTdsExemptedDTO()) {
				PartyTdsExemptedVO partyTdsExemptedVO = new PartyTdsExemptedVO();
				partyTdsExemptedVO.setTdsExempCerti(partyTdsExemptedDTO.getTdsExempCerti());
				partyTdsExemptedVO.setValue(partyTdsExemptedDTO.getValue());
				partyTdsExemptedVO.setFinYear(partyTdsExemptedDTO.getFinYear());
				partyTdsExemptedVO.setPartyMasterVO(partyMasterVO);
				partyTdsExemptedVOs.add(partyTdsExemptedVO);

			}
		
		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			List<PartyPartnerTaggingVO> partyPartnerTaggingVOList = partyPartnerTaggingRepo
					.findByPartyMasterVO(partyMasterVO);
			partyPartnerTaggingRepo.deleteAll(partyPartnerTaggingVOList);
		}
		List<PartyPartnerTaggingVO> partyPartnerTaggingVOs = new ArrayList<>();
			for (PartyPartnerTaggingDTO partyPartnerTaggingDTO : partyMasterDTO.getPartyPartnerTaggingDTO()) {
				PartyPartnerTaggingVO partyPartnerTaggingVO = new PartyPartnerTaggingVO();
				partyPartnerTaggingVO.setPartnerName(partyPartnerTaggingDTO.getPartnerName());
				partyPartnerTaggingVO.setPartyMasterVO(partyMasterVO);
				partyPartnerTaggingVOs.add(partyPartnerTaggingVO);

			}
		

		if (ObjectUtils.isNotEmpty(partyMasterDTO.getId())) {
			PartyVendorEvaluationVO existingPartyVendorEvaluationVO = partyVendorEvaluationRepo
					.findByPartyMasterVO(partyMasterVO);
			if (existingPartyVendorEvaluationVO != null) {
				partyVendorEvaluationRepo.delete(existingPartyVendorEvaluationVO);
			}
		}
		PartyVendorEvaluationVO partyVendorEvaluationVO = null;
			partyVendorEvaluationVO = new PartyVendorEvaluationVO();
			BeanUtils.copyProperties(partyMasterDTO.getPartyVendorEvaluationDTO(), partyVendorEvaluationVO);
			partyVendorEvaluationVO.setPartyMasterVO(partyMasterVO);
		

		getPartyMasterVOFromPartyMasterDTO(partyMasterDTO, partyMasterVO);
		partyMasterVO.setPartyStateVO(partyStateVOs);
		partyMasterVO.setPartyAddressVO(partyAddressVOs);
		partyMasterVO.setPartyDetailsOfDirectorsVO(partyDetailsOfDirectorsVOs);
		partyMasterVO.setPartySpecialTDSVO(partySpecialTDSVOs);
		partyMasterVO.setPartyChargesExemptionVO(partyChargesExemptionVOs);
		partyMasterVO.setPartyCurrencyMappingVO(partyCurrencyMappingVOs);
		partyMasterVO.setPartySalesPersonTaggingVO(partySalesPersonTaggingVOs);
		partyMasterVO.setPartyTdsExemptedVO(partyTdsExemptedVOs);
		partyMasterVO.setPartyPartnerTaggingVO(partyPartnerTaggingVOs);
		if (partyVendorEvaluationVO != null) {
			partyMasterVO.setPartyVendorEvaluationVO(partyVendorEvaluationVO);
		}
		return partyMasterRepo.save(partyMasterVO);
	}

	private void getPartyMasterVOFromPartyMasterDTO(@Valid PartyMasterDTO partyMasterDTO, PartyMasterVO partyMasterVO) {
		partyMasterVO.setPartyType(partyMasterDTO.getPartyType());
		partyMasterVO.setCustomerType(partyMasterDTO.getCustomerType());
		partyMasterVO.setPartyName(partyMasterDTO.getPartyName());
		partyMasterVO.setGstPartyName(partyMasterDTO.getGstPartyName());
		partyMasterVO.setCompany(partyMasterDTO.getCompany());
		partyMasterVO.setAgentName(partyMasterDTO.getAgentName());
		partyMasterVO.setAccountType(partyMasterDTO.getAccountType());
		partyMasterVO.setBussinessType(partyMasterDTO.getBussinessType());
		partyMasterVO.setCarrierCode(partyMasterDTO.getCarrierCode());
		partyMasterVO.setSupplierType(partyMasterDTO.getSupplierType());  
		partyMasterVO.setSalesPerson(partyMasterDTO.getSalesPerson());
		partyMasterVO.setCustomerCoord(partyMasterDTO.getCustomerCoord());
		partyMasterVO.setAccountName(partyMasterDTO.getAccountName());
		partyMasterVO.setGstRegistered(partyMasterDTO.getGstRegistered());
		partyMasterVO.setGstIn(partyMasterDTO.getGstIn());
		partyMasterVO.setCreditLimit(partyMasterDTO.getCreditLimit());
		partyMasterVO.setCreditDays(partyMasterDTO.getCreditDays());
		partyMasterVO.setPanNo(partyMasterDTO.getPanNo());
		partyMasterVO.setControllingOff(partyMasterDTO.getControllingOff());
		partyMasterVO.setCurrency(partyMasterDTO.getCurrency());
		partyMasterVO.setPanName(partyMasterDTO.getPanName());
		partyMasterVO.setAirwayBillNo(partyMasterDTO.getAirwayBillNo());
		partyMasterVO.setAirLineCode(partyMasterDTO.getAirLineCode());
		partyMasterVO.setTanNo(partyMasterDTO.getTanNo());
		partyMasterVO.setBussinessCate(partyMasterDTO.getBussinessCate());
		partyMasterVO.setCountry(partyMasterDTO.getCountry());
		partyMasterVO.setCaf(partyMasterDTO.getCaf());
		partyMasterVO.setRemarks(partyMasterDTO.getRemarks());
		partyMasterVO.setCompoundScheme(partyMasterDTO.getCompoundScheme());
		partyMasterVO.setPsuGovOrg(partyMasterDTO.getPsuGovOrg());
		partyMasterVO.setNameOfBank(partyMasterDTO.getNameOfBank());
		partyMasterVO.setBranch(partyMasterDTO.getBranch());
		partyMasterVO.setAddressBank(partyMasterDTO.getAddressBank());
		partyMasterVO.setAccountNo(partyMasterDTO.getAccountNo());
		partyMasterVO.setAccType(partyMasterDTO.getAccType());
		partyMasterVO.setIfscCode(partyMasterDTO.getIfscCode());
		partyMasterVO.setSwift(partyMasterDTO.getSwift());
		partyMasterVO.setActive(partyMasterDTO.isActive());
		partyMasterVO.setOrgId(partyMasterDTO.getOrgId());
		partyMasterVO.setFinYear(partyMasterDTO.getFinYear());
		partyMasterVO.setBranchCode(partyMasterDTO.getBranchCode());

	}

	@Override
	public String getPartyMasterDocId(Long orgId, String finYear, String branch, String branchCode) {
		String ScreenCode = "PM";
		String result = partyMasterRepo.getPartyMasterDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

}