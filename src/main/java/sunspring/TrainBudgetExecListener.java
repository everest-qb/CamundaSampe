package sunspring;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import sunspring.swf.rest.SwfEmployee;
import sunspring.swf.rest.client.SwfInfoClient;

@Named
public class TrainBudgetExecListener implements ExecutionListener {

	@Inject
	private DataService service;
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		String applyer=(String)execution.getVariable("applyer");
		int totalMoney=Integer.parseInt((String)execution.getVariable("tatalMoney"));
		int level =service.findAuditLevel(totalMoney);
		SwfInfoClient client=new SwfInfoClient("http://hrdev.sunspring.com.tw:8080/swf/rest");
		SwfEmployee empl=client.findEmplByNumber(applyer);
		List<SwfEmployee> list=client.findAudit(empl.getEmpId(), new BigDecimal(level));
		String tmp="";
		for(SwfEmployee ee:list){
			if(tmp.length()==0){
				tmp=ee.getEmpNum();
			}else{
				tmp+=","+ee.getEmpNum();
			}			
		}
		List<SwfEmployee> apList=client.findApprover(empl.getEmpId(), new BigDecimal(level-1));
		int testLevel=level-2;
		while(apList.size()==0 && testLevel>=0){//核准人必須要有,沒有往上一級找
			apList=client.findApprover(empl.getEmpId(), new BigDecimal(testLevel));
			testLevel--;
		}
		
		if(apList.size()>0){
			execution.setVariable("APPROVER", apList.get(apList.size()-1).getEmpNum());
			if(apList.size()>1){//非主管移到審核
				for(int i=0;i<apList.size()-1;i++){
					if(tmp.length()==0){
						tmp=apList.get(i).getEmpNum();
					}else{
						tmp+=","+apList.get(i).getEmpNum();
					}
				}
			}
		}
		
		execution.setVariable("AUDIT", tmp);//如果沒有審核人,流程會pass跑核准
		for(Entry<String, Object>  e:execution.getVariables().entrySet()){
			System.out.println("KEY:"+e.getKey()+" VALUE:"+e.getValue());
		}
	}

}
