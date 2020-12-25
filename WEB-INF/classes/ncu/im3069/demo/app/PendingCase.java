package ncu.im3069.demo.app;

import org.json.*;

public class PendingCase {
    /** case_id，案件編號 */
    private int case_id;

    /** requester_id，接案者編號 */
    private int requester_id;

    /** applicant_id，案主編號 */
    private int applicant_id;

    /** applicant_phone，案主電話 */
    private String applicant_phone;

    /** applicant_email，案主電子郵件 */
    private String applicant_email;

    /** applicant_evaluation，接案者評價 */
    private float applicant_evaluation;

    /** finished，是否審核 */
    private int approval;

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行
     *
     * @param case_id
     */
	public PendingCase(int case_id) {
        this.case_id = case_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param approval
     */
	public PendingCase(int case_id, int requester_id, int applicant_id, int approval) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.approval = approval;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_evaluation
     * @param approval
     */
	public PendingCase(int case_id, int requester_id, int applicant_id, float applicant_evaluation, int approval) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_evaluation = applicant_evaluation;
        this.approval = approval;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_phone
     * @param applicant_email
     * @param approval
     */
	public PendingCase(int case_id, int requester_id, int applicant_id, String applicant_phone, String applicant_email, int approval) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_phone = applicant_phone;
        this.applicant_email = applicant_email;
        this.approval = approval;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_phone
     * @param applicant_email
     * @param applicant_evaluation
     * @param approval
     */
	public PendingCase(int case_id, int requester_id, int applicant_id, String applicant_phone, String applicant_email, float applicant_evaluation, int approval) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_phone = applicant_phone;
        this.applicant_email = applicant_email;
        this.applicant_evaluation = applicant_evaluation;
        this.approval = approval;
    }

    /**
     * 取得案件編號
     *
     * @return
     */
	public int getCaseId() {
		return this.case_id;
    }

    /**
     * 取得案主編號
     *
     * @return
     */
	public int getRequesterId() {
		return this.requester_id;
    }

    /**
     * 取得案主編號
     *
     * @return
     */
	public int getApplicantId() {
		return this.applicant_id;
    }

    /**
     * 取得接案者評價
     *
     * @return
     */
	public float getApplicantEvaluation() {
		return this.applicant_evaluation;
    }

    /**
     * 取得案主評語
     *
     * @return
     */
	public String getApplicantPhone() {
		return this.applicant_phone;
    }

    /**
     * 取得接案者評語
     *
     * @return
     */
	public String getApplicantEmail() {
		return this.applicant_email;
    }

    /**
     * 取得是否審核完成
     *
     * @return
     */
	public int getApproval() {
		return this.approval;
    }

    /**
     * 取得案件完成狀況
     *
     * @return JSONObject 回傳案件審核狀況資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項案件是否完成所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("case_id", getCaseId());
        jso.put("requester_id", getRequesterId());
        jso.put("applicant_id", getApplicantId());
        jso.put("applicant_evaluation", getApplicantEvaluation());
        jso.put("applicant_phone", getApplicantPhone());
        jso.put("applicant_email", getApplicantEmail());
        jso.put("approval", getApproval());

        return jso;
    }
}
