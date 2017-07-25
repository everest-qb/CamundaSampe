package sunspring;

import java.util.Map.Entry;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

@Named
public class ExecListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		String applyer=(String)execution.getVariable("applyer");
		if(applyer.equals("Y405")){
			execution.setVariable("AUDIT", "audit01,audit11");
			execution.setVariable("Director", "director01");
		}else if(applyer.equals("V410")){
			execution.setVariable("AUDIT", "audit02,audit12");
			execution.setVariable("Director", "director02");
		}else{
			execution.setVariable("AUDIT", "audit03,audit13");
			execution.setVariable("Director", "director03");
		}
		execution.setVariable("CEO", "CEO");
		execution.setVariable("ITO", "ITO");
		execution.setVariable("HANDLER", "andy");
		execution.setVariable("HANDLER_MANAGER", "allen");
		System.out.println("NAME:"+execution.getCurrentActivityName()+" EVENT:"+execution.getEventName());
		for(Entry<String, Object>  e:execution.getVariables().entrySet()){
			System.out.println("KEY:"+e.getKey()+" VALUE:"+e.getValue());
		}
	}

}
