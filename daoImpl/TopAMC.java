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

import com.til.bean.AMCNameDto;
import com.til.bean.AUMChangeDto;
import com.til.bean.SchemeDto;
import com.til.bean.BenchmarkDto;


@Repository
public class TopAMC {

	
	
	
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	
	public  List<AMCNameDto> getAMCData() {
		
		List<AMCNameDto> resultList = new ArrayList<AMCNameDto>();
		
	    
		
		List<AMCNameDto> l1 = new ArrayList<AMCNameDto>();
		//String query = " select * from ST_StartupCompanyMaster";
		String q1 = "SELECT amc.id, amc.fundname, aum.assetundermanagement,aum.[date] from mf_assetmanagementcompanymaster amc "
				+ "inner join mf_assetundermanagement aum on amc.id = aum.assetmanagementcompanyid "
				+ "where aum.[date] = (select max(aum.[date]) from mf_assetundermanagement aum) "
				+ "and aum.[date] > '2010-08-31 00:00:00.000'";
		
		l1=jdbcTemplate.query(q1, new RowMapper<AMCNameDto>() {

			
			public AMCNameDto mapRow(ResultSet rs, int arg1) throws SQLException {
				
				//System.out.println(rs.getString("fundname"));
				AMCNameDto fundDto = new AMCNameDto();
				fundDto.setId(rs.getInt("id"));
				fundDto.setFname(rs.getString("fundname"));
				
				fundDto.setAum(rs.getDouble("assetundermanagement"));
				fundDto.setDate(rs.getTimestamp("date"));
				//ComUtil.STARTUP_COMPANIES_MAP.put(rs.getInt("id"), companyDto);
				
				return fundDto;
			}
		});
		
		
		
	
		
		if(l1==null){
			System.out.println("l1 returns null........");
		}
		
		for(AMCNameDto dto : l1){
			AMCNameDto topamc = new AMCNameDto();
			
			topamc.setAmcName(dto.getFname());
			topamc.setId(dto.getId());
			topamc.setAum(dto.getAum());
			topamc.setDate(dto.getDate());
			
			int id=dto.getId();
			List<AUMChangeDto> l2 = new ArrayList<AUMChangeDto>();
			String q2 = " select top 2 aum.assetundermanagement,aum.assetmanagementcompanyid,aum.[date] "
					+ "from mf_assetundermanagement aum where assetmanagementcompanyid = ? "
					+ "and aum.[date]> '2010-08-31 00:00:00.000' order by aum.date desc";
			//final AUMChangeDto ChangeDto = new AUMChangeDto();
			l2 = jdbcTemplate.query(q2,new Object[]{id}, new RowMapper<AUMChangeDto>(){
				
				public AUMChangeDto mapRow(ResultSet rs, int arg1) throws SQLException {
					AUMChangeDto ChangeDto = new AUMChangeDto();
					ChangeDto.setAssetundermanagement(rs.getDouble("assetundermanagement"));
					ChangeDto.setAssetmanagementcompanyid(rs.getInt("assetmanagementcompanyid"));
					ChangeDto.setDate(rs.getTimestamp("date"));
					//ComUtil.STARTUP_COMPANIES_MAP.put(rs.getInt("id"), companyDto);
					
					return ChangeDto;
				}
			});
			
			
			
			double change=0,changepercent=0;
			
			if(l2.size()==2){
				AUMChangeDto dto1 = l2.get(0);
				AUMChangeDto dto2 = l2.get(1);
				
				if(dto1.getAssetundermanagement() != null || dto2.getAssetundermanagement() != null){
				change = dto1.getAssetundermanagement()-dto2.getAssetundermanagement();
				changepercent = (change/dto2.getAssetundermanagement())*100;
				}
			}
			
			/*for(AUMChangeDto chg : l2){
				double temp = chg.getAssetundermanagement();
				change = temp-change;
				changepercent = (change/temp)*100;
		    }*/
			
			//ChangeDto.setChange(change);
			//ChangeDto.setChangepercent(changepercent);
			topamc.setChange(change);
			topamc.setChangeper(changepercent);
			
			
			List<SchemeDto> l3 = new ArrayList<SchemeDto>();
			String q3 = " select count(nameofscheme) as num_of_scheme from mf_scheme where assetmanagementcompanyid = ? "
					+ "and planname = 'growth' and nameofscheme NOT LIKE '%direct%' "
					+ "and nameofscheme NOT LIKE '%institution%'";
			 
			l3 = jdbcTemplate.query(q3,new Object[]{id}, new RowMapper<SchemeDto>(){
				
				public SchemeDto mapRow(ResultSet rs, int arg1) throws SQLException {
					SchemeDto ScmDto = new SchemeDto();
					ScmDto.setCount(rs.getInt("num_of_scheme"));
					//ComUtil.STARTUP_COMPANIES_MAP.put(rs.getInt("id"), companyDto);
					
					return ScmDto;
					
				}
			});
			
			for(SchemeDto s: l3){
				topamc.setNum_of_scheme(s.getCount());				
			}
			
			List<BenchmarkDto> l4 = new ArrayList<BenchmarkDto>();
			String q4 = "select rlf.r1week as FUND1WEEK,rlf.r1month as FUND1MNTH,rlf.r3month as FUND3MNTH,"
					+ "rlf.r6month as FUND6MNTH,rlf.r1year as FUND1YR,rlf.r3year as FUND3YR,rlf.r5year as FUND5YR,"
					+ "rlf.rytd as FUNDYTD,"
					+ "rlb.r1week as BENCH1WEEK,rlb.r1month as BENCH1MNTH,rlb.r3month as BENCH3MNTH,"
					+ "rlb.r6month as BENCH6MNTH,rlb.r1year as BENCH1YR,rlb.r3year as BENCH3YR,rlb.r5year as BENCH5YR,"
					+ "rlb.rytd as BENCHYTD, "
					+ "scm.assetmanagementcompanyid,scm.id,scm.planname,"
					+ "scm.nameofscheme from MF_scheme scm "
					+ "inner join mf_returnlatestfunds rlf on rlf.schemeid = scm.id "
					+ "inner join mf_returnlatestbenchmark rlb on rlb.indexid = scm.benchmarkid "
					+ "where scm.assetmanagementcompanyid = ? and scm.planname = 'growth' "
					+ "and nameofscheme NOT LIKE '%direct%' and nameofscheme NOT LIKE '%institution%' "
					+ "order by scm.assetmanagementcompanyid";
			
			
			
			l4 = jdbcTemplate.query(q4,new Object[]{id}, new RowMapper<BenchmarkDto>(){
				
				public BenchmarkDto mapRow(ResultSet rs, int arg1) throws SQLException {
					
					BenchmarkDto bench = new BenchmarkDto();
					bench.setAmc_id(rs.getInt("assetmanagementcompanyid"));
					bench.setScheme_id(rs.getInt("id"));
					bench.setName_of_scheme(rs.getString("nameofscheme"));
					bench.setPlan_name(rs.getString("planname"));
					bench.setBr1w(rs.getDouble("bench1week"));
					bench.setBr1m(rs.getDouble("bench1mnth"));
					bench.setBr3m(rs.getDouble("bench3mnth"));
					bench.setBr6m(rs.getDouble("bench6mnth"));
					bench.setBr1y(rs.getDouble("bench1yr"));
					bench.setBr3y(rs.getDouble("bench3yr"));
					bench.setBr5y(rs.getDouble("bench5yr"));
					bench.setBrytd(rs.getDouble("benchytd"));
					
					bench.setFr1w(rs.getDouble("fund1week"));
					bench.setFr1m(rs.getDouble("fund1mnth"));
					bench.setFr3m(rs.getDouble("fund3mnth"));
					bench.setFr6m(rs.getDouble("fund6mnth"));
					bench.setFr1y(rs.getDouble("fund1yr"));
					bench.setFr3y(rs.getDouble("fund3yr"));
					bench.setFr5y(rs.getDouble("fund5yr"));
					bench.setFrytd(rs.getDouble("fundytd"));
					
					if(bench.getBr1w() != null || bench.getFr1w() != null){
					bench.setDif1w(bench.getFr1w() - bench.getBr1w());
					}
					if(bench.getBr1m() != null || bench.getFr1m() != null){
					bench.setDif1m(bench.getFr1m() - bench.getBr1m());
					}
					if(bench.getBr3m() != null || bench.getFr3m() != null){
					bench.setDif3m(bench.getFr3m() - bench.getBr3m());
					}
					if(bench.getBr6m() != null || bench.getFr6m() != null){
					bench.setDif6m(bench.getFr6m() - bench.getBr6m());
					}
					if(bench.getBr1y() != null || bench.getFr1y() != null){
					bench.setDif1y(bench.getFr1y() - bench.getBr1y());
					}
					if(bench.getBr3y() != null || bench.getFr3y() != null){
					bench.setDif3y(bench.getFr3y() - bench.getBr3y());
					}
					if(bench.getBr5y() != null || bench.getFr5y() != null){
					bench.setDif5y(bench.getFr5y() - bench.getBr5y());
					}
					if(bench.getBrytd() != null || bench.getFrytd() != null){
					bench.setDifytd(bench.getFrytd() - bench.getBrytd());
					}
					
					//ComUtil.STARTUP_COMPANIES_MAP.put(rs.getInt("id"), companyDto);
					
					return bench;
				}
			});
			
			int count1w=0,count1m=0,count3m=0,count6m=0,count1y=0,count3y=0,count5y=0,countytd=0;
			for(BenchmarkDto bench : l4){
				
				if(bench.getDif1w()>0){
					count1w++;
				}
				if(bench.getDif1m()>0){
					count1m++;
				}
				if(bench.getDif3m()>0){
					count3m++;
				}
				if(bench.getDif6m()>0){
					count6m++;
				}
				if(bench.getDif1y()>0){
					count1y++;
				}
				if(bench.getDif3y()>0){
					count3y++;
				}
				if(bench.getDif5y()>0){
					count5y++;
				}
				if(bench.getDifytd()>0){
					countytd++;
				}
			}
			topamc.setNum_of_scheme_beating_benchmark1w(count1w);
			topamc.setNum_of_scheme_beating_benchmark1m(count1m);
			topamc.setNum_of_scheme_beating_benchmark3m(count3m);
			topamc.setNum_of_scheme_beating_benchmark6m(count6m);
			topamc.setNum_of_scheme_beating_benchmark1y(count1y);
			topamc.setNum_of_scheme_beating_benchmark3y(count3y);
			topamc.setNum_of_scheme_beating_benchmark5y(count5y);
			topamc.setNum_of_scheme_beating_benchmarkytd(countytd);
			topamc.setPer_scheme1w(((double)(topamc.getNum_of_scheme_beating_benchmark1w())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_scheme1m(((double)(topamc.getNum_of_scheme_beating_benchmark1m())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_scheme3m(((double)(topamc.getNum_of_scheme_beating_benchmark3m())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_scheme6m(((double)(topamc.getNum_of_scheme_beating_benchmark6m())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_scheme1y(((double)(topamc.getNum_of_scheme_beating_benchmark1y())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_scheme3y(((double)(topamc.getNum_of_scheme_beating_benchmark3y())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_scheme5y(((double)(topamc.getNum_of_scheme_beating_benchmark5y())/(double)(topamc.getNum_of_scheme())*100));
			topamc.setPer_schemeytd(((double)(topamc.getNum_of_scheme_beating_benchmarkytd())/(double)(topamc.getNum_of_scheme())*100));
			
		//	System.out.println(topamc.getId()+","+topamc.getNum_of_scheme_beating_benchmark1w()+","+topamc.getPer_scheme1w()+","+topamc.getNum_of_scheme());
			resultList.add(topamc);
		}
		
		
		return resultList;
	}


	
}
