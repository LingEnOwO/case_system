package ncu.im3069.demo.app;

import org.json.*;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Member
 * Member類別（class）具有會員所需要之屬性與方法，並且儲存與會員相關之商業判斷邏輯<br>
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class Member {
    
    /** id，會員編號 */
    private int id;
    
    /** email，會員電子郵件信箱 */
    private String email;
    
    /** name，會員姓名 */
    private String name;
    
    /** password，會員密碼 */
    private String password;
    
    /** login_times，更新時間的分鐘數 */
    private int login_times;

    // requester_evaluation，擔任案主的評價
    private float requester_evaluation;

    // applicant_evaluation，擔任接案者的評價
    private float applicant_evaluation;
    
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private MemberHelper mh =  MemberHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        update();
    }

    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新會員資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新會員資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param id 會員編號
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(int id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，將每一筆資料新增為一個會員物件
     *
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     * @param login_times 更新時間的分鐘數
     */
    public Member(int id, String email, String password, String name, int login_times) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.login_times = login_times;
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，將每一筆資料新增為一個會員物件
     *
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     * @param login_times 更新時間的分鐘數
     * @param requester_evaluation 案主的評價
     * @param applicant_evaluation 接案者的評價
     */
    public Member(int id, String email, String password, String name, int login_times, float requester_evaluation, float applicant_evaluation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.login_times = login_times;
        this.requester_evaluation = requester_evaluation;
        this.applicant_evaluation = applicant_evaluation;
    }

    /**
     * 取得會員之編號
     *
     * @return the id 回傳會員編號
     */
    public int getId() {
        return this.id;
    }

    /**
     * 取得會員之電子郵件信箱
     *
     * @return the email 回傳會員電子郵件信箱
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * 取得會員之姓名
     *
     * @return the name 回傳會員姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 取得會員之密碼
     *
     * @return the password 回傳會員密碼
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * 取得更新資料時間之分鐘數
     *
     * @return the login times 回傳更新資料時間之分鐘數
     */
    public int getLoginTimes() {
        return this.login_times;
    }

    /**
     * 取得案主之評價
     *
     * @return the requester_evaluation 回傳案主之評價
     */
    public float getRequesterEvaluation() {
        return this.requester_evaluation;
    }

    /**
     * 取得接案者之評價
     *
     * @return the requester_evaluation 回傳接案者之評價
     */
    public float getApplicantEvaluation() {
        return this.applicant_evaluation;
    }

    /**
     * 更新會員資料
     *
     * @return the JSON object 回傳SQL更新之結果與相關封裝之資料
     */
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        
        /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
        data = mh.update(this);
        
        return data;
    }
    
    /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("requester_evaluation", getRequesterEvaluation());
        jso.put("applicant_evaluation", getApplicantEvaluation());
        jso.put("login_times", getLoginTimes());
        
        return jso;
    }
    
}