package ncu.im3069.demo.app;

import org.json.*;

public class Comment {
    /** case_id，案件編號 */
    private int case_id;

    /** requester_id，接案者編號 */
    private int requester_id;

    /** applicant_id，案主編號 */
    private int applicant_id;

    /** evaluation，評價 */
    private int evaluation;

    /** comment，評論 */
    private String comment;

    /** requester_evaluation，案主評價 */
    private String requester_evaluation;

    /** applicant_evaluation，接案者評價 */
    private String applicant_evaluation;

    /** requester_comment，案主評論 */
    private String requester_comment;

    /** applicant_comment，接案者評論 */
    private String applicant_comment;

    /**
     * 實例化（Instantiates）一個新的（new）Comment 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param case_id 案件編號
     */
	public Comment(int case_id) {
		this.case_id = case_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Comment 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增評價時
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     */
	public Comment(int case_id, int requester_id, int applicant_id) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Comment 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增評價時
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_evaluation
     * @param requester_evaluation
     */
	public Comment(int case_id, int requester_id, int applicant_id, String applicant_evaluation, String requester_evaluation) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_evaluation = applicant_evaluation;
        this.requester_evaluation = requester_evaluation;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Comment 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增評價時
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_evaluation
     * @param requester_evaluation
     * @param applicant_comment
     * @param requester_comment
     * @param finished
     * @param finished_time
     */
	public Comment(int case_id, int requester_id, int applicant_id, String requester_evaluation, String applicant_evaluation, String requester_comment ,String applicant_comment) {
        this.case_id = case_id;
        this.applicant_id = applicant_id;
        this.requester_id = requester_id;
        this.requester_evaluation = requester_evaluation;
        this.applicant_evaluation = applicant_evaluation;
        this.applicant_comment = applicant_comment;
        this.requester_comment = requester_comment;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Comment 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增評價時
     *
     * @param case_id
     * @param evaluation
     * @param comment
     */
	public Comment(int case_id, int evaluation, String comment) {
        this.case_id = case_id;
        this.evaluation = evaluation;
        this.comment = comment;
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
     * 取得案主評價
     *
     * @return
     */
	public String getRequesterEvaluation() {
		return this.requester_evaluation;
    }

    /**
     * 取得接案者評價
     *
     * @return
     */
	public String getApplicantEvaluation() {
		return this.applicant_evaluation;
    }

    /**
     * 取得評價
     *
     * @return
     */
	public int getEvaluation() {
		return this.evaluation;
    }

    /**
     * 取得評語
     *
     * @return
     */
	public String getComment() {
		return this.comment;
    }

    /**
     * 取得案主評語
     *
     * @return
     */
	public String getRequesterComment() {
		return this.requester_comment;
    }

    /**
     * 取得接案者評語
     *
     * @return
     */
	public String getApplicantComment() {
		return this.applicant_comment;
    }

    /**
     * 取得案件完成狀況
     *
     * @return JSONObject 回傳案件完成狀況資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項案件是否完成所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("case_id", getCaseId());
        jso.put("requester_id", getRequesterId());
        jso.put("applicant_id", getApplicantId());
        jso.put("requester_evaluation", getRequesterEvaluation());
        jso.put("applicant_evaluation", getApplicantEvaluation());
        jso.put("requester_comment", getRequesterComment());
        jso.put("applicant_comment", getApplicantComment());

        return jso;
    }
}
