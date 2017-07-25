package sunspring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;
import org.camunda.bpm.engine.cdi.annotation.ProcessVariable;

@Named 
@BusinessProcessScoped 
public class ApplyEmailBean implements Serializable{
	private static final long serialVersionUID = 7446532526517776452L;
	
	  @Inject
	  @ProcessVariable("AUDIT")
	  private Object audits;
	  
	  public List<String> findAudit(){
		  List<String>  returnValue=new ArrayList<String>();
		  if(audits==null){
			  returnValue.add("manager");
		  }else{
			  String[] tmp=((String)audits).split(",");
			  for(String s:tmp){
				  if(s.length()>0)
					  returnValue.add(s);
			  }
		  }
		  
		  return returnValue;
	  }

}
