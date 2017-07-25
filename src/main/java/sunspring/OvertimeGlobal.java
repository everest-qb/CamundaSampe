package sunspring;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class OvertimeGlobal {

	public final static int NONE=0;
	public final static int APPROVAL=1;
	public final static int RETURN=2;
	public final static int REJECT=3;
	
	public int getNone() {
		return NONE;
	}
	public int getApproval() {
		return APPROVAL;
	}
	public int getReturn() {
		return RETURN;
	}
	public int getReject() {
		return REJECT;
	}
	
}
