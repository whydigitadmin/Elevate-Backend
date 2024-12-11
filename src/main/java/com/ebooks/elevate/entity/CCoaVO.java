package com.ebooks.elevate.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ebooks.elevate.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ccoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CCoaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ccoagen")
	@SequenceGenerator(name = "ccoagen", sequenceName = "ccoaseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "ccoaid")
	private Long id;
	
	@Column(name = "type", length = 30)
	private String type; 
	
	@Column(name = "groupname", length = 150)
	private String groupName;
	
	@Column(name = "accountgroupname", length = 150)
	private String accountGroupName;
	
	@Column(name = "natureofaccount", length = 50)
	private String natureOfAccount;
	
	@Column(name = "parentid")
	private String parentId;
	
	@Column(name = "parentcode",length =10)
	private String parentCode;
	
	@Column(name = "accountcode", length = 10)
	private String accountCode;
	
	@Column(name = "createdby", length = 50)
	private String createdBy;
	
	@Column(name = "modifiedby", length = 50)
	private String updatedBy;
	
	@Column(name = "cancelremarks", length = 150)
	private String cancelRemarks;
	
	@Column(name = "interbranchac")
	private boolean interBranchAc;
	
	@Column(name = "controllac")
	private boolean controllAc;
	
	@Column(name = "currency", length = 50)
	private String currency;
	
	@Column(name = "clientname", length = 50)
	private String clientName;
	
	@Column(name = "clientid", length = 50)
	private String clientId;
	
	private boolean cancel=false;
	
	private boolean active;

	@Embedded
	@Builder.Default
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
}