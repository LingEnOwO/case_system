package ncu.im3069.demo.app;

import org.json.*;

public class Task {
    /** case_id，案件編號 */
    private int case_id;

    /** requester_id，接案者編號 */
    private int requester_id;

    /** applicant_id，案主編號 */
    private int applicant_id;

    /** requester_evaluation，案主評價 */
    private float requester_evaluation;

    /** applicant_evaluation，接案者評價 */
    private float applicant_evaluation;

    /** requester_comment，案主評論 */
    private String requester_comment;

    /** applicant_comment，接案者評論 */
    private String applicant_comment;

    /** finished，是否結案 */
    private int finished;

    /** finished_time，結案時間 */
    private String finished_time;

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param case_id 案件編號
     */
	public Task(int case_id) {
		this.case_id = case_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_evaluation
     * @param requester_evaluation
     */
	public Task(int case_id, int requester_id, int applicant_id, float applicant_evaluation, float requester_evaluation) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_evaluation = applicant_evaluation;
        this.requester_evaluation = requester_evaluation;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param case_id
     * @param requester_id
     * @param applicant_id
     * @param applicant_evaluation
     * @param requester_evaluation
     * @param finished
     * @param finished_time
     */
	public Task(int case_id, int requester_id, int applicant_id, float applicant_evaluation, float requester_evaluation, int finished, String finished_time) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_evaluation = applicant_evaluation;
        this.requester_evaluation = requester_evaluation;
        this.finished = finished;
        this.finished_time = finished_time;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Task 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
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
	public Task(int case_id, int requester_id, int applicant_id, float applicant_evaluation, float requester_evaluation,String applicant_comment,String requester_comment, int finished, String finished_time) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicant_evaluation = applicant_evaluation;
        this.requester_evaluation = requester_evaluation;
        this.applicant_comment = applicant_comment;
        this.requester_comment = requester_comment;
        this.finished = finished;
        this.finished_time = finished_time;
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
	public float getRequesterEvaluation() {
		return this.requester_evaluation;
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
     * 取得是否完成
     *
     * @return
     */
	public int getFinished() {
		return this.finished;
    }

    /**
     * 取得完成時間
     *
     * @return
     */
	public String getFinishedTime() {
		return this.finished_time;
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
        jso.put("finished", getFinished());
        jso.put("finished_time", getFinishedTime());

        return jso;
    }
}
