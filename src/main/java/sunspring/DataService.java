package sunspring;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * Session Bean implementation class DataService
 */
@Stateless
@Named
public class DataService {

	@PersistenceContext
	private EntityManager em;
	
	public DataService() {
        
    }
	
	@Transactional(value=TxType.REQUIRED)
	public void save(List<SwfWfApproveLine> list){
		for(SwfWfApproveLine l:list){
			if(l.getLineId()==0){
				em.persist(l);
			}else{
				System.out.println(l.getApproveAmt()+"/"+l.getDepartmentLevel());
				if(em.contains(l)){
					em.merge(l);
				}else{
					SwfWfApproveLine line=em.find( SwfWfApproveLine.class,l.getLineId());
					line.setApproveAmt(l.getApproveAmt());
					line.setDepartmentLevel(l.getDepartmentLevel());
					em.merge(line);
				}
			}
			em.flush();
		}
	}
	
	/**
	 * 取得目前的所有排序組態,金額小至大
	 * @return List 
	 * {@link SwfWfApproveLine}
	 */
	public List<SwfWfApproveLine> fkindAll(){
		return em.createQuery("SELECT l FROM SwfWfApproveLine l "
				+ "WHERE l.headerId=:HID ORDER BY l.approveAmt"
				, SwfWfApproveLine.class)
				.setParameter("HID", 1)
				.getResultList();
	}

	/**
	 * 根據金額判別審核層級
	 * @param totalMoney 總金額(NT)
	 * @return 0~6 0:總裁  1:執行長 2:管理中心 3:處 4:廠/部 5:課 6:組 
	 */
	public int findAuditLevel(int totalMoney){
		List<SwfWfApproveLine> list=em.createQuery("SELECT l FROM SwfWfApproveLine l "
				+ "WHERE l.headerId=:HID ORDER BY l.approveAmt"
				, SwfWfApproveLine.class)
				.setParameter("HID", 1)
				.getResultList();
		for(SwfWfApproveLine l :list){
			if(totalMoney<=l.getApproveAmt())
				return l.getDepartmentLevel();
		}
		return 0;
	}
}
