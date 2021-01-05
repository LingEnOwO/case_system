package ncu.im3069.demo.controller;

import java.io.*;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import ncu.im3069.demo.app.Comment;
import ncu.im3069.demo.app.CommentHelper;
import ncu.im3069.tools.JsonReader;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class MemberController<br>
 * MemberController類別（class）主要用於處理Member相關之Http請求（Request），繼承HttpServlet
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class CommentController extends HttpServlet {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private CommentHelper ch =  CommentHelper.getHelper();
    
    /**
     * 處理Http Method請求POST方法（新增資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int case_id = jso.getInt("case_id");
        int requester_id = jso.getInt("requester_id");
        int applicant_id = jso.getInt("applicant_id");
        
        /** 建立一個新的會員物件 */
        Comment c = new Comment(case_id, requester_id, applicant_id);
        
        /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
        JSONObject data = ch.create(c);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功新增評價表");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }

    /**
     * 處理Http Method請求GET方法（取得資料）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        String queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        JSONObject jsq = new JSONObject(queryString);
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        int requester_id = jsq.getInt("requester_id");
        int applicant_id = jsq.getInt("applicant_id");
        int case_id = jsq.getInt("case_id");

        JSONObject resp = new JSONObject();
        /** 判斷該字串是否存在，若存在代表要取回該案件之資料，否則代表要取回全部資料庫內案件之資料 */
        if (requester_id != 0) {
            JSONObject query = ch.getByRequesterId(requester_id);
            resp.put("status", "200");
            resp.put("message", "該案主案件評價資料取得成功");
            resp.put("response", query);
        }
        else if(applicant_id != 0){
            JSONObject query = ch.getByApplicantId(applicant_id);
            resp.put("status", "200");
            resp.put("message", "該接案者案件評價資料取得成功");
            resp.put("response", query);
        }
        else if(case_id != 0){
            JSONObject query = ch.getByCaseId(case_id);
            resp.put("status", "200");
            resp.put("message", "該案件評價資料取得成功");
            resp.put("response", query);
        }

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
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "400");
        resp.put("message", "無法刪除評價！");

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

        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int case_id = jso.getInt("case_id");
        int requester_evaluation = jso.getInt("requester_evaluation");
        int applicant_evaluation = jso.getInt("applicant_evaluation");
        String requester_comment = jso.getString("requester_comment");
        String applicant_comment = jso.getString("applicant_comment");

        

        if (!requester_comment.isEmpty()) {
            Comment c = new Comment(case_id, requester_evaluation, requester_comment);
            /** 透過傳入之參數，新建一個以這些參數之會員Member物件 */
            JSONObject query = ch.updateRequesterComment(c);
            resp.put("status", "200");
            resp.put("message", "該案主評價更新成功");
            resp.put("response", query);
        }
        else if(!applicant_comment.isEmpty()){
            Comment c = new Comment(case_id, applicant_evaluation, applicant_comment);
            /** 透過傳入之參數，新建一個以這些參數之會員Member物件 */
            JSONObject query = ch.updateApplicantComment(c);
            resp.put("status", "200");
            resp.put("message", "該接案者評價更新成功");
            resp.put("response", query);
        }        
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
}