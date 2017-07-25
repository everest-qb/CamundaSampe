package sunspring;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the swf_wf_approve_lines database table.
 * 
 */
/**
 * 儲存組態的TABLE
 * @author QB
 * 
 */
@Entity
@Table(name="SWF_WF_APPROVE_LINES")
@NamedQuery(name="SwfWfApproveLine.findAll", query="SELECT s FROM SwfWfApproveLine s")
public class SwfWfApproveLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LINE_ID")
	private int lineId;

	@Column(name="APPROVE_AMT")
	private int approveAmt;

	@Column(name="DEPARTMENT_LEVEL")
	private int departmentLevel;

	@Column(name="HEADER_ID")
	private int headerId;

	@Column(name="LEDGER_ID")
	private int ledgerId;

	public SwfWfApproveLine() {
	}

	public int getLineId() {
		return this.lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getApproveAmt() {
		return this.approveAmt;
	}

	public void setApproveAmt(int approveAmt) {
		this.approveAmt = approveAmt;
	}

	public int getDepartmentLevel() {
		return this.departmentLevel;
	}

	public void setDepartmentLevel(int departmentLevel) {
		this.departmentLevel = departmentLevel;
	}

	public int getHeaderId() {
		return this.headerId;
	}

	public void setHeaderId(int headerId) {
		this.headerId = headerId;
	}

	public int getLedgerId() {
		return this.ledgerId;
	}

	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
	}

}