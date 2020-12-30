package ncu.im3069.demo.app;

import java.sql.*;

import org.json.*;

import ncu.im3069.demo.util.DBMgr;

public class CommentHelper {
    
    private CommentHelper() {

    }

    private static CommentHelper ch;
    private Connection conn = null;
    private PreparedStatement pres = null;

    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個CommentHelper物件
     *
     * @return the helper 回傳MemberHelper物件
     */
    public static CommentHelper getHelper() {
        /** Singleton檢查是否已經有CommentHelper物件，若無則new一個，若有則直接回傳 */
        if(ch == null) ch = new CommentHelper();
        
        return ch;
    }

    public JSONObject getAll(){
        /** 新建一個 Comment 物件之 c 變數，用於紀錄每一位查詢回之案件資料 */
    	Comment c = null;
        /** 用於儲存所有檢索回之案件，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;

        try{
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `case_system`.`comments`";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int case_id = rs.getInt("case_id");
                int requester_id = rs.getInt("requester_id");
                int applicant_id = rs.getInt("applicant_id");
                int requester_evaluation = rs.getInt("requester_evaluation");
                int applicant_evaluation = rs.getInt("applicant_evaluation");
                String requester_comment = rs.getString("requester_comment");
                String applicant_comment = rs.getString("applicant_comment");
                
                /** 將每一筆案件資料產生一名新Comment物件 */
                c = new Comment(case_id, requester_id, applicant_id, requester_evaluation, applicant_evaluation, requester_comment, applicant_comment);
                /** 取出該項案件之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end - start);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

    public JSONObject getByCaseId(int id){
        /** 新建一個 Comment 物件之 c 變數，用於紀錄每一位查詢回之案件資料 */
        Comment c = null;
        /** 用於儲存所有檢索回之案件評鑑，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `case_system`.`comments` WHERE `comments`.`case_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, Integer.toString(id));
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 將 ResultSet 之資料取出 */
                int case_id = rs.getInt("case_id");
                int requester_id = rs.getInt("requester_id");
                int applicant_id = rs.getInt("applicant_id");
                int requester_evaluation = rs.getInt("requester_evaluation");
                int applicant_evaluation = rs.getInt("applicant_evaluation");
                String requester_comment = rs.getString("requester_comment");
                String applicant_comment = rs.getString("applicant_comment");
                
                /** 將每一筆商品資料產生一名新Comment物件 */
                c = new Comment(case_id, requester_id, applicant_id, requester_evaluation, applicant_evaluation, requester_comment, applicant_comment);
                /** 取出該項案件之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        /** 將進度資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("data", jsa);

        return response;
    }

    public JSONObject getByRequesterId(int id){
        /** 新建一個 Comment 物件之 c 變數，用於紀錄每一位查詢回之案件資料 */
        Comment c = null;
        /** 用於儲存所有檢索回之案件評鑑，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `case_system`.`comments` WHERE `comments`.`requester_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, Integer.toString(id));
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 將 ResultSet 之資料取出 */
                int case_id = rs.getInt("case_id");
                int requester_id = rs.getInt("requester_id");
                int applicant_id = rs.getInt("applicant_id");
                int requester_evaluation = rs.getInt("requester_evaluation");
                int applicant_evaluation = rs.getInt("applicant_evaluation");
                String requester_comment = rs.getString("requester_comment");
                String applicant_comment = rs.getString("applicant_comment");
                
                /** 將每一筆商品資料產生一名新Comment物件 */
                c = new Comment(case_id, requester_id, applicant_id, requester_evaluation, applicant_evaluation, requester_comment, applicant_comment);
                /** 取出該項案件之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        /** 將進度資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("data", jsa);

        return response;
    }

    public JSONObject getByApplicantId(int id){
        /** 新建一個 Comment 物件之 c 變數，用於紀錄每一位查詢回之案件資料 */
        Comment c = null;
        /** 用於儲存所有檢索回之案件評鑑，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `case_system`.`comments` WHERE `comments`.`applicant_id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, Integer.toString(id));
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 將 ResultSet 之資料取出 */
                int case_id = rs.getInt("case_id");
                int requester_id = rs.getInt("requester_id");
                int applicant_id = rs.getInt("applicant_id");
                int requester_evaluation = rs.getInt("requester_evaluation");
                int applicant_evaluation = rs.getInt("applicant_evaluation");
                String requester_comment = rs.getString("requester_comment");
                String applicant_comment = rs.getString("applicant_comment");
                
                /** 將每一筆商品資料產生一名新Comment物件 */
                c = new Comment(case_id, requester_id, applicant_id, requester_evaluation, applicant_evaluation, requester_comment, applicant_comment);
                /** 取出該項案件之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        /** 將進度資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("data", jsa);

        return response;
    }

    /**
     * 建立該評價至資料庫
     *
     * @param c 一個案件之Case物件
     * @return the JSON object 回傳SQL指令執行之結果
     */
    public JSONObject create(Comment c) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "INSERT INTO `case_system`.`cases`(`case_id`, `requester_id`, `applicant_id`)"
                    + " VALUES(?, ?, ?)";
            
            /** 取得所需之參數 */
            int case_id = c.getCaseId();
            int requester_id = c.getRequesterId();
            int applicant_id = c.getApplicantId();

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, case_id);
            pres.setInt(2, requester_id);
            pres.setInt(3, applicant_id);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end - start);

        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("row", row);

        return response;
    }

    /**
     * 更新接案者給案主之評價資料
     *
     * @param c 一個案件之Comment物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject updateRequesterComment(Comment c) {
        /** 紀錄回傳之資料 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `case_system`.`comments` SET `requester_evaluation` = ? ,`requester_comment` = ? WHERE `case_id` = ?";
            /** 取得所需之參數 */
            int requester_evaluation = c.getEvaluation();
            String requester_comment = c.getComment();
            int case_id = c.getCaseId();

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, requester_evaluation);
            pres.setString(2, requester_comment);
            pres.setInt(3, case_id);

            /** 執行更新之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end - start);
        
        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

    /**
     * 更新案主給接案者之評價資料
     *
     * @param c 一個案件之Comment物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject updateApplicantComment(Comment c) {
        /** 紀錄回傳之資料 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `case_system`.`comments` SET `applicant_evaluation` = ? ,`applicant_comment` = ? WHERE `case_id` = ?";
            /** 取得所需之參數 */
            int applicant_evaluation = c.getEvaluation();
            String applicant_comment = c.getComment();
            int case_id = c.getCaseId();

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, applicant_evaluation);
            pres.setString(2, applicant_comment);
            pres.setInt(3, case_id);

            /** 執行更新之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end - start);
        
        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

}
