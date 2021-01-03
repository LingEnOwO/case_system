package ncu.im3069.demo.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Progress;
import ncu.im3069.demo.app.ProgressHelper;
import ncu.im3069.tools.JsonReader;

public class ProgressController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	private ProgressHelper ph =  ProgressHelper.getHelper();

    public ProgressController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        String queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        JSONObject jsq = new JSONObject(queryString);
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        int requester_id = Integer.parseInt(jsq.getString("requester_id"));
        int applicant_id = Integer.parseInt(jsq.getString("applicant_id"));
        int case_id = Integer.parseInt(jsq.getString("case_id"));

        JSONObject resp = new JSONObject();
        /** 判斷該字串是否存在，若存在代表要取回該案件之資料，否則代表要取回全部資料庫內案件之資料 */
        if (!jsq.getString("requester_id").isEmpty()) {
            JSONObject query = ph.getByRequesterId(requester_id);
            resp.put("status", "200");
            resp.put("message", "該案主案件進度資料取得成功");
            resp.put("response", query);
        }
        else if(!jsq.getString("applicant_id").isEmpty()){
            JSONObject query = ph.getByRequesterId(applicant_id);
            resp.put("status", "200");
            resp.put("message", "該接案者案件進度資料取得成功");
            resp.put("response", query);
        }
        else if(!jsq.getString("case_id").isEmpty()){
            JSONObject query = ph.getByCaseId(case_id);
            resp.put("status", "200");
            resp.put("message", "該接案者案件進度資料取得成功");
            resp.put("response", query);
        }

        jsr.response(resp, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int case_id = jso.getInt("case_id");
        int requester_id = jso.getInt("requester_id");
        int applicant_id = jso.getInt("applicant_id");
        
        /** 建立一個新的會員物件 */
        Progress p = new Progress(case_id, requester_id, applicant_id);
        
        /** 透過ProgressHelper物件的create()方法新建一個會員至資料庫 */
        JSONObject data = ph.create(p);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功接案");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
	}

    /**
     * 處理Http Method請求DELETE方法（刪除）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int case_id = jso.getInt("case_id");
        
        /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
        JSONObject query = ph.deleteById(case_id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "案件移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }

    
    /**
     * 處理Http Method請求PUT方法（更新）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        
        /** 取出經解析到JSONObject之Request參數 */
        int applicant_id = jso.getInt("applicant_id");
        
        /** 透過CaseHelper物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = ph.updateProgressFinished(applicant_id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "已完成案件");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }

}
