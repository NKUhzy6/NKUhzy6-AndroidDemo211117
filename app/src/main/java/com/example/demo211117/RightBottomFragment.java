package com.example.demo211117;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RightBottomFragment extends Fragment {

    private ListView fragment4ListView;
    private List<String> titlesList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        fragment4ListView = (ListView) view.findViewById(R.id.fragment4ListView);
        doFragment4(view);
        return view;
    }

    private static final int UPDATE = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE:
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titlesList);
                    fragment4ListView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

    private void doFragment4(View view){
        Button fragment4Btn = (Button) view.findViewById(R.id.fragment4Btn);
        titlesList = new ArrayList<>();
        //String[] data = {"1", "2", "3", "4", "5", "6","7", "8", "9", "10","1", "2", "3", "4", "5", "6","7", "8", "9", "10"};
        fragment4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //parseJSONWithGson();

                        try{
                                okHttp();

                            /*getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ListView fragment4ListView = (ListView) view.findViewById(R.id.fragment4ListView);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titlesList);
                                    fragment4ListView.setAdapter(adapter);
                                }
                            });*/


                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("TAG", "run: 000");
                        }finally {
                            parseJSONWithGson(jsonData);
                            Message message = new Message();
                            message.what = UPDATE;
                            handler.sendMessage(message);
                        }

                    }
                };
                ThreadPoolSingleton.getInstance().threadPoolExecutor.execute(runnable);


            }
        });
    }

    private void parseJSONWithJSONObject() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        for (int page = 0; page<10; page++){
            String data = "https://www.wanandroid.com/article/list/"+ page+ "/json";
            Request request = new Request.Builder().url(data).build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

            JSONObject jsonObject = new JSONObject(jsonData);
            //jsonObject = jsonObject.getJSONObject("data");
            String data0 = jsonObject.optString("data");

            JSONObject jsonObject1 = new JSONObject(data0);
            JSONArray jsonArray =new JSONArray();
            jsonArray = jsonObject1.optJSONArray("datas");


            for (int i =0; i<jsonArray.length(); i++){
                String title = jsonArray.getJSONObject(i).optString("title");
                titlesList.add(title);
            }
        }
    }

    /**
     * Copyright 2021 bejson.com
     */


    /**
     * Auto-generated: 2021-11-19 18:53:53
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class JsonRootBean {

        private Data data;
        private int errorCode;
        private String errorMsg;
        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
        public String getErrorMsg() {
            return errorMsg;
        }

    }

    /**
     * Copyright 2021 bejson.com
     */

    public class Data {

        private int curPage;
        private List<Datas> datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }
        public int getCurPage() {
            return curPage;
        }

        public void setDatas(List<Datas> datas) {
            this.datas = datas;
        }
        public List<Datas> getDatas() {
            return datas;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }
        public int getOffset() {
            return offset;
        }

        public void setOver(boolean over) {
            this.over = over;
        }
        public boolean getOver() {
            return over;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }
        public int getPageCount() {
            return pageCount;
        }

        public void setSize(int size) {
            this.size = size;
        }
        public int getSize() {
            return size;
        }

        public void setTotal(int total) {
            this.total = total;
        }
        public int getTotal() {
            return total;
        }

    }


    /**
     * Auto-generated: 2021-11-19 18:53:53
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Datas {

        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private String host;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int realSuperChapterId;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private List<Tags> tags;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }
        public String getApkLink() {
            return apkLink;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }
        public int getAudit() {
            return audit;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
        public String getAuthor() {
            return author;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }
        public boolean getCanEdit() {
            return canEdit;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }
        public int getChapterId() {
            return chapterId;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }
        public String getChapterName() {
            return chapterName;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }
        public boolean getCollect() {
            return collect;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }
        public int getCourseId() {
            return courseId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getDesc() {
            return desc;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }
        public String getDescMd() {
            return descMd;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }
        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }
        public boolean getFresh() {
            return fresh;
        }

        public void setHost(String host) {
            this.host = host;
        }
        public String getHost() {
            return host;
        }

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setLink(String link) {
            this.link = link;
        }
        public String getLink() {
            return link;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }
        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }
        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }
        public String getOrigin() {
            return origin;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
        public String getPrefix() {
            return prefix;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }
        public String getProjectLink() {
            return projectLink;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }
        public long getPublishTime() {
            return publishTime;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }
        public int getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }
        public int getSelfVisible() {
            return selfVisible;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }
        public long getShareDate() {
            return shareDate;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }
        public String getShareUser() {
            return shareUser;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }
        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }
        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }
        public List<Tags> getTags() {
            return tags;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getUserId() {
            return userId;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
        public int getVisible() {
            return visible;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
        public int getZan() {
            return zan;
        }

    }

    class Tags{
        String name;
        String url;
    }

    private String jsonData;

    private String okHttp() throws IOException {
        String data = "https://www.wanandroid.com/article/list/1/json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(data).build();
        Response response = client.newCall(request).execute();
        jsonData = response.body().string();
        Log.d("TAG", "parseJSONWithGson: "+jsonData);
        return jsonData;
    }

    private void parseJSONWithGson(String jsonData){


        Gson gson = new Gson();
        JsonRootBean jsonRootBean = gson.fromJson(jsonData, JsonRootBean.class);

            Data data1 = jsonRootBean.getData();
            List<Datas> datas = data1.getDatas();
            for (Datas datas1: datas){
                String title = datas1.getTitle();
                titlesList.add(title);
            }


    }
}
