package ncu.im3069.demo.app;

import org.json.*;

public class Case {
    /** case_id，案件編號 */
    private int case_id;

    /** requester_id，案主編號 */
    private int requester_id;

    /** applicant_id，案主編號 */
    private int applicant_id;

    /** phone，案主電話 */
    private String phone;

    /** title，案件標題 */
    private String title;

    /** content，案件內容 */
    private String content;

    /** area，案件地點 */
    private String area;

    /** case_time，案件時間 */
    private String case_time;

    /** end_time，案件截止時間 */
    private String  end_time;

    /** pay，案件報酬 */
    private String pay;

    /** finished，案件完成 */
    private int finished;

    /** finished_time，案件完成時間 */
    private String finished_time = "0";

    /** finished_time，案件接受時間 */
    private String applicated_time = "0";

    /**
     * 實例化（Instantiates）一個新的（new）Case 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增案件時
     *
     * @param case_id 案件編號
     */
	public Case(int case_id) {
		this.case_id = case_id;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增案件時
     *
     * @param requester_id 
     * @param title 
     * @param pay
     */
	public Case(int requester_id, String title, String pay) {
		this.requester_id = requester_id;
		this.title = title;
		this.pay = pay;
    }


      /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增案件時
     *
     * @param requester_id 
     * @param phone
     * @param title 
     * @param content
     * @param area
     * @param start_time
     * @param end_time
     * @param pay
     */
	public Case(int requester_id, String phone, String title, String content, String area, String case_time, String end_time, String pay) {
        this.requester_id = requester_id;
        this.phone = phone;
        this.title = title;
        this.content = content;
        this.area = area;
        this.case_time = case_time;
        this.end_time = end_time;
		this.pay = pay;
    }
    

    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增案件時
     *
     * @param case_id
     * @param requester_id 
     * @param phone
     * @param title 
     * @param content
     * @param area
     * @param case_time
     * @param end_time
     * @param pay
     */
	public Case(int case_id, int requester_id,String phone, String title, String content, String area, String case_time, String end_time, String pay) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.phone = phone;
        this.title = title;
        this.content = content;
        this.area = area;
        this.case_time = case_time;
        this.end_time = end_time;
		this.pay = pay;
	}
    
    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增案件時
     *
     * @param case_id
     * @param requester_id 
     * @param applicant_id
     * @param finished_time
     * @param applicated_time
     */
	public Case(int case_id, int requester_id, int applicant_id,String applicated_time, String finished_time) {
        this.case_id = case_id;
        this.requester_id = requester_id;
        this.applicant_id = applicant_id;
        this.applicated_time = applicated_time;
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
     * 取得案主電話
     *
     * @return
     */
	public String getPhone() {
		return this.phone;
    }

    /**
     * 取得案件大綱
     *
     * @return
     */
	public String getTitle() {
		return this.title;
    }

    /**
     * 取得案件內容
     *
     * @return
     */
	public String getContent() {
		return this.content;
    }

    /**
     * 取得案件地點
     *
     * @return
     */
	public String getArea() {
		return this.area;
    }

    /**
     * 取得案件開始時間
     *
     * @return
     */
	public String getCaseTime() {
		return this.case_time;
    }

    /**
     * 取得案件結束時間
     *
     * @return
     */
	public String getEndTime() {
		return this.end_time;
    }

    /**
     * 取得案件報酬
     *
     * @return
     */
	public String getPay() {
		return this.pay;
    }

    /**
     * 取得案件完成
     *
     * @return
     */
	public int getFinished() {
		return this.finished;
    }

    /**
     * 取得案件完成時間
     *
     * @return
     */
	public String getFinishedTime() {
		return this.finished_time;
    }

    /**
     * 取得案件接受時間
     *
     * @return
     */
	public String getApplicatedTime() {
		return this.applicated_time;
    }

    /**
     * 取得案件資訊
     *
     * @return JSONObject 回傳案件資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項案件所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("case_id", getCaseId());
        jso.put("requester_id", getRequesterId());
        jso.put("applicant_id", getApplicantId());
        jso.put("phone", getPhone());
        jso.put("title", getTitle());
        jso.put("content", getContent());
        jso.put("area", getArea());
        jso.put("case_time", getCaseTime());
        jso.put("end_time", getEndTime());
        jso.put("pay", getPay());
        jso.put("finished", getFinished());
        jso.put("finished_time", getFinishedTime());
        jso.put("applicated_time", getApplicatedTime());

        return jso;
    }
}
