package com.example.aiot_home.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.frs.v2.region.FrsRegion;
import com.huaweicloud.sdk.frs.v2.*;
import com.huaweicloud.sdk.frs.v2.model.*;

import java.util.ArrayList;
import java.util.List;

public class FaceApi {
    private static String Ak="JSY3UMY6C33ZFAJBKRQS";
    private static String Sk="87dt5M3Z76bdSWLX90YhOW65dqRfubp2oYRfwcr5";

    public void CreateFaceDatabse(String Name){
        String ak = Ak;
        String sk = Sk;

        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);

        FrsClient client = FrsClient.newBuilder()
                .withCredential(auth)
                .withRegion(FrsRegion.valueOf("cn-north-4"))
                .build();
        CreateFaceSetRequest request = new CreateFaceSetRequest();
        CreateFaceSetReq body = new CreateFaceSetReq();
        body.withFaceSetName(Name);
        request.withBody(body);
        try {
            CreateFaceSetResponse response = client.createFaceSet(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }
    public void DeleteDatabse(String Name){
        String ak = Ak;
        String sk = Sk;

        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);

        FrsClient client = FrsClient.newBuilder()
                .withCredential(auth)
                .withRegion(FrsRegion.valueOf("cn-north-4"))
                .build();
        DeleteFaceSetRequest request = new DeleteFaceSetRequest();
        request.withFaceSetName(Name);
        try {
            DeleteFaceSetResponse response = client.deleteFaceSet(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }
    public void SelectFaceDatabase(){
        ICredential auth = new BasicCredentials()
                .withAk(Ak)
                .withSk(Sk);

        FrsClient client = FrsClient.newBuilder()
                .withCredential(auth)
                .withRegion(FrsRegion.valueOf("cn-north-4"))
                .build();
        ShowAllFaceSetsRequest request = new ShowAllFaceSetsRequest();
        try {
            ShowAllFaceSetsResponse response = client.showAllFaceSets(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getRequestId());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }
    public String CreateFace(String DatabaseName,String base64) {
        String ak = Ak;
        String sk = Sk;
        String FaceId = "'";
        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);

        FrsClient client = FrsClient.newBuilder()
                .withCredential(auth)
                .withRegion(FrsRegion.valueOf("cn-north-4"))
                .build();
        AddFacesByBase64Request request = new AddFacesByBase64Request();
        request.withFaceSetName(DatabaseName);
        AddFacesBase64Req body = new AddFacesBase64Req();
        body.withImageBase64(base64);
        request.withBody(body);
        try {
            AddFacesByBase64Response response = client.addFacesByBase64(request);
            String Json = JSON.toJSONString(response);
            JSONObject jsonObject = JSON.parseObject(Json);
            JSONArray faces = jsonObject.getJSONArray("faces");
            JSONObject jsonObject1 = faces.getJSONObject(0);
            FaceId = jsonObject1.getString("faceId");
            return FaceId;
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return FaceId;
    }
    public void DelectFace(String ClassName,String FaceId) {
        String ak = Ak;
        String sk = Sk;

        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);

        FrsClient client = FrsClient.newBuilder()
                .withCredential(auth)
                .withRegion(FrsRegion.valueOf("cn-north-4"))
                .build();
        DeleteFaceByFaceIdRequest request = new DeleteFaceByFaceIdRequest();
        request.withFaceSetName(ClassName);
        request.withFaceId(FaceId);
        try {
            DeleteFaceByFaceIdResponse response = client.deleteFaceByFaceId(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }
    public List<String> SelectFace(String ClassName, String base){
        String ak = Ak;
        String sk = Sk;
        List<String> FaceId=new ArrayList<>();
        ICredential auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk);

        FrsClient client = FrsClient.newBuilder()
                .withCredential(auth)
                .withRegion(FrsRegion.valueOf("cn-north-4"))
                .build();
        SearchFaceByBase64Request request = new SearchFaceByBase64Request();
        request.withFaceSetName(ClassName);
        FaceSearchBase64Req body = new FaceSearchBase64Req();
        body.withImageBase64(base);
        request.withBody(body);
        try {
            SearchFaceByBase64Response response = client.searchFaceByBase64(request);
            String string = JSON.toJSONString(response);
            JSONArray jsonArray = JSON.parseObject(string).getJSONArray("faces");
            for(int i=0;i<jsonArray.size();i++){
                String similarity=jsonArray.getJSONObject(i).getString("similarity");
                if(Double.parseDouble(similarity)>0.85){
                    String faceId=jsonArray.getJSONObject(i).getString("faceId");
                    FaceId.add(faceId);
                }
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return FaceId;
    }
}
