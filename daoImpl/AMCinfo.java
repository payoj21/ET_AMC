package com.til.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.til.bean.SchemeDetailDto;

@Repository
public class AMCinfo {
	
	
public JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public List<SchemeDetailDto> getAMC(int id){
		List <SchemeDetailDto> list = new ArrayList<SchemeDetailDto>();
		
		List <SchemeDetailDto> l1 = new ArrayList<SchemeDetailDto>();
		String q1 = "select acm.fundname, scm.nameofscheme, scm.id as schemeid from mf_assetmanagementcompanymaster acm "
				+ "inner join mf_scheme scm on acm.id = scm.assetmanagementcompanyid where acm.id ="+id+"and scm.planname='Growth' and scm.nameofscheme NOT LIKE '%direct%' and scm.nameofscheme NOT LIKE '%instituion%'" ;
		
		l1=jdbcTemplate.query(q1, new RowMapper<SchemeDetailDto>() {

			
			public SchemeDetailDto mapRow(ResultSet rs, int arg1) throws SQLException {
				
				//System.out.println(rs.getString("fundname"));
				SchemeDetailDto fundDto = new SchemeDetailDto();
				fundDto.setId(rs.getInt("schemeid"));
				fundDto.setFname(rs.getString("fundname"));
				//System.out.println(fundDto.getId());
				fundDto.setName_of_scheme(rs.getString("nameofscheme"));
				//ComUtil.STARTUP_COMPANIES_MAP.put(rs.getInt("id"), companyDto);
				
				return fundDto;
			}
		});
		
		if(l1==null){
			System.out.println("l1 returns null........");
		}
		
		for(SchemeDetailDto s: l1){
			SchemeDetailDto scm1 = new SchemeDetailDto();
			
			
			int schemeid = s.getId();
			
			List<SchemeDetailDto> tempList = new ArrayList<SchemeDetailDto>();
			
			String q2 = "select R1Week, R1Month, R3Month, R6Month, R9Month, R1Year, R3Year, R5Year, RYTD, mean,sharperatio,"
					+ "informationratio,standarddeviation,vrratings,vrriskrating,vrreturnrating  from mf_returnlatestfunds "
					+ "where schemeid = ?";
			tempList=jdbcTemplate.query(q2,new Object[]{schemeid}, new RowMapper<SchemeDetailDto>(){
				public SchemeDetailDto mapRow(ResultSet rs, int arg1) throws SQLException {
					SchemeDetailDto scm = new SchemeDetailDto();
					//System.out.println(rs.getString("fundname"));
					//SchemeDetailDto fundDto = new SchemeDetailDto();
					scm.setR1week(rs.getDouble("R1Week"));
					scm.setR1month(rs.getDouble("R1Month"));
					scm.setR3month(rs.getDouble("R3Month"));
					scm.setR6month(rs.getDouble("R6Month"));
					scm.setR9month(rs.getDouble("R9Month"));
					scm.setR1year(rs.getDouble("R1Year"));
					scm.setR3year(rs.getDouble("R3Year"));
					scm.setR5year(rs.getDouble("R5Year"));
					scm.setRytd(rs.getDouble("RYTD"));
					scm.setMean(rs.getDouble("mean"));
					scm.setSharpe(rs.getDouble("sharperatio"));
					scm.setInfo(rs.getDouble("informationratio"));
					scm.setStd(rs.getDouble("standarddeviation"));
					scm.setVrratings(rs.getInt("vrratings"));
					scm.setVrreturn(rs.getInt("vrreturnrating"));
					scm.setRiskrating(rs.getInt("vrriskrating"));
					scm.setSaferating((6-rs.getInt("vrriskrating"))); // safe rating is opposite of risk rating; if risk rating is 5 then safe rating is 1; if risk rating is 1 then safe rating is 5	
				//	System.out.println(scm.getR1month()+"::");
					//ComUtil.STARTUP_COMPANIES_MAP.put(rs.getInt("id"), companyDto);
					
					return scm;
				}			
				
			});
			
			if(tempList.size()>0){
				scm1 = tempList.get(0);
				
				
			}
			scm1.setFname(s.getFname());
			scm1.setId(s.getId());
			scm1.setName_of_scheme(s.getName_of_scheme());
			
			
			
			SchemeDetailDto scmtemp1 = new SchemeDetailDto();
			String q3 = "select top 1 s.assetsize,e.expenseratio,d.latestnav,d.[52weekhigh],d.[52weeklow],d.monthlyhigh"
					+ ",d.monthlylow from mf_size s,mf_expense e,mf_dailyupdate d where e.schemeid=s.schemeid "
					+ "and d.schemeid=s.schemeid and	s.schemeid = 22 order by s.date desc,e.date desc";
					     
			tempList=jdbcTemplate.query(q3,new Object[]{schemeid,schemeid}, new RowMapper<SchemeDetailDto>(){
				public SchemeDetailDto mapRow(ResultSet rs, int arg1) throws SQLException {
					SchemeDetailDto scm = new SchemeDetailDto();
					scm.setAssetsize(rs.getDouble("assetsize"));
					scm.setNav(rs.getDouble("latestnav"));
					scm.setWeekHigh(rs.getDouble("52weekhigh"));
					scm.setWeekLow(rs.getDouble("52weeklow"));
					scm.setExpense(rs.getDouble("expenseratio"));
					scm.setMonthHigh(rs.getDouble("monthlyhigh"));
					scm.setMonthLow(rs.getDouble("monthlylow"));
					return scm	;				

									
				}
				
			});
			
			
			if(tempList.size()>0){
				scmtemp1=tempList.get(0);
				scm1.setAssetsize(scmtemp1.getAssetsize());
				scm1.setExpense(scmtemp1.getExpense());
				scm1.setNav(scmtemp1.getNav());
				scm1.setWeekHigh(scmtemp1.getWeekHigh());
				scm1.setWeekLow(scmtemp1.getWeekLow());

				scm1.setMonthHigh(scmtemp1.getMonthHigh());
				scm1.setMonthLow(scmtemp1.getMonthLow());

			}
			
			
/*			
			
			String q4 = "select latestnav,[52weekhigh],[52weeklow],monthlyhigh,monthlylow from mf_dailyupdate where latestnavdate = (select max(latestnavdate) from mf_dailyupdate where schemeid = ?) and schemeid = ?";
			//System.out.println(schemeid+"last sch id");
			SchemeDetailDto scmtemp2 = new SchemeDetailDto();
			tempList=jdbcTemplate.query(q4,new Object[]{schemeid,schemeid}, new RowMapper<SchemeDetailDto>(){
				public SchemeDetailDto mapRow(ResultSet rs, int arg1) throws SQLException {
					SchemeDetailDto scm = new SchemeDetailDto();
					scm.setNav(rs.getDouble("latestnav"));
					scm.setWeekHigh(rs.getDouble("52weekhigh"));
					scm.setWeekLow(rs.getDouble("52weeklow"));

					scm.setMonthHigh(rs.getDouble("monthlyhigh"));
					scm.setMonthLow(rs.getDouble("monthlylow"));
					scm.setExpense(rs.getDouble("expenseratio"));
					return scm	;				
				}
				
			});
		
			
			if(tempList.size()>0){
				scmtemp2=tempList.get(0);
				scm1.setNav(scmtemp2.getNav());
				scm1.setWeekHigh(scmtemp2.getWeekHigh());
				scm1.setWeekLow(scmtemp2.getWeekLow());

				scm1.setMonthHigh(scmtemp2.getMonthHigh());
				scm1.setMonthLow(scmtemp2.getMonthLow());
			}
			
			
			SchemeDetailDto scmtemp3 = new SchemeDetailDto();
			 String q5 = "select expenseratio from mf_expense where date = (select max(date) from mf_expense where schemeid = ?) and schemeid = ?";
			 tempList= jdbcTemplate.query(q5,new Object[]{schemeid,schemeid}, new RowMapper<SchemeDetailDto>(){
					public SchemeDetailDto mapRow(ResultSet rs, int arg1) throws SQLException {
						SchemeDetailDto scm = new SchemeDetailDto();
						scm.setExpense(rs.getDouble("expenseratio"));
						return scm	;				
					}
					
				});
			 
			 if(tempList.size()>0){
				 scmtemp3=tempList.get(0);
				 scm1.setExpense(scmtemp3.getExpense());
			}
			 
			*/
			 
			 list.add(scm1);
		}
		
		return list;
	}
	
	
}
