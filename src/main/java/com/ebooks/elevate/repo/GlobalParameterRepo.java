package com.ebooks.elevate.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebooks.elevate.entity.GlobalParameterVO;

public interface GlobalParameterRepo extends JpaRepository<GlobalParameterVO, Long> {

//	@Query("select a from GlobalParameterVO a where  a.userid=?1")
//	Optional<GlobalParameterVO> (String username);

//	@Query("select a from GlobalParameterVO a where a.orgId=?1 and a.userid=?2")
//	GlobalParameterVO findGlobalParam(Long orgId, String userid);

//	@Query(nativeQuery = true, value = "select w.warehouse from warehouse w , warehouseclient wc where wc.warehouseid=w.warehouseid and orgid=?1 and branch=?2 and client =?3")
//	Set<Object[]> findWarehouseNameByOrgIdAndBranchAndClient(Long orgid, String branch, String client);

	Optional<GlobalParameterVO> findById(String id);

	@Query(nativeQuery = true,value="select * from globalparam g where g.userid=?1")
	Optional<GlobalParameterVO> getGlobalParam(Long userId);

	GlobalParameterVO findByUserid(Long userId);

}
