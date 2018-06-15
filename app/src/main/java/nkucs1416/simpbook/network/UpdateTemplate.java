package nkucs1416.simpbook.network;

import nkucs1416.simpbook.database.TemplateDb;
import android.content.Context;

import nkucs1416.simpbook.util.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.sqlite.SQLiteDatabase;

/**
 * 处理更新服务器template接口类
 */

public class UpdateTemplate implements HttpResponeCallBack {

    private Context context;

    private SQLiteDatabase db;


    /**
     * 构建一个UpdateTemplate实例
     *
     * @param templateList template数据
     * @param token 身份验证
     * @param instance 运行环境实例
     * @param db_instance 数据库实例
     */
    public UpdateTemplate(ArrayList<Collection> templateList, String token, Context instance, SQLiteDatabase db_instance) {
        context = instance;
        db = db_instance;
        RequestApiData.getInstance().getUpdateTemplateData(templateList, token,
                UpdateTemplate.this, context);
    }

    /**
     * 响应开始回调函数
     *
     * @param apiName 接口api名
     */
    @Override
    public void onResponeStart(String apiName) {

    }

    /**
     * 响应成功回调函数
     *
     * @param apiName 接口api名
     * @param object 返回对象
     */
    @Override
    public void onSuccess(String apiName, Object object) {
        // TODO Auto-generated method stub
        //注册接口
        if (UrlConstance.KEY_UPDATE_TEMPLATE_INFO.equals(apiName)) {
            if (object != null && object instanceof JSONObject) {
                JSONObject info = (JSONObject) object;
                try {
                    String ret = info.getInt("ret") + "";
                    //请求成功
                    if (ret.equals(Constant.KEY_SUCCESS)) {
                        JSONArray templateIdList = info.getJSONArray("templateIdList");
                        int[] templateId = new int[templateIdList.length()];
                        int[] isDelete = new int[templateIdList.length()];
                        for(int i = 0;i<templateIdList.length();i++) {
                            JSONObject template = templateIdList.getJSONObject(i);
                            templateId[i] = template.getInt("templateId");
                            isDelete[i] = template.getInt("isDelete");
                        }
                        TemplateDb templateDb = new TemplateDb(db);
                        templateDb.updateTemplateStatus(templateId, isDelete);

                    } else {
                        // return baseUser;
                        // Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        String errode = info.getString("errcode");
                        String msg = info.getString("msg");
                    }
                } catch (JSONException e) {

                }
            }
        }
    }

    /**
     * 响应失败回调函数
     *
     * @param apiName 接口api名
     * @param t 异常
     * @param errorNo 错误码
     * @param strMsg 错误信息
     */
    @Override
    public void onFailure(String apiName, Throwable t, int errorNo, String strMsg) {

    }
}
