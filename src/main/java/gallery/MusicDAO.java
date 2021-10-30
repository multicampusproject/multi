package gallery;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper   //@Mapper-Scan
@Repository //@cOMPONENT-SCAN
public interface MusicDAO {
	
	public ArrayList<MusicVO> musiclist();
	
	// 0> sql-mapping.xml 주석 해제
	// 1> 메소드이름을 session.xxxx("emp.mapping 파일 id", xxx);
	// 2> 메소드 구현부 삭제
	// 3> EmpService, EmpSeviceImpl 주석해제. dao 호출메소드 수정 
	// 4> 컨트롤러 /empdeptlist 주석 해제 후 실행
	
/*	public EmpVO getEmpOne(int id) {
		EmpVO vo = session.selectOne("emp.empone", id);
		return vo;
	//System.out.println(vo.getEmployee_id() + ":" + vo.getFirst_name() +":" + vo.getHire_date() +":" + vo.getSalary());
	}
	
	public void insertEmp(EmpVO vo) {
		session.insert("emp.insertemp", vo);
	}
	
	public void updateEmp(EmpVO vo) {
		session.update("emp.updateemp", vo);
	}
	
	public void deleteEmp(String name) {
		session.delete("emp.deleteemp", name);
	}
	
	public int countEmp() {
		int cnt = session.selectOne("emp.cnt");
		return cnt;
	}
	
	public List<EmpVO>  empDeptList(int [] dept_list) {
		List<EmpVO> list = session.selectList("emp.empdeptlist" , dept_list);
		return list;
	}*/
}
















