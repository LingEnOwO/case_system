package ncu.im3069.demo.app;

import java.util.Date;

import org.json.*;

public class Progress {
    /** case_id，案件編號 */
    private int case_id;

    /** requester_id，接案者編號 */
    private int requester_id;

    /** applicant_id，案主編號 */
    private int applicant_id;

    /** applicated，是否接受 */
    private int applicated;

    /** applicated_time，接受時間 */
    private Date applicated_time;

    /** finished，是否完成 */
    private int finished;

    /** finished_time，完成時間 */
    private Date finished_time;


    /**
     * 實例化（Instantiates）一個新的（new）Progress 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增進度時
     *
     * @param case_id 產品編號
     */
	public Progress(int case_id) {
		this.case_id = case_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Progress 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增進度時
     *
     * @param case_id 
     * @param requester_id
     */
	public Progress(int case_id, int requester_id) {
        this.case_id = case_id;
        this.requester_id = requester_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Progress 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增進度時
     *
     * @param case_id 
     * @param requester_id
     * @param applicant_id
     */
	public Progress(int case_id, int requester_id, int applicant_id) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Progress 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增進度時
     *
     * @param case_id 
     * @param requester_id
     * @param applicant_id
     * @param applicated
     * @param applicated_time
     */
	public Progress(int case_id, int requester_id, int applicant_id, int applicated, Date applicated_time) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicated = applicated;
        this.applicated_time = applicated_time;
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Progress 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增進度時
     *
     * @param case_id 
     * @param requester_id
     * @param applicant_id
     * @param applicated
     * @param applicated_time
     * @param finished
     * @param finished_time
     */
	public Progress(int case_id, int requester_id, int applicant_id, int applicated, Date applicated_time, int finished, Date finished_time) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicated = applicated;
        this.applicated_time = applicated_time;
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
     * 取得接案者編號
     *
     * @return
     */
	public int getApplicantId() {
		return this.applicant_id;
    }

    /**
     * 取得是否接受
     *
     * @return
     */
	public int getApplicated() {
		return this.applicated;
    }

    /**
     * 取得接受時間
     *
     * @return
     */
	public Date getApplicatedTime() {
		return this.applicated_time;
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
	public Date getFinishedTime() {
		return this.finished_time;
    }

    /**
     * 取得產品資訊
     *
     * @return JSONObject 回傳案件進度資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項案件進度所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("case_id", getCaseId());
        jso.put("requester_id", getRequesterId());
        jso.put("applicant_id", getApplicantId());
        jso.put("applicated", getApplicated());
        jso.put("applicated_time", getApplicatedTime());
        jso.put("finished", getFinished());
        jso.put("finished_time", getFinishedTime());

        return jso;
    }
}
